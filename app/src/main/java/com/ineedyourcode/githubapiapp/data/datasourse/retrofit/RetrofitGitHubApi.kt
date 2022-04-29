package com.ineedyourcode.githubapiapp.data.datasourse.retrofit

import com.ineedyourcode.githubapiapp.data.datasourse.retrofit.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.datasourse.retrofit.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.datasourse.retrofit.dto.GitHubUserSearchResultDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitGitHubApi {
    @GET("users/{user}")
    fun getGitHubUser(@Path("user") login: String): Single<GitHubUserProfileDto>

    @GET("search/users")
    fun searchUser(
        @Query("q") searchingRequest: String,
        @Query("per_page") usersPerPage: Int = 10,
        @Query("page") page: Int = 1,
    ): Single<GitHubUserSearchResultDto>

    @GET("users/{user}/repos")
    fun getGitHubUserRepositoriesList(
        @Path("user") login: String,
        @Query("per_page") usersPerPage: Int = 10,
        @Query("page") page: Int = 1,
    ): Single<List<GitHubUserRepositoryDto>>

    @GET("repos/{user}/{repoName}")
    fun getGitHubRepository(
        @Path("user") login: String,
        @Path("repoName") name: String,
    ): Single<GitHubUserRepositoryDto>

    @GET("search/users?q=followers:>20000")
    fun getMostPopularUsers(
        @Query("per_page") usersPerPage: Int = 50,
        @Query("page") page: Int = 1,
    ): Single<GitHubUserSearchResultDto>
}