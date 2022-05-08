package com.ineedyourcode.githubapiapp.di

import com.ineedyourcode.githubapiapp.ui.screens.userdetails.UserDetailsFragment
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.UserRepositoryDetailsFragment
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.UserSearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppDependenciesModule::class,
    RetrofitDependenciesModule::class
])
interface AppDependenciesComponent {
    fun inject1(userSearchFragment: UserSearchFragment)
    fun inject2(userDetailsFragment: UserDetailsFragment)
    fun inject3(userRepositoryDetailsFragment: UserRepositoryDetailsFragment)
}