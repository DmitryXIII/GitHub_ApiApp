package com.ineedyourcode.githubapiapp.di

import com.ineedyourcode.githubapiapp.ui.screens.userdetails.UserDetailsFragment
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.UserRepositoryDetailsFragment
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.UserSearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RepositoriesDependenciesModule::class,
    RetrofitDependenciesModule::class
])
interface AppDependenciesComponent {
    fun inject(userSearchFragment: UserSearchFragment)
    fun inject(userDetailsFragment: UserDetailsFragment)
    fun inject(userRepositoryDetailsFragment: UserRepositoryDetailsFragment)
}