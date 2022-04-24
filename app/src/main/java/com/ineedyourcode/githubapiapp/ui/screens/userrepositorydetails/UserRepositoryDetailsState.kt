package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

sealed class UserRepositoryDetailsState {
    object UserRepositoryDetailsProgress : UserRepositoryDetailsState()

    data class UserRepositoryDetailsSuccess(val repository: GitHubUserRepositoryDto) :
        UserRepositoryDetailsState()

    data class UserRepositoryDetailsError(val error: MessageMapper) : UserRepositoryDetailsState()
}