package com.example.newsapplication.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsapplication.R

/**
 * Extention to start activity
 */
fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}



/**
 * Extention Toast
 */
fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

/**
 * Glide load image
 */
fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .fallback(R.drawable.ic_error)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

}

