package com.example.belablok.extensions

import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import androidx.constraintlayout.widget.ConstraintSet.INVISIBLE

inline fun View.onClick(crossinline onClick: () -> Unit) {
    this.setOnClickListener {
        onClick()
    }
}

inline fun MenuItem.invisible() {
    isVisible = false
}

inline fun View.invisble() {
    visibility = View.INVISIBLE
}

inline fun View.visible() {
    visibility = View.VISIBLE
}

