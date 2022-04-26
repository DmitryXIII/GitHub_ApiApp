package com.ineedyourcode.githubapiapp.data

import com.ineedyourcode.githubapiapp.data.usecase.DataGetGitHubRepositoryUsecase
import com.ineedyourcode.githubapiapp.data.usecase.DataGetGitHubUserUsecase
import com.ineedyourcode.githubapiapp.data.usecase.DataSearchGitHubUserUsecase
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi

interface IDataRepository : GitHubApi, DataSearchGitHubUserUsecase, DataGetGitHubUserUsecase, DataGetGitHubRepositoryUsecase {
}