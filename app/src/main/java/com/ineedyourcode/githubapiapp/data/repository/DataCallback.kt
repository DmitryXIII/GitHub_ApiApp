package com.ineedyourcode.githubapiapp.data.repository

interface DataCallback<T> {
    fun onSuccess(result: T)
    fun onFail(error: Throwable)
}