package com.ineedyourcode.githubapiapp.domain.usecase

import com.ineedyourcode.githubapiapp.domain.entity.UserProfile
import com.ineedyourcode.githubapiapp.domain.entity.UserProjectRepository
import io.reactivex.rxjava3.core.Single

interface GetUserUsecase {
    fun getUserProfile(
        login: String
    )  : Single<UserProfile>

    fun getUserRepositoriesList(
        login: String
    )  : Single<List<UserProjectRepository>>
}