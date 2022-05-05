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

    override fun searchUser(
        searchingRequest: String,
    ): Single<List<UserProfile>> {
        val searchingResult = generateMockSearchResult(searchingRequest)
        return Single.create { emitter ->
            if (searchingResult.isNotEmpty()) {
                emitter.onSuccess(searchingResult)
            } else {
                emitter.onError(Exception("По данному запросу нет результатов"))
            }
        }
    }

    override fun getMostPopularUsers(): Single<List<UserProfile>> {
        val mostPopularUserSearchResult = generateMockMostPopularResult()
        return Single.create { emitter ->
            if (mostPopularUserSearchResult.isNotEmpty()) {
                emitter.onSuccess(mostPopularUserSearchResult)
            } else {
                emitter.onError(Exception("По данному запросу нет результатов"))
            }
        }
    }

    override fun getUserProfile(login: String): Single<UserProfile> {
        var isUserFound = false
        return Single.create { emitter ->
            for (user in mockUserProfileList) {
                if (user.login == login) {
                    emitter.onSuccess(user)
                    isUserFound = true
                    break
                }
            }
            if (!isUserFound) {
                emitter.onError(Exception("Пользователь не найден"))
            }
        }

    }

    override fun getUserRepositoriesList(login: String): Single<List<UserProjectRepository>> {
        val foundRepositoriesList =
            mockUserRepositoriesList.filter { repository ->
                repository.ownerLogin == login
            }

        return Single.create { emitter ->
            if (foundRepositoriesList.isNotEmpty()) {
                emitter.onSuccess(foundRepositoriesList)
            } else {
                emitter.onError(Exception("Ошибка получения данных"))
            }
        }
    }

    override fun getProjectRepository(
        repoOwnerLogin: String,
        repoName: String,
    ): Single<UserProjectRepository> {
        var isRepositoryFound = false

        return Single.create { emitter ->
            for (repository in mockUserRepositoriesList) {
                if (repository.name == repoName && repository.ownerLogin == repoOwnerLogin) {
                    emitter.onSuccess(repository)
                    isRepositoryFound = true
                    break
                }
            }

            if (!isRepositoryFound) {
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