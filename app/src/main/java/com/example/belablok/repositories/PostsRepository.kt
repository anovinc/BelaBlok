package com.example.belablok.repositories


import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.belablok.PrefsManager
import com.example.belablok.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random


class PostsRepository(
    storage: FirebaseStorage,
    private val database: FirebaseFirestore
) {

    private val initialList: MutableList<Post> = mutableListOf()

    private val _userPosts = MutableLiveData<List<Post>>()
    val userPosts: LiveData<List<Post>>
        get() = _userPosts

    private var reference = storage.reference

    suspend fun uploadImage(fileUri: Uri) {
        withContext(Dispatchers.IO) {
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
                        "username" to userName,
                        "url" to url.result.toString()
                    )
                    database.collection("posts").add(map).addOnSuccessListener {
                        Log.i("tager", "uploadImage:uspjesno ")
                    }
                }
            }
        }
    }

    suspend fun getImages() {
        withContext(Dispatchers.IO) {
            val userName = PrefsManager().getUser() ?: ""
            database.collection("posts").whereEqualTo("username", userName).get()
                .addOnSuccessListener {
                    initialList.clear()
                    for (document in it) {
                        initialList.add(
                            Post(
                                document.get("username").toString(),
                                document.get("url").toString()
                            )
                        )
                    }
                }
            _userPosts.postValue(initialList)
        }
        Log.i("posts", "getImages: ${userPosts.value}")
    }

}
