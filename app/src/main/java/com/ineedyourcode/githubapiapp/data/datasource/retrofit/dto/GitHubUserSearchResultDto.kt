package com.ineedyourcode.githubapiapp.data.datasource.retrofit.dto

import com.google.gson.annotations.SerializedName

data class GitHubUserSearchResultDto(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<GitHubSearchItemDto>,
    @SerializedName("total_count")
    val totalCount: Int
)