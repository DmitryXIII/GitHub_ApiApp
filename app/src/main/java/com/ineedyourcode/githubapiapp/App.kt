package com.ineedyourcode.githubapiapp

import android.app.Application
import com.ineedyourcode.githubapiapp.data.repository.DataRepository
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi

class App : Application() {

    companion object {
        val repository: GitHubApi by lazy {
            DataRepository()
        }
    }
}