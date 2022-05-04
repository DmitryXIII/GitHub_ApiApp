package com.ineedyourcode.githubapiapp

import android.app.Application
import com.ineedyourcode.githubapiapp.data.repository.DataRepository
import com.ineedyourcode.githubapiapp.domain.repository.UsecaseRepository

class App : Application() {

    companion object {
        val repository: UsecaseRepository by lazy {
            DataRepository()
        }
    }
}