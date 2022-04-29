package com.ineedyourcode.githubapiapp.ui.screens.userdetails

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

sealed class UserDetailsState {
    object UserDetailsProgress : UserDetailsState()

    data class UserDetailsSuccess(val user: GitHubUserProfile) : UserDetailsState()

    data class UserRepositoriesSuccess(
        val repositoriesList: List<GitHubUserRepository>,
    ) : UserDetailsState()

    data class UserDetailsError(val error: MessageMapper) : UserDetailsState()
}