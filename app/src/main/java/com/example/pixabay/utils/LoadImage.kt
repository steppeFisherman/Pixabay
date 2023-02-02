package com.example.pixabay.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.pixabay.R

interface LoadImage {

    fun load(context: Context, imageView: ImageView, url: String)

    class Base : LoadImage {
        override fun load(context: Context, imageView: ImageView, url: String) {
            Glide.with(context)
                .load(url)
                .apply(RequestOptions())
                .placeholder(R.drawable.holder)
                .error(R.drawable.holder)
                .fallback(R.drawable.holder)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .dontAnimate()
                .into(imageView)
        }
    }
}






