package com.ineedyourcode.githubapiapp.domain.entity

data class UserProfile(
    val id: String,
    val login: String,
    val name: String,
    val avatar: String,
    val registrationDate: String,
    val url: String,
    val publicRepos: Int,
)