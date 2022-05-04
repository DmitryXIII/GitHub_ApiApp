package com.ineedyourcode.githubapiapp.domain.entity

data class UserProjectRepository(
    val id: String,
    val name: String,
    val url: String,
    val description: String,
    val language: String,
    val createDate: String,
    val ownerLogin: String,
)