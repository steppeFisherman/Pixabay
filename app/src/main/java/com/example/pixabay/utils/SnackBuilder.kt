package com.example.pixabay.utils

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.example.pixabay.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

interface SnackBuilder {

    fun buildSnackIndefinite(view: View): Snackbar

    class Base : SnackBuilder {

        override fun buildSnackIndefinite(view: View): Snackbar {
            val snack = Snackbar.make(
                view,
                R.string.check_internet_connection,
                Snackbar.LENGTH_INDEFINITE
            )
            val layoutParams = FrameLayout.LayoutParams(snack.view.layoutParams)
            layoutParams.gravity = Gravity.BOTTOM
            snack.view.setPadding(0, 0, 0, 0)
            snack.view.setBackgroundResource(R.drawable.snack_background_shape)
            snack.view.layoutParams = layoutParams
            snack.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
            return snack
        }
    }
}