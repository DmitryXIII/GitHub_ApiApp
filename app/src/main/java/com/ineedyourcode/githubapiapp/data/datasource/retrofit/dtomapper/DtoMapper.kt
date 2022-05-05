package com.ineedyourcode.githubapiapp.data.datasource.retrofit.dtomapper

import com.ineedyourcode.githubapiapp.data.datasource.retrofit.dto.GitHubSearchItemDto
import com.ineedyourcode.githubapiapp.data.datasource.retrofit.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.datasource.retrofit.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.datasource.retrofit.dto.GitHubUserSearchResultDto
import com.ineedyourcode.githubapiapp.domain.entity.UserProfile
import com.ineedyourcode.githubapiapp.domain.entity.UserProjectRepository

private const val DEFAULT_DESCRIPTION_VALUE = "Описание не добавлено"
private const val DEFAULT_LANGUAGE_VALUE = "Язык программирования не указан"
private const val N_D_VALUE = "N/D"
private const val DEFAULT_PUBLIC_REPOS_VALUE = -1

class DtoMapper {
    fun convertGitHubUserDtoToUserProfile(user: GitHubUserProfileDto): UserProfile {
        return UserProfile(
            id = user.id.toString(),
            login = user.login,
            name = user.name,
            avatar = user.avatarUrl,
            registrationDate = user.createdAt,
            url = user.htmlUrl,
            publicRepos = user.publicRepos,
        )
    }

    fun convertGitHubUserRepositoryDtoToUserProjectRepository(
        gitHubRepository: GitHubUserRepositoryDto,
    ): UserProjectRepository {
        return UserProjectRepository(
            id = gitHubRepository.id.toString(),
            name = gitHubRepository.name,
            url = gitHubRepository.htmlUrl,
            description = gitHubRepository.description ?: DEFAULT_DESCRIPTION_VALUE,
            language = gitHubRepository.language ?: DEFAULT_LANGUAGE_VALUE,
            createDate = gitHubRepository.createdAt,
            ownerLogin = gitHubRepository.owner.login,
        )
    }

    fun convertGitHubSearchResultDtoToUserProfileList(
        searchResult: GitHubUserSearchResultDto,
    ): List<UserProfile> {
        return searchResult.items.map {
            convertGitHubSearchItemDtoToUserProfile(it)
        }
    }

    fun convertGitHubUserRepositoriesListDtoToUserProjectRepositoryList(
        gitHubRepositoriesListEntity: List<GitHubUserRepositoryDto>,
    ): List<UserProjectRepository> {
        return gitHubRepositoriesListEntity.map {
            (convertGitHubUserRepositoryDtoToUserProjectRepository(it))
        }
    }

    private fun convertGitHubSearchItemDtoToUserProfile(
        searchItem: GitHubSearchItemDto,
    ): UserProfile {
        return UserProfile(
            id = searchItem.id.toString(),
            login = searchItem.login,
            name = N_D_VALUE,
            avatar = searchItem.avatarUrl,
            registrationDate = N_D_VALUE,
            url = searchItem.htmlUrl,
            publicRepos = DEFAULT_PUBLIC_REPOS_VALUE,
        )
    }
}