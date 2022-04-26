package com.ineedyourcode.githubapiapp.ui.screens.usersearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ineedyourcode.githubapiapp.data.usecase.DataSearchGitHubUserUsecase

class UserSearchViewModelFactory (private val repository: DataSearchGitHubUserUsecase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return UserSearchViewModel(repository) as T
    }
}