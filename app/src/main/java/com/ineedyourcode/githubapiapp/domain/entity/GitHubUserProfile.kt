package com.ineedyourcode.githubapiapp.domain.entity

import com.google.gson.annotations.SerializedName

data class GitHubUserProfile(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val id: Int,
    val login: String,
    val name: String,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("updated_at")
    val updatedAt: String,
)