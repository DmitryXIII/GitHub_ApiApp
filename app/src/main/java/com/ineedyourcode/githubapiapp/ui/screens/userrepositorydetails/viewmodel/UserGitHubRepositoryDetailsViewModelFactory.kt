package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ineedyourcode.githubapiapp.domain.usecase.GetGitHubRepositoryUsecase

class UserGitHubRepositoryDetailsViewModelFactory (private val repository: GetGitHubRepositoryUsecase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return UserGitHubRepositoryViewModel(repository) as T
    }
}