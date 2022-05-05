package com.ineedyourcode.githubapiapp.di

import com.ineedyourcode.githubapiapp.data.repository.DataRepository
import com.ineedyourcode.githubapiapp.domain.usecase.GetProjectRepositoryUsecase
import com.ineedyourcode.githubapiapp.domain.usecase.GetUserUsecase
import com.ineedyourcode.githubapiapp.domain.usecase.SearchUserUsecase
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.viewmodel.UserDetailsViewModel
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.viewmodel.UserGitHubRepositoryViewModel
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.viewmodel.UserSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<GetUserUsecase> { DataRepository() }
    single<SearchUserUsecase> { DataRepository() }
    single<GetProjectRepositoryUsecase> { DataRepository() }

    viewModel { UserDetailsViewModel(get()) }
    viewModel { UserSearchViewModel(get()) }
    viewModel { UserGitHubRepositoryViewModel(get()) }
}