package com.ineedyourcode.githubapiapp.domain.entity

data class GitHubUserProfileEntity(
    val avatarUrl: String,
    val createdAt: String,
    val htmlUrl: String,
    val id: Int,
    val login: String,
    val name: String,
    val nodeId: String,
    val publicRepos: Int,
    val reposUrl: String,
    val updatedAt: String
)