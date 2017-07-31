package com.lnybrave.zzbook.utils

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView

import com.squareup.picasso.Picasso


@BindingAdapter("android:src")
fun setSrc(view: ImageView, bitmap: Bitmap) {
    view.setImageBitmap(bitmap)
}

@BindingAdapter("android:src")
fun setSrc(view: ImageView, resId: Int) {
    view.setImageResource(resId)
}

@BindingAdapter("bind:imageUrl", "bind:place", "bind:error")
fun loadImage(imageView: ImageView, url: String, holderDrawable: Drawable, errorDrawable: Drawable) {
    Picasso.with(imageView.context)
            .load(url)
            .placeholder(holderDrawable)
            .error(errorDrawable)
            .into(imageView)
}
