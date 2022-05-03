package com.ineedyourcode.githubapiapp.data.datasourse.retrofit

import com.google.gson.GsonBuilder
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

class RetrofitGitHubRepository : GitHubApi {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(createConverterFactory())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(createClient())
            .build().create(RetrofitGitHubApi::class.java)
    }

    override fun getMostPopularUsers(): Single<GitHubUserSearchResult> {
        return retrofit.getMostPopularUsers()
    }

    override fun getGitHubUser(login: String): Single<GitHubUserProfile> {
        return retrofit.getGitHubUser(login)
    }

    override fun getGitHubUserRepositoriesList(login: String): Single<List<GitHubUserRepository>> {
        return retrofit.getGitHubUserRepositoriesList(login)
    }

    override fun getGitHubRepository(
        repoOwnerLogin: String, repoName: String,
    ): Single<GitHubUserRepository> {
        return retrofit.getGitHubRepository(repoOwnerLogin, repoName)
    }

    override fun searchUser(searchingRequest: String): Single<GitHubUserSearchResult> {
        return retrofit.searchUser(searchingRequest)
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