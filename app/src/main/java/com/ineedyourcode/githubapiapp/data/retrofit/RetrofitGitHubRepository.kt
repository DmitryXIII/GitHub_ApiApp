package com.ineedyourcode.githubapiapp.data.retrofit

import com.google.gson.GsonBuilder
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

class RetrofitGitHubRepository : GitHubApi {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(createConverterFactory())
            .client(createClient())
            .build().create(RetrofitGitHubApi::class.java)
    }

    override fun getUser(login: String, callback: Callback<GitHubUserProfile>) {
        retrofit.getUser(login).enqueue(callback)
    }

    override fun searchUsers(searchingRequest: String, callback: Callback<GitHubUserSearchResult>) {
        retrofit.searchUsers(searchingRequest).enqueue(callback)
    }

    override fun getUserGitHubRepositories(
        login: String,
        callback: Callback<List<GitHubUserRepository>>,
    ) {
        retrofit.getUserGitHubRepositories(login).enqueue(callback)
    }

    override fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String,
        callback: Callback<GitHubUserRepository>,
    ) {
        retrofit.getGitHubRepository(repoOwnerLogin, repoName).enqueue(callback)
    }

    override fun getMostPopularUsers(callback: Callback<GitHubUserSearchResult>) {
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