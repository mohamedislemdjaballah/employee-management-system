# Day 1 — Buggy SPA Challenge Quiz (Votense Ready)

> **App URL:** `http://<VPS_IP>:8000/`  
> **Swagger Docs:** `http://<VPS_IP>:8000/docs`  
> **Votense Type:** `Multiple choice` *(One right answer)*  
> **Instructions:** Open the MiniSocial SPA, try each action, inspect the failed red request via **Right-click → Inspect → Network**, and answer in **Votense**:

---

**1. When you click "Publish Post", it fails with `405 Method Not Allowed`. Why?**
- A) The database is offline
- B) **The SPA sent `PUT /posts` instead of `POST /posts`** ✅
- C) The post text was too short
- D) The browser blocked JavaScript

**2. When you click "View Author Profile", it fails with `404 Not Found`. What is wrong with the URL?**
- A) It used `https://` instead of `http://`
- B) **It requested `/user/{id}` (singular) instead of `/users/{id}` (plural)** ✅
- C) It forgot the port `:8000`
- D) It used `DELETE` instead of `GET`

**3. When you click "Comment", it fails with `422 Unprocessable Entity`. What did the SPA send wrong in the JSON body?**
- A) **It sent `"message"` instead of `"text"` (check `/docs`!)** ✅
- B) It sent XML instead of JSON
- C) It forgot `Content-Type`
- D) It sent `user_id` as a boolean

**4. When Bob (#2) clicks "Like" on Post #1 (which he already liked), what 3-digit HTTP Status Code does the server return?**
- A) `200 OK`
- B) `404 Not Found`
- C) **`409 Conflict`** ✅ *(a user can only like a post once)*
- D) `500 Internal Server Error`
