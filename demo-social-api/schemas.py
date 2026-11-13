from datetime import datetime

from pydantic import BaseModel, ConfigDict, Field


class UserCreate(BaseModel):
    username: str = Field(..., min_length=2, max_length=50, examples=["sara_pm"])
    display_name: str = Field(..., min_length=1, max_length=100, examples=["Sara"])
    bio: str = Field(
        default="", max_length=250, examples=["Product Manager learning APIs"]
    )


class UserOut(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: int
    username: str
    display_name: str
    bio: str
    created_at: datetime


class PostCreate(BaseModel):
    user_id: int = Field(..., examples=[1])
    content: str = Field(
        ..., min_length=1, max_length=1000, examples=["Hello from my first API call!"]
    )


class PostUpdate(BaseModel):
    content: str = Field(
        ..., min_length=1, max_length=1000, examples=["Updated post content!"]
    )


class PostOut(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: int
    user_id: int
    content: str
    created_at: datetime
    likes_count: int = 0
    comments_count: int = 0


class CommentCreate(BaseModel):
    user_id: int = Field(..., examples=[2])
    text: str = Field(..., min_length=1, max_length=500, examples=["Awesome post!"])


class CommentOut(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: int
    post_id: int
    user_id: int
    text: str
    created_at: datetime


class LikeCreate(BaseModel):
    user_id: int = Field(..., examples=[2])


class LikeOut(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: int
    post_id: int
    user_id: int
    created_at: datetime
