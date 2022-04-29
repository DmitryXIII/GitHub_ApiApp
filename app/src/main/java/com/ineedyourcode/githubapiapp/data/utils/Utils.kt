package com.ineedyourcode.githubapiapp.data.utils

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserSearchResultDto
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult

fun convertGitHubUserEntityToDto(user: GitHubUserProfile): GitHubUserProfileDto {
    return GitHubUserProfileDto(
        user.avatarUrl,
        user.createdAt,
        user.htmlUrl,
        user.id,
        user.login,
        user.name,
        user.publicRepos,
        user.reposUrl,
        user.updatedAt)
}

fun convertGitHubUserRepositoryEntityToDto(
    gitHubRepository: GitHubUserRepository,
): GitHubUserRepositoryDto {
    return GitHubUserRepositoryDto(
        gitHubRepository.id,
        gitHubRepository.name,
        gitHubRepository.private,
        gitHubRepository.htmlUrl,
        gitHubRepository.description,
        gitHubRepository.language,
        gitHubRepository.createdAt,
        gitHubRepository.pushedAt,
        gitHubRepository.updatedAt,
        GitHubUserRepositoryDto.Owner(gitHubRepository.owner.login))
}

fun convertGitHubSearchResultEntityToDto(searchResult: GitHubUserSearchResult): GitHubUserSearchResultDto {
    val userList = mutableListOf<GitHubUserProfileDto>()

    for (item in searchResult.items) {
        userList.add(convertGitHubUserEntityToDto(item))
    }

    return GitHubUserSearchResultDto(searchResult.totalCount,
        searchResult.incompleteResults,
        userList)
}

fun convertGitHubUserRepositoriesEntityListToDto(
    gitHubRepositoriesListEntity: List<GitHubUserRepository>,
): List<GitHubUserRepositoryDto> {
    val gitHubUserRepositoriesDtoList = mutableListOf<GitHubUserRepositoryDto>()

    for (gitHubRepositoryEntity in gitHubRepositoriesListEntity) {
        gitHubUserRepositoriesDtoList.add(convertGitHubUserRepositoryEntityToDto(
            gitHubRepositoryEntity))
    }

    return gitHubUserRepositoriesDtoList
}