package com.ineedyourcode.githubapiapp.data.datasourse.retrofit.dto

import com.google.gson.annotations.SerializedName

data class GitHubUserRepositoryDto(
    val id: String,
    val name: String,
    val private: Boolean,
    @SerializedName("html_url")
    val htmlUrl: String,
    val description: String?,
    val language: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("pushed_at")
    val pushedAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val owner: Owner,
) {
    data class Owner(
        val login: String,
    )
}