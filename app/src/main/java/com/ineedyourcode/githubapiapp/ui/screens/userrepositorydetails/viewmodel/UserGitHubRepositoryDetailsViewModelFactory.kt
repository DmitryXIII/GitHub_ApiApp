package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi

class UserGitHubRepositoryDetailsViewModelFactory (private val retrofitRepository: GitHubApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return UserGitHubRepositoryViewModel(retrofitRepository) as T
    }
}