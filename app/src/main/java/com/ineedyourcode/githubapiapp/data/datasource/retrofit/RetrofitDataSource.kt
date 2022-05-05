package com.ineedyourcode.githubapiapp.data.datasource.retrofit

import com.ineedyourcode.githubapiapp.data.datasource.retrofit.dtomapper.DtoMapper
import com.ineedyourcode.githubapiapp.domain.entity.UserProfile
import com.ineedyourcode.githubapiapp.domain.entity.UserProjectRepository
import com.ineedyourcode.githubapiapp.domain.repository.UsecaseRepository
import io.reactivex.rxjava3.core.Single

class RetrofitDataSource(
    private val retrofit: RetrofitGitHubApi,
    private val dtoMapper: DtoMapper,
) : UsecaseRepository {

    override fun getMostPopularUsers(): Single<List<UserProfile>> {
        return retrofit.getMostPopularUsers()
            .map { dtoMapper.convertGitHubSearchResultDtoToUserProfileList(it) }
    }

    override fun getUserProfile(login: String): Single<UserProfile> {
        return retrofit.getGitHubUser(login)
            .map { dtoMapper.convertGitHubUserDtoToUserProfile(it) }
    }

    override fun getUserRepositoriesList(login: String): Single<List<UserProjectRepository>> {
        return retrofit.getGitHubUserRepositoriesList(login)
            .map { dtoMapper.convertGitHubUserRepositoriesListDtoToUserProjectRepositoryList(it) }
    }

    override fun getProjectRepository(
        repoOwnerLogin: String, repoName: String,
    ): Single<UserProjectRepository> {
        return retrofit.getGitHubRepository(repoOwnerLogin, repoName)
            .map { dtoMapper.convertGitHubUserRepositoryDtoToUserProjectRepository(it) }
    }

    override fun searchUser(searchingRequest: String): Single<List<UserProfile>> {
        return retrofit.searchUser(searchingRequest)
            .map { dtoMapper.convertGitHubSearchResultDtoToUserProfileList(it) }
    }
}