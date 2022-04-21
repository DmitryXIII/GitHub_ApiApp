package com.ineedyourcode.githubapiapp.domain.entity

data class GitHubUserSearchResult(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<GitHubUserProfile>,
)