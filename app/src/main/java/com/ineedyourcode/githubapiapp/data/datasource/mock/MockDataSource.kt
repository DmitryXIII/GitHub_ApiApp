package com.ineedyourcode.githubapiapp.data.datasource.mock

import com.ineedyourcode.githubapiapp.domain.entity.UserProfile
import com.ineedyourcode.githubapiapp.domain.entity.UserProjectRepository
import com.ineedyourcode.githubapiapp.domain.repository.UsecaseRepository
import io.reactivex.rxjava3.core.Single

class MockDataSource : UsecaseRepository {
    private val mockUserProfileList = mutableListOf<UserProfile>()
    private val mockUserRepositoriesList = mutableListOf<UserProjectRepository>()

    init {
        generateMockGitHubUserList()
        generateMockGitHubUserRepositoriesList()
    }

    override fun searchUser(searchingRequest: String): Single<List<UserProfile>> {
        return Single.just(generateMockSearchResult(searchingRequest))
    }

    override fun getMostPopularUsers(): Single<List<UserProfile>> {
        return Single.just(generateMockMostPopularResult())
    }

    override fun getUserProfile(login: String): Single<UserProfile> {
        return Single.create { emitter ->
            val user: UserProfile? = mockUserProfileList.find { user -> user.login == login }
            if (user != null) {
                emitter.onSuccess(user)
            } else {
                emitter.onError(Exception("Пользователь не найден"))
            }
        }
    }

    override fun getUserRepositoriesList(login: String): Single<List<UserProjectRepository>> {
        return Single.just(mockUserRepositoriesList.filter { repository ->
            repository.ownerLogin == login
        })
    }

    override fun getProjectRepository(
        repoOwnerLogin: String,
        repoName: String,
    ): Single<UserProjectRepository> {
        return Single.create { emitter ->
            val repository: UserProjectRepository? = mockUserRepositoriesList.find { repository ->
                repository.ownerLogin == repoOwnerLogin && repository.name == repoName
            }
            if (repository != null) {
                emitter.onSuccess(repository)
            } else {
                emitter.onError(Exception("Репозиторий не найден"))
            }
        }
    }

    private fun generateMockGitHubUserList() {
        for (i in 1..10) {
            mockUserProfileList.add(UserProfile(
                id = i.toString(),
                login = "User_$i",
                name = "Name_$i",
                avatar = "https://avatars.githubusercontent.com/u/91154478?v=4",
                registrationDate = "00-00-0000",
                url = "https://github.com/DmitryXIII",
                publicRepos = 10
            ))
        }
    }

    private fun generateMockGitHubUserRepositoriesList() {
        for (i in 1..10) {
            for (j in 1..10)
                mockUserRepositoriesList.add(UserProjectRepository(
                    id = i.toString(),
                    name = "RepoName_${i}_$j",
                    url = "https://github.com/DmitryXIII/GitHub_ApiApp",
                    description = "Фейковый репозиторий",
                    language = "Kotlin",
                    createDate = "00-00-0000",
                    ownerLogin = "User_$i",
                ))
        }
    }

    private fun generateMockSearchResult(searchingRequest: String): List<UserProfile> {
        return mockUserProfileList.filter { user ->
            user.login.lowercase().contains(searchingRequest.lowercase())
        }
    }

    private fun generateMockMostPopularResult() = mockUserProfileList
}