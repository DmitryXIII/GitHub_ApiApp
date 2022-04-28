package com.ineedyourcode.githubapiapp.domain.usecase

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import io.reactivex.rxjava3.core.Single

interface GetGitHubUserUsecase {
    fun getGitHubUser(
        login: String
    )  : Single<GitHubUserProfile>

    fun getGitHubUserRepositoriesList(
        login: String
    )  : Single<List<GitHubUserRepository>>
}