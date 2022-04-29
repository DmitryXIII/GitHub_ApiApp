package com.ineedyourcode.githubapiapp.domain.githubapi

import com.ineedyourcode.githubapiapp.domain.usecase.GetGitHubRepositoryUsecase
import com.ineedyourcode.githubapiapp.domain.usecase.GetGitHubUserUsecase
import com.ineedyourcode.githubapiapp.domain.usecase.SearchGitHubUserUsecase

interface GitHubApi : SearchGitHubUserUsecase, GetGitHubUserUsecase, GetGitHubRepositoryUsecase {
    interface GitHubCallback<T> {
        fun onSuccess(result: T)
        fun onFail(error: Throwable)
    }
}