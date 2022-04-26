package com.ineedyourcode.githubapiapp.ui.screens.userdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ineedyourcode.githubapiapp.data.usecase.DataGetGitHubUserUsecase

class UserDetailsViewModelFactory (private val repository: DataGetGitHubUserUsecase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return UserDetailsViewModel(repository) as T
    }
}