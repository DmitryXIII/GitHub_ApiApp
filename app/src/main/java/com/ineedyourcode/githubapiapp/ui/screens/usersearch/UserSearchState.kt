package com.ineedyourcode.githubapiapp.ui.screens.usersearch

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper

sealed class UserSearchState {
    object UserSearchProgress : UserSearchState()
    data class UserSearchSuccess(val userList: List<GitHubUserSearchResult.GitHubSearchItem>) : UserSearchState()
    data class UserSearchError(val error: MessageMapper) : UserSearchState()
}