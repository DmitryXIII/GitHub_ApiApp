package com.ineedyourcode.githubapiapp

import android.app.Application
import com.ineedyourcode.githubapiapp.data.retrofit.RetrofitGitHubRepository
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi

class App : Application() {

    companion object {
        val retrofitRepository: GitHubApi by lazy {
            RetrofitGitHubRepository()
        }
    }
}