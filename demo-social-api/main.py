from contextlib import asynccontextmanager
from pathlib import Path
from typing import Annotated, TypeVar

from fastapi import Depends, FastAPI, HTTPException, Query, status
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import FileResponse
from fastapi.staticfiles import StaticFiles
from models import Comment, Like, Post, User
from schemas import (
    CommentCreate,
    CommentOut,
    LikeCreate,
    LikeOut,
    PostCreate,
    PostOut,
    PostUpdate,
    UserCreate,
    UserOut,
)
from seed import seed_if_empty
from sqlalchemy.orm import Session

from database import Base, SessionLocal, engine, get_db

ModelT = TypeVar("ModelT")
DbSession = Annotated[Session, Depends(get_db)]


@asynccontextmanager
async def lifespan(_app: FastAPI):
    """Create SQLite tables and seed demo data on startup."""
    Base.metadata.create_all(bind=engine)
    with SessionLocal() as db:
        seed_if_empty(db)
    yield


app = FastAPI(
    title="Social Media Demo REST API",
    description="Workshop demo API with Users, Posts, Comments, and Likes (FastAPI + SQLAlchemy + SQLite).",
    version="1.0.0",
    lifespan=lifespan,
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_methods=["*"],
    allow_headers=["*"],
)


BASE_DIR = Path(__file__).parent
app.mount("/static", StaticFiles(directory=BASE_DIR / "static"), name="static")


def _get_or_404(db: Session, model: type[ModelT], item_id: int, label: str) -> ModelT:
    item = db.get(model, item_id)
    if item is None:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"{label} with id={item_id} not found",
        )
    return item


def _serialize_post(post: Post) -> PostOut:
    return PostOut(
        id=post.id,
        user_id=post.user_id,
        content=post.content,
        created_at=post.created_at,
        likes_count=len(post.likes),
        comments_count=len(post.comments),
    )


@app.get("/", include_in_schema=False)
def root():
    """Serve the MiniSocial SPA at the root path."""
    return FileResponse(BASE_DIR / "static" / "index.html")


# ---------------------------------------------------------------------------
# Users
# ---------------------------------------------------------------------------
@app.get("/users", response_model=list[UserOut], tags=["Users"])
def list_users(db: DbSession):
    return db.query(User).order_by(User.id).all()


@app.get("/users/{user_id}", response_model=UserOut, tags=["Users"])
def get_user(user_id: int, db: DbSession):
    return _get_or_404(db, User, user_id, "User")


@app.post(
    "/users",
    response_model=UserOut,
    status_code=status.HTTP_201_CREATED,
    tags=["Users"],
)
def create_user(payload: UserCreate, db: DbSession):
    existing = db.query(User).filter(User.username == payload.username).first()
    if existing:
        raise HTTPException(
            status_code=status.HTTP_409_CONFLICT,
            detail=f"Username '{payload.username}' is already taken",
        )
    user = User(**payload.model_dump())
    db.add(user)
    db.commit()
    db.refresh(user)
    return user


@app.delete("/users/{user_id}", tags=["Users"])
def delete_user(user_id: int, db: DbSession):
    user = _get_or_404(db, User, user_id, "User")
    db.delete(user)
    db.commit()
    return {"deleted": True, "user_id": user_id}


# ---------------------------------------------------------------------------
# Posts
# ---------------------------------------------------------------------------
@app.get("/posts", response_model=list[PostOut], tags=["Posts"])
def list_posts(db: DbSession, user_id: Annotated[int | None, Query()] = None):
    query = db.query(Post)
    if user_id is not None:
        query = query.filter(Post.user_id == user_id)
    posts = query.order_by(Post.id).all()
    return [_serialize_post(post) for post in posts]


@app.get("/posts/{post_id}", response_model=PostOut, tags=["Posts"])
def get_post(post_id: int, db: DbSession):
    post = _get_or_404(db, Post, post_id, "Post")
    return _serialize_post(post)


