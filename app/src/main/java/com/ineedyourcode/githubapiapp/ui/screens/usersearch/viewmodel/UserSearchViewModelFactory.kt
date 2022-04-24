package com.ineedyourcode.githubapiapp.ui.screens.usersearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi

class UserSearchViewModelFactory (private val retrofitRepository: GitHubApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return UserSearchViewModel(retrofitRepository) as T
    }
}