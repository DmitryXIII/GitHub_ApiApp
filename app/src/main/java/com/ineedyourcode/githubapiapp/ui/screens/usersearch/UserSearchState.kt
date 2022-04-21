package com.ineedyourcode.githubapiapp.ui.screens.usersearch

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto

sealed class UserSearchState {
    object UserSearchProgress : UserSearchState()
    data class UserSearchSuccess(val userList: List<GitHubUserProfileDto>) : UserSearchState()
    data class UserSearchError(val error: String) : UserSearchState()
}