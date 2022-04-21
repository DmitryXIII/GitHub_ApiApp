package com.ineedyourcode.githubapiapp.data.dto

import com.google.gson.annotations.SerializedName

data class GitHubUserSearchResultDto(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<GitHubUserProfileDto>
)