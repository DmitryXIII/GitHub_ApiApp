package com.ineedyourcode.githubapiapp

import android.app.Application
import com.ineedyourcode.githubapiapp.data.DataRepository
import com.ineedyourcode.githubapiapp.data.IDataRepository

class App : Application() {

    companion object {
        val repository: IDataRepository by lazy {
            DataRepository()
        }
    }
}