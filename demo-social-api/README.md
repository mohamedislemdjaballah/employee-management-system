# Social Media Demo REST API

A lightweight **FastAPI + SQLAlchemy + SQLite** demo API with four resources (**Users**, **Posts**, **Comments**, and **Likes**) for **Day 1 — Period 3: REST API Practice**.

## Quick Start

```bash
cd demo-social-api
uv sync
uv run uvicorn main:app --reload --host 0.0.0.0 --port 8000
```

Once running:
- **API Base URL:** `http://localhost:8000`
- **Interactive Swagger Docs:** `http://localhost:8000/docs`
- **Database:** `social_media.sqlite` (auto-created and seeded with 3 sample users, 3 posts, comments, and likes on first startup)

## Endpoints

| Resource | Method | Path | Description |
| :--- | :--- | :--- | :--- |
| Users | `GET` | `/users` | List all users |
| Users | `GET` | `/users/{user_id}` | Get one user |
| Users | `POST` | `/users` | Create a user |
| Users | `DELETE` | `/users/{user_id}` | Delete a user (cascades posts/comments/likes) |
| Posts | `GET` | `/posts` | List all posts (optional `?user_id=1`) |
| Posts | `GET` | `/posts/{post_id}` | Get one post |
| Posts | `POST` | `/posts` | Create a post |
| Posts | `PUT` | `/posts/{post_id}` | Update a post's content |
| Posts | `DELETE` | `/posts/{post_id}` | Delete a post |
| Comments | `GET` | `/posts/{post_id}/comments` | List comments on a post |
| Comments | `POST` | `/posts/{post_id}/comments` | Add a comment to a post |
| Comments | `DELETE` | `/comments/{comment_id}` | Delete a comment |
| Likes | `GET` | `/posts/{post_id}/likes` | List likes on a post |
| Likes | `POST` | `/posts/{post_id}/likes` | Like a post |
| Likes | `DELETE` | `/posts/{post_id}/likes?user_id=2` | Unlike a post |
