package com.ineedyourcode.githubapiapp.data.datasourse.retrofit

import com.google.gson.GsonBuilder
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    override fun getMostPopularUsers(callback: GitHubApi.GitHubCallback<GitHubUserSearchResult>) {
        retrofit.getMostPopularUsers().enqueue(object : Callback<GitHubUserSearchResult> {
            override fun onResponse(
                call: Call<GitHubUserSearchResult>,
                response: Response<GitHubUserSearchResult>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        callback.onSuccess(result)
                    }
                }
            }

            override fun onFailure(call: Call<GitHubUserSearchResult>, error: Throwable) {
                callback.onFail(error)
            }
        })
    }

    override fun getGitHubUser(
        login: String,
        callback: GitHubApi.GitHubCallback<GitHubUserProfile>,
    ) {
        retrofit.getGitHubUser(login).enqueue(object : Callback<GitHubUserProfile> {
            override fun onResponse(
                call: Call<GitHubUserProfile>,
                response: Response<GitHubUserProfile>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { gitHubUserProfile ->
                        callback.onSuccess(gitHubUserProfile)
                    }
                }
            }

            override fun onFailure(call: Call<GitHubUserProfile>, error: Throwable) {
                callback.onFail(error)
            }
        })
    }

    override fun getGitHubUserRepositoriesList(
        login: String,
        callback: GitHubApi.GitHubCallback<List<GitHubUserRepository>>,
    ) {
        retrofit.getGitHubUserRepositoriesList(login)
            .enqueue(object : Callback<List<GitHubUserRepository>> {
                override fun onResponse(
                    call: Call<List<GitHubUserRepository>>,
                    response: Response<List<GitHubUserRepository>>,
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { gitHubRepositoriesList ->
                            callback.onSuccess(gitHubRepositoriesList)
                        }
                    }
                }

                override fun onFailure(call: Call<List<GitHubUserRepository>>, error: Throwable) {
                    callback.onFail(error)
                }

            })
    }

    override fun getGitHubRepository(
        repoOwnerLogin: String,
        repoName: String,
        callback: GitHubApi.GitHubCallback<GitHubUserRepository>,
    ) {
        retrofit.getGitHubRepository(repoOwnerLogin, repoName)
            .enqueue(object : Callback<GitHubUserRepository> {
                override fun onResponse(
                    call: Call<GitHubUserRepository>,
                    response: Response<GitHubUserRepository>,
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { gitHubRepository ->
                            callback.onSuccess(gitHubRepository)
                        }
                    }
                }

                override fun onFailure(call: Call<GitHubUserRepository>, error: Throwable) {
                    callback.onFail(error)
                }
            })
    }

    override fun searchUser(
        searchingRequest: String,
        callback: GitHubApi.GitHubCallback<GitHubUserSearchResult>,
    ) {
        retrofit.searchUser(searchingRequest).enqueue(object : Callback<GitHubUserSearchResult> {
            override fun onResponse(
                call: Call<GitHubUserSearchResult>,
                response: Response<GitHubUserSearchResult>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { gitHubUserSearchResult ->
                        callback.onSuccess(gitHubUserSearchResult)
                    }
                }
            }

            override fun onFailure(call: Call<GitHubUserSearchResult>, error: Throwable) {
                callback.onFail(error)
            }
        })
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