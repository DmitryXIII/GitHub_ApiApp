package com.ineedyourcode.githubapiapp.domain.entity

import com.google.gson.annotations.SerializedName

data class GitHubUserSearchResult(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<GitHubUserProfile>
)