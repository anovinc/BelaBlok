package com.example.belablok.repositories


import android.net.Uri
import android.util.Log
import com.example.belablok.data.PrefsManager
import com.example.belablok.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random


class PostsRepository(
    storage: FirebaseStorage,
    private val database: FirebaseFirestore
) {

    private var numOfPost = 0
    private var reference = storage.reference

    suspend fun uploadImage(fileUri: Uri) {
        withContext(Dispatchers.IO) {
            suspendCoroutine<Int> { continuation ->
                database.collection("posts").whereEqualTo("num", 0).get().addOnFailureListener {
                }
                    .addOnSuccessListener {
                        for (document in it) {
                            numOfPost = Integer.parseInt(document.get("post").toString())
                            continuation.resume(numOfPost)
                        }
                    }
            }
            val userName = PrefsManager().getUser() ?: ""
            val fileName = userName.plus("@" + Random.nextInt(0, 10000).toString() + ".jpg")
            var _numOfPosts = numOfPost
            if (fileUri != null) {
                val uploadTask = reference.child("images/$fileName").putFile(fileUri)
                uploadTask.addOnFailureListener {

                }.addOnSuccessListener {
                    val url = it.storage.downloadUrl
                    while (!url.isComplete) {
                    }
                    val map = hashMapOf(
                        "id" to _numOfPosts,
                        "username" to userName,
                        "url" to url.result.toString()
                    )

                    database.collection("posts").document(_numOfPosts.toString()).set(map)
                        .addOnSuccessListener {
                            Log.i("tager", "uploadImage:uspjesno ")
                            _numOfPosts++
                            database.collection("posts").document("numOfPosts")
                                .update("post", _numOfPosts)
                        }
                }
            }
        }
    }

    suspend fun getUserPosts() = withContext(Dispatchers.IO) {
        suspendCoroutine<List<Post>> { continuation ->
            val userName = PrefsManager().getUser() ?: ""
            database.collection("posts").whereEqualTo("username", userName).get()
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
                .addOnSuccessListener {
                    val posts = arrayListOf<Post>()
                    for (document in it) {
                        posts.add(
                            Post(
                                Integer.parseInt(document.get("id").toString()),
                                document.get("username").toString(),
                                document.get("url").toString()
                            )
                        )
                    }
                    continuation.resume(posts)
                }
        }
    }

    suspend fun getAllPosts() = withContext(Dispatchers.IO) {
        suspendCoroutine<List<Post>> { continuation ->
            database.collection("posts").get()
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
                .addOnSuccessListener {
                    val posts = arrayListOf<Post>()
                    for (document in it) {
                        posts.add(
                            Post(
                                Integer.parseInt(document.get("id").toString()),
                                document.get("username").toString(),
                                document.get("url").toString()
                            )
                        )
                    }
                    continuation.resume(posts)
                }
        }
    }

    suspend fun deletePost(id: Int) {
        withContext(Dispatchers.IO) {
            database.collection("posts").document(id.toString()).delete().addOnSuccessListener {

            }
        }
    }

}
