package com.ineedyourcode.githubapiapp.ui.utils

import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible

fun setInProgressStartScreenVisibility (vararg views: View) {
    for (view in views) {
        if (view is ProgressBar) {
            view.isVisible = true
        } else {
            view.visibility = View.INVISIBLE
        }
    }
}

fun setInProgressEndScreenVisibility (vararg views: View) {
    for (view in views) {
        if (view is ProgressBar) {
            view.isVisible = false
        } else {
            view.visibility = View.VISIBLE
        }
    }
}