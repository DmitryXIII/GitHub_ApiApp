package com.ineedyourcode.githubapiapp.data.repository

import com.ineedyourcode.githubapiapp.domain.entity.UserProfile
import com.ineedyourcode.githubapiapp.domain.entity.UserProjectRepository
import com.ineedyourcode.githubapiapp.domain.repository.UsecaseRepository
import io.reactivex.rxjava3.core.Single

class DataRepository(private val dataSource: UsecaseRepository) : UsecaseRepository {

    override fun searchUser(searchingRequest: String): Single<List<UserProfile>> {
        return dataSource.searchUser(searchingRequest)
    }

    override fun getMostPopularUsers(): Single<List<UserProfile>> {
        return dataSource.getMostPopularUsers()
    }

    override fun getUserProfile(login: String): Single<UserProfile> {
        return dataSource.getUserProfile(login)
    }

    override fun getUserRepositoriesList(
        login: String,
    ): Single<List<UserProjectRepository>> {
        return dataSource.getUserRepositoriesList(login)
    }

    override fun getProjectRepository(
        repoOwnerLogin: String,
        repoName: String,
    ): Single<UserProjectRepository> {
        return dataSource.getProjectRepository(repoOwnerLogin, repoName)
    }
}