package com.ineedyourcode.githubapiapp.domain.usecase

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi

interface GetGitHubUserUsecase {
    fun getGitHubUser(login: String, callback: GitHubApi.GitHubCallback<GitHubUserProfile>)

    fun getGitHubUserRepositoriesList(login: String, callback: GitHubApi.GitHubCallback<List<GitHubUserRepository>>)
}