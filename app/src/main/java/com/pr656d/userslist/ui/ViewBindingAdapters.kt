package com.pr656d.userslist.ui

import android.net.Uri
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.pr656d.userslist.R
import com.squareup.picasso.Picasso

/**
 * Load image into [imageView] from the [imageUri].
 */
@BindingAdapter(value = ["imageUri"], requireAll = false)
fun imageUri(imageView: ImageView, imageUri: Uri?) {
    when (imageUri) {
        null -> {
            imageView.isVisible = false
        }
        else -> {
            imageView.isVisible = true
            Picasso.get()
                .load(imageUri)
                .placeholder(R.drawable.image_placeholder)
                .into(imageView)
        }
    }
}

/**
 * Load image into [imageView] from the [imageUrl].
 */
@BindingAdapter(value = ["imageUrl"], requireAll = false)
fun imageUrl(imageView: ImageView, imageUrl: String?) {
    imageUri(imageView, imageUrl?.toUri())
}