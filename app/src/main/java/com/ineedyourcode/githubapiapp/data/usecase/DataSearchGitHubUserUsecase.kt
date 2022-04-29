package com.ineedyourcode.githubapiapp.data.usecase

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserSearchResultDto
import com.ineedyourcode.githubapiapp.data.repository.DataCallback

interface DataSearchGitHubUserUsecase {
    fun searchUser(
        searchingRequest: String,
        callback: DataCallback<GitHubUserSearchResultDto>,
    )

    fun getMostPopularUsers(callback: DataCallback<GitHubUserSearchResultDto>) {
    }
}