package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

sealed class UserGitHubRepositoryDetailsState {
    object UserGitHubRepositoryDetailsProgress : UserGitHubRepositoryDetailsState()

    data class UserGitHubRepositoryDetailsSuccess(val repository: GitHubUserRepositoryDto) :
        UserGitHubRepositoryDetailsState()

    data class UserGitHubRepositoryDetailsError(val error: MessageMapper) : UserGitHubRepositoryDetailsState()
}