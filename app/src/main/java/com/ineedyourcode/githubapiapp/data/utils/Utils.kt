package com.ineedyourcode.githubapiapp.data.utils

import com.ineedyourcode.githubapiapp.data.datasourse.retrofit.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.datasourse.retrofit.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.datasourse.retrofit.dto.GitHubUserSearchResultDto
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult

fun convertGitHubUserDtoToEntity(user: GitHubUserProfileDto): GitHubUserProfile {
    return GitHubUserProfile(
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

fun convertGitHubUserRepositoryDtoToEntity(
    gitHubRepository: GitHubUserRepositoryDto,
): GitHubUserRepository {
    return GitHubUserRepository(
        gitHubRepository.id,
        gitHubRepository.name,
        gitHubRepository.private,
        gitHubRepository.htmlUrl,
        gitHubRepository.description,
        gitHubRepository.language,
        gitHubRepository.createdAt,
        gitHubRepository.pushedAt,
        gitHubRepository.updatedAt,
        GitHubUserRepository.Owner(gitHubRepository.owner.login))
}

fun convertGitHubSearchResultDtoToEntity(
    searchResult: GitHubUserSearchResultDto,
): GitHubUserSearchResult {
    return GitHubUserSearchResult(
        searchResult.totalCount,
        searchResult.incompleteResults,
        searchResult.items.map { convertGitHubSearchItemDtoToEntity(it) })
}

fun convertGitHubUserRepositoriesListDtoToEntity(
    gitHubRepositoriesListEntity: List<GitHubUserRepositoryDto>,
): List<GitHubUserRepository> {
    return gitHubRepositoriesListEntity.map { (convertGitHubUserRepositoryDtoToEntity(it)) }
}

fun convertGitHubUserProfileToSearchItem(user: GitHubUserProfile): GitHubUserSearchResult.GitHubSearchItem {
    return GitHubUserSearchResult.GitHubSearchItem(user.login, user.avatarUrl)
}

fun convertGitHubSearchItemDtoToEntity(searchItem: GitHubUserSearchResultDto.GitHubSearchItemDto): GitHubUserSearchResult.GitHubSearchItem {
    return GitHubUserSearchResult.GitHubSearchItem(searchItem.login, searchItem.avatarUrl)
}