package com.ineedyourcode.githubapiapp.data.usecase

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter

interface DataGetGitHubUserUsecase {
    fun getGitHubUser(login: String): Single<GitHubUserProfileDto>
    fun getGitHubUserRepositoriesList(login: String): Single<List<GitHubUserRepositoryDto>>
}