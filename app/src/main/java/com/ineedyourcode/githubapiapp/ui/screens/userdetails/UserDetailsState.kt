package com.ineedyourcode.githubapiapp.ui.screens.userdetails

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto

sealed class UserDetailsState {
    object UserDetailsProgress : UserDetailsState()
    data class UserDetailsSuccess(val user: GitHubUserProfileDto) : UserDetailsState()
    data class UserDetailsError(val error: String) : UserDetailsState()
}