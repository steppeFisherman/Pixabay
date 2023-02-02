package com.example.pixabay.utils

import android.app.Application
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.IOException

open class MyBitmap(private val context: Context) : CustomTarget<Bitmap>() {
    override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
        try {
            val wallpaperManager = WallpaperManager.getInstance(context)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                val screenWidth = context.resources.displayMetrics.widthPixels
                val screenHeight = context.resources.displayMetrics.heightPixels

                val start = Point(0, 0)
                val end = Point(bitmap.width, bitmap.height)

                if (bitmap.width > screenWidth) {
                    start.x = (bitmap.width - screenWidth) / 2
                    end.x = start.x + screenWidth
                }

                if (bitmap.height > screenHeight) {
                    start.y = (bitmap.height - screenHeight) / 2
                    end.y = start.y + screenHeight
                }

                wallpaperManager.setBitmap(
                    bitmap,
                    Rect(start.x, start.y, end.x, end.y),
                    false
                )
            } else {
                wallpaperManager.setBitmap(bitmap)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onLoadCleared(placeholder: Drawable?) {}
}
