package com.ineedyourcode.githubapiapp.data.retrofit

import com.google.gson.GsonBuilder
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserSearchResultDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

class RetrofitGitHubRepository : IRetrofitGitHubRepository {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(createConverterFactory())
            .client(createClient())
            .build().create(RetrofitGitHubApi::class.java)
    }

    override fun getUser(login: String, callback: Callback<GitHubUserProfileDto>) {
        retrofit.getUser(login).enqueue(callback)
    }

    override fun searchUsers(
        searchingRequest: String,
        callback: Callback<GitHubUserSearchResultDto>,
    ) {
        retrofit.searchUsers(searchingRequest).enqueue(callback)
    }

    override fun getUserRepositories(
        login: String,
        callback: Callback<List<GitHubUserRepositoryDto>>,
    ) {
        retrofit.getUserRepositories(login).enqueue(callback)
    }

    override fun getRepository(
        repoOwnerLogin: String,
        repoName: String,
        callback: Callback<GitHubUserRepositoryDto>,
    ) {
        retrofit.getRepository(repoOwnerLogin, repoName).enqueue(callback)
    }

    override fun getMostPopularUsers(callback: Callback<GitHubUserSearchResultDto>) {
        retrofit.getMostPopularUsers().enqueue(callback)
    }

    private fun createConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
}