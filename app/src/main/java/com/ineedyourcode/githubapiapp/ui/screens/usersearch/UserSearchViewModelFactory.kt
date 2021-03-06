package com.ineedyourcode.githubapiapp.ui.screens.usersearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ineedyourcode.githubapiapp.domain.usecase.SearchUserUsecase

class UserSearchViewModelFactory(private val repository: SearchUserUsecase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return UserSearchViewModel(repository) as T
    }
}