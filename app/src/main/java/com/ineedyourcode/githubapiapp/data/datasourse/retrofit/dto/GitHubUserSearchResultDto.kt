package com.ineedyourcode.githubapiapp.data.datasourse.retrofit.dto

import com.google.gson.annotations.SerializedName

data class GitHubUserSearchResultDto(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<GitHubSearchItemDto>,
) {
    data class GitHubSearchItemDto(
        val login: String,
        @SerializedName("avatar_url")
        val avatarUrl: String,
    )
}