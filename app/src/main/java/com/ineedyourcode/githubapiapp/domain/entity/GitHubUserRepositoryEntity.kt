package com.ineedyourcode.githubapiapp.domain.entity

data class GitHubUserRepositoryEntity(
    val id: String,
    val name: String,
    val private: Boolean,
    val htmlUrl: String,
    val description: String,
    val language: String,
    val createdAt: String,
    val pushedAt: String,
    val updatedAt: String
)