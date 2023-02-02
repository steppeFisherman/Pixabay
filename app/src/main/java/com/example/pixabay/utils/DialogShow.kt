package com.example.pixabay.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.pixabay.R

interface DialogShow {

    fun show(context: Context)

    class Base : DialogShow {
        override fun show(context: Context) {
            AlertDialog.Builder(context)
                .setTitle(R.string.generic_exception_message)
                .setMessage(R.string.try_vpn_and_quit)
                .create()
                .show()
        }
    }
}