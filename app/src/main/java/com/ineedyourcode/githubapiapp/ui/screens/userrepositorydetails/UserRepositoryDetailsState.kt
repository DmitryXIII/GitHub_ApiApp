package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

sealed class UserRepositoryDetailsState {
    object UserRepositoryDetailsProgress : UserRepositoryDetailsState()

    data class UserRepositoryDetailsSuccess(val repository: GitHubUserRepository) :
        UserRepositoryDetailsState()

    data class UserRepositoryDetailsError(val error: MessageMapper) : UserRepositoryDetailsState()
}