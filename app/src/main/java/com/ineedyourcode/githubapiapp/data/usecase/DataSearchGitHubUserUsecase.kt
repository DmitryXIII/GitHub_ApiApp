package com.ineedyourcode.githubapiapp.data.usecase

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserSearchResultDto
import io.reactivex.rxjava3.core.Single

interface DataSearchGitHubUserUsecase {
    fun searchUser(searchingRequest: String): Single<GitHubUserSearchResultDto>
    fun getMostPopularUsers(): Single<GitHubUserSearchResultDto>
}