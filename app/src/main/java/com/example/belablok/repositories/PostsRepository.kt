package com.example.belablok.repositories


import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.belablok.data.PrefsManager
import com.example.belablok.model.GameRound
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

    private var numOfPost = Random.nextFloat()
    private var reference = storage.reference

    suspend fun uploadImage(fileUri: Uri, onResult: (Boolean) -> Unit) =
        withContext(Dispatchers.IO) {
            database.collection("numOfPosts").whereEqualTo("num", 0).get()
                .addOnFailureListener {
                }
                .addOnSuccessListener {
                    for (document in it) {
                        numOfPost = document.get("post").toString().toFloat()
                    }
                }


            val userName = PrefsManager().getUser() ?: ""
            val fileName = userName.plus("@" + Random.nextInt(0, 10000).toString() + ".jpg")
            if (fileUri != null) {
                val uploadTask = reference.child("images/$fileName").putFile(fileUri)
                uploadTask.addOnFailureListener {

                }.addOnSuccessListener {
                    val url = it.storage.downloadUrl
                    while (!url.isComplete) {
                    }
                    val map = hashMapOf(
                        "id" to numOfPost,
                        "username" to userName,
                        "url" to url.result.toString()
                    )

                    database.collection("posts").document(numOfPost.toString()).set(map)
                        .addOnSuccessListener {
                            onResult(true)
                            Log.i("tager", "uploadImage:uspjesno ")
                            numOfPost++
                            database.collection("numOfPosts").document("numOfPosts")
                                .update("post", numOfPost)
                        }
                        .addOnFailureListener {
                            onResult(false)
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
                                document.get("id").toString().toFloat(),
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
                                document.get("id").toString().toFloat(),
                                document.get("username").toString(),
                                document.get("url").toString()
                            )
                        )
                    }
                    continuation.resume(posts)
                }
        }
    }


    suspend fun deletePost(id: Float) {
        withContext(Dispatchers.IO) {
            database.collection("posts").document(id.toString()).delete().addOnSuccessListener {

            }
        }
    }

}