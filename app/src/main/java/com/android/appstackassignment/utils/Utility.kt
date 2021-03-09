package com.android.appstackassignment.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast

fun showToast(context: Context, message: String) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    toast.setGravity(Gravity.BOTTOM, 0, 0)
    toast.show()
}

fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}