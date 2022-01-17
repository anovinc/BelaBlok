package com.example.belablok.model

import android.net.Uri
import com.google.android.gms.tasks.Task

data class Post(
    val title: String?,
    val imageUrl: Uri
)