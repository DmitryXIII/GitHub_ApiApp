package com.ineedyourcode.githubapiapp.data.retrofit

import com.google.gson.GsonBuilder
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
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
        retrofit.getUser(login)
    }

    override fun searchUsers(
        searchingRequest: String,
        callback: Callback<List<GitHubUserProfileDto>>,
    ) {
        retrofit.searchUsers(searchingRequest)
    }

    override fun getUserRepositories(
        login: String,
        callback: Callback<List<GitHubUserRepositoryDto>>,
    ) {
        retrofit.getUserRepositories(login)
    }

    override fun getRepository(
        repoOwnerLogin: String,
        repoName: String,
        callback: Callback<GitHubUserRepositoryDto>,
    ) {
        retrofit.getRepository(repoOwnerLogin, repoName)
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