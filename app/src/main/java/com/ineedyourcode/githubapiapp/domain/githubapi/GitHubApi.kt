package com.ineedyourcode.githubapiapp.domain.githubapi

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult

interface GitHubApi {
    fun getUser(login: String): GitHubUserProfile
    fun searchUser(searchingRequest: String): GitHubUserSearchResult
    fun getUserRepositories(login: String): List<GitHubUserRepository>
    fun getRepository(repoOwnerLogin: String, repoName: String): GitHubUserRepository
}