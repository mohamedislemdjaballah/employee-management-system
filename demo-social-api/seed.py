from models import Comment, Like, Post, User
from sqlalchemy.orm import Session


def seed_if_empty(db: Session) -> None:
    """Populate initial demo data on first startup when the database is empty."""
    if db.query(User).first() is not None:
        return

    alice = User(username="alice", display_name="Alice Martin", bio="Product Designer")
    bob = User(username="bob", display_name="Bob Chen", bio="Operations Lead")
    sara = User(username="sara", display_name="Sara Haddad", bio="Project Manager")
    karim = User(username="karim", display_name="Karim Benali", bio="Data Analyst")
    lina = User(username="lina", display_name="Lina Torres", bio="Marketing Specialist")
    omar = User(username="omar", display_name="Omar Farooq", bio="QA Tester")
    db.add_all([alice, bob, sara, karim, lina, omar])
    db.flush()

    post_1 = Post(
        user_id=alice.id, content="Excited to learn how REST APIs work today!"
    )
    post_2 = Post(user_id=bob.id, content="Just sent my first GET request in Postman.")
    post_3 = Post(user_id=sara.id, content="Coffee + JSON = great morning.")
    post_4 = Post(
        user_id=karim.id, content="Filtering posts with ?user_id=1 is so clean!"
    )
    db.add_all([post_1, post_2, post_3, post_4])
    db.flush()

    db.add_all(
        [
            Comment(
                post_id=post_1.id, user_id=bob.id, text="Welcome to the API workshop!"
            ),
            Comment(
                post_id=post_1.id,
                user_id=sara.id,
                text="Wait until you try curl and Python.",
            ),
            Comment(post_id=post_2.id, user_id=alice.id, text="200 OK feels great!"),
            Comment(
                post_id=post_3.id, user_id=karim.id, text="Best way to start Day 1."
            ),
            Comment(
                post_id=post_4.id,
                user_id=sara.id,
                text="Query parameters are super handy.",
            ),
        ]
    )

    db.add_all(
        [
            Like(post_id=post_1.id, user_id=bob.id),
            Like(post_id=post_1.id, user_id=sara.id),
            Like(post_id=post_1.id, user_id=karim.id),
            Like(post_id=post_2.id, user_id=alice.id),
            Like(post_id=post_3.id, user_id=bob.id),
            Like(post_id=post_4.id, user_id=sara.id),
        ]
    )

    db.commit()
