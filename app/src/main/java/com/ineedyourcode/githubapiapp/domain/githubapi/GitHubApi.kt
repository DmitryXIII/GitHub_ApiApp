package com.ineedyourcode.githubapiapp.domain.githubapi

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfileEntity
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepositoryEntity

interface GitHubApi {
    fun getUser(login: String): GitHubUserProfileEntity
    fun searchUser(searchingRequest: String): List<GitHubUserProfileEntity>
    fun getUserRepositories(login: String): List<GitHubUserRepositoryEntity>
}