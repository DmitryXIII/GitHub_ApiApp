package com.ineedyourcode.githubapiapp.ui.utils

import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar

fun setInProgressStartScreenVisibility(vararg views: View) {
    for (view in views) {
        if (view is ProgressBar) {
            view.isVisible = true
        } else {
            view.visibility = View.INVISIBLE
        }
    }
}

fun setInProgressEndScreenVisibility(vararg views: View) {
    for (view in views) {
        if (view is ProgressBar) {
            view.isVisible = false
        } else {
            view.visibility = View.VISIBLE
        }
    }
}

fun showErrorSnack(view: View, message: MessageMapper) {
    when (message) {
        is MessageMapper.DirectString -> {
            Snackbar
                .make(view, message.message, Snackbar.LENGTH_SHORT)
                .show()
        }
        is MessageMapper.StringResource -> {
            Snackbar
                .make(view, view.context.getString(message.getStringResource()), Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}