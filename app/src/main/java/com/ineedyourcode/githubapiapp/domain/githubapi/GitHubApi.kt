package com.ineedyourcode.githubapiapp.domain.githubapi

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository

interface GitHubApi {
    fun getUser(login: String): GitHubUserProfile
    fun searchUser(searchingRequest: String): List<GitHubUserProfile>
    fun getUserRepositories(login: String): List<GitHubUserRepository>
}