package com.ineedyourcode.githubapiapp.data.repository

import com.ineedyourcode.githubapiapp.data.usecase.DataGetGitHubRepositoryUsecase
import com.ineedyourcode.githubapiapp.data.usecase.DataGetGitHubUserUsecase
import com.ineedyourcode.githubapiapp.data.usecase.DataSearchGitHubUserUsecase

interface IDataRepository :
    DataSearchGitHubUserUsecase,
    DataGetGitHubUserUsecase,
    DataGetGitHubRepositoryUsecase