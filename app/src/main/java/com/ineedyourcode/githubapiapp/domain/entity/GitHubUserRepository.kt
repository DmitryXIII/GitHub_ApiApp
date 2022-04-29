package com.ineedyourcode.githubapiapp.domain.entity

import com.google.gson.annotations.SerializedName

data class GitHubUserRepository(
    val id: String,
    val name: String,
    val private: Boolean,
    @SerializedName("html_url")
    val htmlUrl: String,
    val description: String,
    val language: String,
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