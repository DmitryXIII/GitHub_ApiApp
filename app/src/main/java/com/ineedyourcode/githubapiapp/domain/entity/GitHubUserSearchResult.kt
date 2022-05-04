package com.ineedyourcode.githubapiapp.domain.entity

data class GitHubUserSearchResult(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<GitHubSearchItem>,
) {
    data class GitHubSearchItem(
        val login: String,
        val avatarUrl: String,
    )
}