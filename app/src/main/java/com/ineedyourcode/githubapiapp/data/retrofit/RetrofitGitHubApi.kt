package com.ineedyourcode.githubapiapp.data.retrofit

import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserSearchResultDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitGitHubApi {
    @GET("users/{user}")
    fun getUser(@Path("user") login: String): Call<GitHubUserProfileDto>

    @GET("search/users")
    fun searchUsers(
        @Query("q") searchingRequest: String,
        @Query("per_page") usersPerPage: Int = 10,
        @Query("page") page: Int = 1,
    ): Call<GitHubUserSearchResultDto>

    @GET("users/{user}/repos")
    fun getUserRepositories(
        @Path("user") login: String,
        @Query("per_page") usersPerPage: Int = 10,
        @Query("page") page: Int = 1,
    ): Call<List<GitHubUserRepositoryDto>>

    @GET("repos/{user}/{repoName}")
    fun getRepository(
        @Path("user") login: String,
        @Path("repoName") name: String,
    ): Call<GitHubUserRepositoryDto>

    @GET("search/users?q=followers:>20000")
    fun getMostPopularUsers(
        @Query("per_page") usersPerPage: Int = 50,
        @Query("page") page: Int = 1,
    ): Call<GitHubUserSearchResultDto>
}