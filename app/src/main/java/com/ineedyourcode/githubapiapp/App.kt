package com.ineedyourcode.githubapiapp

import android.app.Application
import android.content.Context
import com.ineedyourcode.githubapiapp.di.AppDependenciesComponent
import com.ineedyourcode.githubapiapp.di.DaggerAppDependenciesComponent

class App : Application() {
    lateinit var appDependenciesComponent: AppDependenciesComponent

    override fun onCreate() {
        super.onCreate()
        appDependenciesComponent = DaggerAppDependenciesComponent
            .builder()
            .build()
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }
