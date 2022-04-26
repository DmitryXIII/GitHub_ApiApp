package com.ineedyourcode.githubapiapp.data.datasourse.retrofit

import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitGitHubApi {
    @GET("users/{user}")
    fun getGitHubUser(@Path("user") login: String): Call<GitHubUserProfile>

    @GET("search/users")
    fun searchUser(
        @Query("q") searchingRequest: String,
        @Query("per_page") usersPerPage: Int = 10,
        @Query("page") page: Int = 1,
    ): Call<GitHubUserSearchResult>

    @GET("users/{user}/repos")
    fun getGitHubUserRepositoriesList(
        @Path("user") login: String,
        @Query("per_page") usersPerPage: Int = 10,
        @Query("page") page: Int = 1,
    ): Call<List<GitHubUserRepository>>

    @GET("repos/{user}/{repoName}")
    fun getGitHubRepository(
        @Path("user") login: String,
        @Path("repoName") name: String,
    ): Call<GitHubUserRepository>

    @GET("search/users?q=followers:>20000")
    fun getMostPopularUsers(
        @Query("per_page") usersPerPage: Int = 50,
        @Query("page") page: Int = 1,
    ): Call<GitHubUserSearchResult>
}