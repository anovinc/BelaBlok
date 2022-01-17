package com.example.belablok.repositories


import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.belablok.PrefsManager
import com.example.belablok.model.Post
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.random.Random


class PostsRepository(private val storage: FirebaseStorage) {

    private val initialList: MutableList<Post> = mutableListOf()
    private val _userPosts = MutableLiveData<List<Post>>(initialList)
    val userPosts: LiveData<List<Post>>
        get() = _userPosts
    private val userName = PrefsManager().getUser()


    suspend fun uploadImage(fileUri: Uri) {
        withContext(Dispatchers.IO) {
            val fileName = userName.plus("@" + Random.nextInt(0, 10000).toString() + ".jpg")
            if (fileUri != null) {

                var uploadTask = storage.reference.child("images/$fileName").putFile(fileUri)

                uploadTask.addOnFailureListener {

                }.addOnSuccessListener {

                }
            }
        }
    }




}