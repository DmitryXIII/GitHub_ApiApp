package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto

sealed class UserRepositoryDetailsState {
    object UserRepositoryDetailsProgress : UserRepositoryDetailsState()

    data class UserRepositoryDetailsSuccess(val repository: GitHubUserRepositoryDto) :
        UserRepositoryDetailsState()

    data class UserRepositoryDetailsError(val error: String) : UserRepositoryDetailsState()
}