@app.post(
    "/posts",
    response_model=PostOut,
    status_code=status.HTTP_201_CREATED,
    tags=["Posts"],
)
def create_post(payload: PostCreate, db: DbSession):
    _get_or_404(db, User, payload.user_id, "User")
    post = Post(**payload.model_dump())
    db.add(post)
    db.commit()
    db.refresh(post)
    return _serialize_post(post)


@app.put("/posts/{post_id}", response_model=PostOut, tags=["Posts"])
def update_post(post_id: int, payload: PostUpdate, db: DbSession):
    post = _get_or_404(db, Post, post_id, "Post")
    post.content = payload.content
    db.commit()
    db.refresh(post)
    return _serialize_post(post)


@app.delete("/posts/{post_id}", tags=["Posts"])
def delete_post(post_id: int, db: DbSession):
    post = _get_or_404(db, Post, post_id, "Post")
    db.delete(post)
    db.commit()
    return {"deleted": True, "post_id": post_id}


# ---------------------------------------------------------------------------
# Comments
# ---------------------------------------------------------------------------
@app.get(
    "/posts/{post_id}/comments", response_model=list[CommentOut], tags=["Comments"]
)
def list_comments(post_id: int, db: DbSession):
    _get_or_404(db, Post, post_id, "Post")
    return (
        db.query(Comment).filter(Comment.post_id == post_id).order_by(Comment.id).all()
    )


@app.post(
    "/posts/{post_id}/comments",
    response_model=CommentOut,
    status_code=status.HTTP_201_CREATED,
    tags=["Comments"],
)
def create_comment(post_id: int, payload: CommentCreate, db: DbSession):
    _get_or_404(db, Post, post_id, "Post")
    _get_or_404(db, User, payload.user_id, "User")
    comment = Comment(post_id=post_id, user_id=payload.user_id, text=payload.text)
    db.add(comment)
    db.commit()
    db.refresh(comment)
    return comment


@app.delete("/comments/{comment_id}", tags=["Comments"])
def delete_comment(comment_id: int, db: DbSession):
    comment = _get_or_404(db, Comment, comment_id, "Comment")
    db.delete(comment)
    db.commit()
    return {"deleted": True, "comment_id": comment_id}


# ---------------------------------------------------------------------------
# Likes
# ---------------------------------------------------------------------------
@app.get("/posts/{post_id}/likes", response_model=list[LikeOut], tags=["Likes"])
def list_likes(post_id: int, db: DbSession):
    _get_or_404(db, Post, post_id, "Post")
    return db.query(Like).filter(Like.post_id == post_id).order_by(Like.id).all()


@app.post(
    "/posts/{post_id}/likes",
    response_model=LikeOut,
    status_code=status.HTTP_201_CREATED,
    tags=["Likes"],
)
def create_like(post_id: int, payload: LikeCreate, db: DbSession):
    _get_or_404(db, Post, post_id, "Post")
    _get_or_404(db, User, payload.user_id, "User")

    existing = (
        db.query(Like)
        .filter(Like.post_id == post_id, Like.user_id == payload.user_id)
        .first()
    )
    if existing:
        raise HTTPException(
            status_code=status.HTTP_409_CONFLICT,
            detail=f"User {payload.user_id} has already liked post {post_id}",
        )

    like = Like(post_id=post_id, user_id=payload.user_id)
    db.add(like)
    db.commit()
    db.refresh(like)
    return like


@app.delete("/posts/{post_id}/likes", tags=["Likes"])
def delete_like(post_id: int, user_id: Annotated[int, Query()], db: DbSession):
    _get_or_404(db, Post, post_id, "Post")
    like = (
        db.query(Like).filter(Like.post_id == post_id, Like.user_id == user_id).first()
    )
    if like is None:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Like by user {user_id} on post {post_id} not found",
        )
    db.delete(like)
    db.commit()
    return {"deleted": True, "post_id": post_id, "user_id": user_id}
