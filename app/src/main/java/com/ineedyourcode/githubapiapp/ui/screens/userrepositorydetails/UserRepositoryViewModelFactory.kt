package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ineedyourcode.githubapiapp.domain.usecase.GetProjectRepositoryUsecase

class UserRepositoryViewModelFactory(private val repository: GetProjectRepositoryUsecase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return UserRepositoryViewModel(repository) as T
    }
}