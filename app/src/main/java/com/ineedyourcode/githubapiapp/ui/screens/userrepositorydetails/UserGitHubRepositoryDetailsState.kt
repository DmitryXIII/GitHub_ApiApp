package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails

import com.ineedyourcode.githubapiapp.domain.entity.UserProjectRepository
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

sealed class UserGitHubRepositoryDetailsState {
    object UserGitHubRepositoryDetailsProgress : UserGitHubRepositoryDetailsState()

    data class UserGitHubRepositoryDetailsSuccess(val repository: UserProjectRepository) :
        UserGitHubRepositoryDetailsState()

    data class UserGitHubRepositoryDetailsError(
        val error: MessageMapper,
    ) : UserGitHubRepositoryDetailsState()
}