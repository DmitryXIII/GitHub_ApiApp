package com.ineedyourcode.githubapiapp.data.repository

import com.ineedyourcode.githubapiapp.data.datasourse.mock.MockRepository
import com.ineedyourcode.githubapiapp.data.datasourse.retrofit.RetrofitGitHubRepository
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserSearchResultDto
import com.ineedyourcode.githubapiapp.data.utils.convertGitHubSearchResultEntityToDto
import com.ineedyourcode.githubapiapp.data.utils.convertGitHubUserEntityToDto
import com.ineedyourcode.githubapiapp.data.utils.convertGitHubUserRepositoriesEntityListToDto
import com.ineedyourcode.githubapiapp.data.utils.convertGitHubUserRepositoryEntityToDto
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

private enum class DataSourceType {
    DATA_SOURCE_TYPE_MOCK,
    DATA_SOURCE_TYPE_RETROFIT,
}

class DataRepository : IDataRepository {
    private val dataSourceType: DataSourceType = DataSourceType.DATA_SOURCE_TYPE_RETROFIT

    private val dataSource = when (dataSourceType) {
        DataSourceType.DATA_SOURCE_TYPE_MOCK -> {
            MockRepository()
        }

        DataSourceType.DATA_SOURCE_TYPE_RETROFIT -> {
            RetrofitGitHubRepository()
        }
    }

    override fun searchUser(searchingRequest: String): Single<GitHubUserSearchResultDto> {
        return Single.create { emitter ->
            dataSource.searchUser(searchingRequest).subscribeBy(
                onSuccess = { emitter.onSuccess(convertGitHubSearchResultEntityToDto(it)) },
                onError = { emitter.onError(it) }
            )
        }
    }

    override fun getMostPopularUsers(): Single<GitHubUserSearchResultDto> {
        return Single.create { emitter ->
            dataSource.getMostPopularUsers().subscribeBy(
                onSuccess = { emitter.onSuccess(convertGitHubSearchResultEntityToDto(it)) },
                onError = { emitter.onError(it) }
            )
        }
    }

    override fun getGitHubUser(login: String): Single<GitHubUserProfileDto> {
        return Single.create { emitter ->
            dataSource.getGitHubUser(login).subscribeBy(
                onSuccess = { emitter.onSuccess(convertGitHubUserEntityToDto(it)) },
                onError = { emitter.onError(it) }
            )
        }
    }

    override fun getGitHubUserRepositoriesList(
        login: String,
    ): Single<List<GitHubUserRepositoryDto>> {
        return Single.create { emitter ->
            dataSource.getGitHubUserRepositoriesList(login).subscribeBy(
                onSuccess = { emitter.onSuccess(convertGitHubUserRepositoriesEntityListToDto(it)) },
                onError = { emitter.onError(it) }
            )
        }
    }

    override fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String,
    ): Single<GitHubUserRepositoryDto> {
        return Single.create { emitter ->
            dataSource.getGitHubRepository(repoOwnerLogin, repoName).subscribeBy(
                onSuccess = { emitter.onSuccess(convertGitHubUserRepositoryEntityToDto(it)) },
                onError = { emitter.onError(it) }
            )
        }
    }
}