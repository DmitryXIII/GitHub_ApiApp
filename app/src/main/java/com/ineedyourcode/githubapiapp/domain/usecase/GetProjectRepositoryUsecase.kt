package com.ineedyourcode.githubapiapp.domain.usecase

import com.ineedyourcode.githubapiapp.domain.entity.UserProjectRepository
import io.reactivex.rxjava3.core.Single

interface GetProjectRepositoryUsecase {
    fun getProjectRepository(
        repoOwnerLogin: String,
        repoName: String
    ) : Single<UserProjectRepository>
}