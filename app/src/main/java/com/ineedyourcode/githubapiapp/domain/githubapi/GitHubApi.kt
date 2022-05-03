package com.ineedyourcode.githubapiapp.domain.githubapi

import com.ineedyourcode.githubapiapp.domain.usecase.GetGitHubRepositoryUsecase
import com.ineedyourcode.githubapiapp.domain.usecase.GetGitHubUserUsecase
import com.ineedyourcode.githubapiapp.domain.usecase.SearchGitHubUserUsecase

interface GitHubApi : SearchGitHubUserUsecase, GetGitHubUserUsecase, GetGitHubRepositoryUsecase {
}