package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

sealed class UserGitHubRepositoryDetailsState {
    object UserGitHubRepositoryDetailsProgress : UserGitHubRepositoryDetailsState()

    data class UserGitHubRepositoryDetailsSuccess(val repository: GitHubUserRepository) :
        UserGitHubRepositoryDetailsState()

    data class UserGitHubRepositoryDetailsError(val error: MessageMapper) : UserGitHubRepositoryDetailsState()
}