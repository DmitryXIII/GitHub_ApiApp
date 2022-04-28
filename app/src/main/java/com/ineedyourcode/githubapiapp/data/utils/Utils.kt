package com.ineedyourcode.githubapiapp.data.utils

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserSearchResultDto
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import java.time.format.DateTimeFormatter

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

fun convertGitHubSearchResultEntityToDto(
    searchResult: GitHubUserSearchResult,
): GitHubUserSearchResultDto {
    return GitHubUserSearchResultDto(
        searchResult.totalCount,
        searchResult.incompleteResults,
        searchResult.items.map { convertGitHubUserEntityToDto(it) })
}

fun convertGitHubUserRepositoriesEntityListToDto(
    gitHubRepositoriesListEntity: List<GitHubUserRepository>,
): List<GitHubUserRepositoryDto> {
    return gitHubRepositoriesListEntity.map { (convertGitHubUserRepositoryEntityToDto(it)) }
}

fun getUnixTime(epochSecond: Long): String {
    return DateTimeFormatter.ISO_INSTANT
        .format(java.time.Instant.ofEpochSecond(epochSecond)).toString()
}