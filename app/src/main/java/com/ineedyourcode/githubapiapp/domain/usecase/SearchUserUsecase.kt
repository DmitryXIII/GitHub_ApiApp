package com.ineedyourcode.githubapiapp.domain.usecase

import com.ineedyourcode.githubapiapp.domain.entity.UserProfile
import io.reactivex.rxjava3.core.Single

interface SearchUserUsecase {
    fun searchUser(
        searchingRequest: String,
    ): Single<List<UserProfile>>

    fun getMostPopularUsers(): Single<List<UserProfile>>
}