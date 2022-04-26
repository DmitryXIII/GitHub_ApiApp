package com.ineedyourcode.githubapiapp.data.mock

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi
import java.lang.NullPointerException

class MockRepository : GitHubApi {
    private val mockGitHubUserList = mutableListOf<GitHubUserProfile>()
    private val mockGitHubUserRepositoriesList = mutableListOf<GitHubUserRepository>()

    init {
        generateMockGitHubUserList()
        generateMockGitHubUserRepositoriesList()
    }

    override fun searchUser(
        searchingRequest: String,
        callback: GitHubApi.GitHubCallback<GitHubUserSearchResult>,
    ) {
        val searchingResult = generateMockSearchResult(searchingRequest)
        if (searchingResult.totalCount > 0) {
            callback.onSuccess(searchingResult)
        } else {
            callback.onFail(NullPointerException("По данному запросу нет результатов"))
        }
    }

    override fun getMostPopularUsers(callback: GitHubApi.GitHubCallback<GitHubUserSearchResult>) {
        val mostPopularUserSearchResult = generateMockMostPopularResult()
        if (mostPopularUserSearchResult.totalCount > 0) {
            callback.onSuccess(mostPopularUserSearchResult)
        } else {
            callback.onFail(NullPointerException("По данному запросу нет результатов"))
        }
    }

    override fun getGitHubUser(
        login: String,
        callback: GitHubApi.GitHubCallback<GitHubUserProfile>,
    ) {
        var isUserFound = false
        for (user in mockGitHubUserList) {
            if (user.login == login) {
                callback.onSuccess(user)
                isUserFound = true
                break
            }
        }
        if (!isUserFound) {
            callback.onFail(NullPointerException("Пользователь не найден"))
        }
    }

    override fun getGitHubUserRepositoriesList(
        login: String,
        callback: GitHubApi.GitHubCallback<List<GitHubUserRepository>>,
    ) {
        val foundRepositoriesList =
            mockGitHubUserRepositoriesList.filter { repository ->
                repository.owner.login == login
            }
        if (foundRepositoriesList.isNotEmpty()) {
            callback.onSuccess(foundRepositoriesList)
        } else {
            callback.onFail(NullPointerException("Ошибка получения данных"))
        }

    }

    override fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String,
        callback: GitHubApi.GitHubCallback<GitHubUserRepository>,
    ) {
        var isRepositoryFound = false
        for (repository in mockGitHubUserRepositoriesList) {
            if (repository.name == repoName && repository.owner.login == repoOwnerLogin) {
                callback.onSuccess(repository)
                isRepositoryFound = true
                break
            }
        }

        if (!isRepositoryFound) {
            callback.onFail(NullPointerException("Репозиторий не найден"))
        }
    }

    private fun generateMockGitHubUserList() {
        for (i in 1..10) {
            mockGitHubUserList.add(GitHubUserProfile(
                "https://avatars.githubusercontent.com/u/91154478?v=4",
                "00-00-0000",
                "https://github.com/DmitryXIII",
                i,
                "User_$i",
                "Name_$i",
                10,
                "https://api.github.com/users/DmitryXIII/repos",
                "00-00-0000"))
        }
    }

    private fun generateMockGitHubUserRepositoriesList() {
        for (i in 1..10) {
            for (j in 1..10)
                mockGitHubUserRepositoriesList.add(GitHubUserRepository(
                    i.toString(),
                    "RepoName_${i}_$j",
                    false,
                    "https://github.com/DmitryXIII/GitHub_ApiApp",
                    "Фейковый репозиторий",
                    "Kotlin",
                    "00-00-0000",
                    "00-00-0000",
                    "00-00-0000",
                    GitHubUserRepository.Owner("User_$i")))
        }
    }

    private fun generateMockSearchResult(searchingRequest: String): GitHubUserSearchResult {
        val foundUsersList =
            mockGitHubUserList.filter { user ->
                user.login.lowercase().contains(searchingRequest.lowercase())
            }
        return GitHubUserSearchResult(foundUsersList.size, false, foundUsersList)
    }

    private fun generateMockMostPopularResult(): GitHubUserSearchResult {
        val foundMostPopularUsersList = mockGitHubUserList
        return GitHubUserSearchResult(foundMostPopularUsersList.size,
            false,
            foundMostPopularUsersList)
    }
}