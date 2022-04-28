package com.ineedyourcode.githubapiapp.data.datasourse.mock

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi
import io.reactivex.rxjava3.core.Single

class MockRepository : GitHubApi {
    private val mockGitHubUserList = mutableListOf<GitHubUserProfile>()
    private val mockGitHubUserRepositoriesList = mutableListOf<GitHubUserRepository>()

    init {
        generateMockGitHubUserList()
        generateMockGitHubUserRepositoriesList()
    }

    override fun searchUser(
        searchingRequest: String): Single<GitHubUserSearchResult> {
        val searchingResult = generateMockSearchResult(searchingRequest)
        return Single.create { emitter ->
            if (searchingResult.totalCount > 0) {
                emitter.onSuccess(searchingResult)
            } else {
                emitter.onError(NullPointerException("По данному запросу нет результатов"))
            }
        }
    }

    override fun getMostPopularUsers(): Single<GitHubUserSearchResult> {
        val mostPopularUserSearchResult = generateMockMostPopularResult()
        return Single.create {emitter ->
            if (mostPopularUserSearchResult.totalCount > 0) {
                emitter.onSuccess(mostPopularUserSearchResult)
            } else {
                emitter.onError(NullPointerException("По данному запросу нет результатов"))
            }
        }
    }

    override fun getGitHubUser(login: String): Single<GitHubUserProfile> {
        var isUserFound = false
      return  Single.create { emitter ->
          for (user in mockGitHubUserList) {
              if (user.login == login) {
                  emitter.onSuccess(user)
                  isUserFound = true
                  break
              }
          }
          if (!isUserFound) {
              emitter.onError(NullPointerException("Пользователь не найден"))
          }
      }

    }

    override fun getGitHubUserRepositoriesList(login: String): Single<List<GitHubUserRepository>> {
        val foundRepositoriesList =
            mockGitHubUserRepositoriesList.filter { repository ->
                repository.owner.login == login
            }

        return Single.create { emitter ->
            if (foundRepositoriesList.isNotEmpty()) {
                emitter.onSuccess(foundRepositoriesList)
            } else {
                emitter.onError(NullPointerException("Ошибка получения данных"))
            }
        }
    }

    override fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String): Single<GitHubUserRepository> {
        var isRepositoryFound = false

        return Single.create { emitter ->
            for (repository in mockGitHubUserRepositoriesList) {
                if (repository.name == repoName && repository.owner.login == repoOwnerLogin) {
                    emitter.onSuccess(repository)
                    isRepositoryFound = true
                    break
                }
            }

            if (!isRepositoryFound) {
                emitter.onError(NullPointerException("Репозиторий не найден"))
            }
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