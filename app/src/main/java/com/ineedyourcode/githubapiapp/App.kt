package com.ineedyourcode.githubapiapp

import android.app.Application
import com.ineedyourcode.githubapiapp.data.retrofit.IRetrofitGitHubRepository
import com.ineedyourcode.githubapiapp.data.retrofit.RetrofitGitHubRepository

class App : Application() {

    companion object {
        val retrofitRepository: IRetrofitGitHubRepository by lazy {
            RetrofitGitHubRepository()
        }
    }
}