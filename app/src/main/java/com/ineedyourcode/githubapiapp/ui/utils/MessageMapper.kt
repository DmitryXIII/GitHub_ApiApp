package com.ineedyourcode.githubapiapp.ui.utils

import com.ineedyourcode.githubapiapp.R

sealed class MessageMapper {
    class DirectString(val message: String) : MessageMapper()

    class StringResource(private val message: ResponseState) : MessageMapper() {
        fun getStringResource(): Int {
            return when (message) {
                ResponseState.RESPONSE_IS_EMPTY -> {
                    R.string.response_is_empty
                }
            }
        }
    }

    enum class ResponseState {
        RESPONSE_IS_EMPTY,
    }
}