package com.ineedyourcode.githubapiapp.data.datasourse.retrofit

import com.google.gson.GsonBuilder
import com.ineedyourcode.githubapiapp.data.utils.getUnixTime
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserProfile
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserRepository
import com.ineedyourcode.githubapiapp.domain.entity.GitHubUserSearchResult
import com.ineedyourcode.githubapiapp.domain.githubapi.GitHubApi
import io.reactivex.rxjava3.core.Single
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

    override fun getMostPopularUsers(): Single<GitHubUserSearchResult> {
        return Single.create { emitter ->
            retrofit.getMostPopularUsers().enqueue(object : Callback<GitHubUserSearchResult> {
                override fun onResponse(
                    call: Call<GitHubUserSearchResult>,
                    response: Response<GitHubUserSearchResult>,
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { result ->
                            emitter.onSuccess(result)
                        }
                    } else {
                        emitter.onError(Exception(checkLimits(response)))
                    }
                }

                override fun onFailure(call: Call<GitHubUserSearchResult>, error: Throwable) {
                    emitter.onError(error)
                }
            })
        }
    }

    override fun getGitHubUser(login: String): Single<GitHubUserProfile> {
        return Single.create { emitter ->
            retrofit.getGitHubUser(login).enqueue(object : Callback<GitHubUserProfile> {
                override fun onResponse(
                    call: Call<GitHubUserProfile>,
                    response: Response<GitHubUserProfile>,
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { gitHubUserProfile ->
                            emitter.onSuccess(gitHubUserProfile)
                        }
                    } else {
                        emitter.onError(Exception(checkLimits(response)))
                    }
                }

                override fun onFailure(call: Call<GitHubUserProfile>, error: Throwable) {
                    emitter.onError(error)
                }
            })
        }
    }

    override fun getGitHubUserRepositoriesList(login: String): Single<List<GitHubUserRepository>> {
        return Single.create { emitter ->
            retrofit.getGitHubUserRepositoriesList(login)
                .enqueue(object : Callback<List<GitHubUserRepository>> {
                    override fun onResponse(
                        call: Call<List<GitHubUserRepository>>,
                        response: Response<List<GitHubUserRepository>>,
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { gitHubRepositoriesList ->
                                emitter.onSuccess(gitHubRepositoriesList)
                            }
                        } else {
                            emitter.onError(Exception(checkLimits(response)))
                        }
                    }

                    override fun onFailure(
                        call: Call<List<GitHubUserRepository>>,
                        error: Throwable,
                    ) {
                        emitter.onError(error)
                    }
                })
        }
    }

    override fun getGitHubRepository(
        repoOwnerLogin: String, repoName: String,
    ): Single<GitHubUserRepository> {
        return Single.create { emitter ->
            retrofit.getGitHubRepository(repoOwnerLogin, repoName)
                .enqueue(object : Callback<GitHubUserRepository> {
                    override fun onResponse(
                        call: Call<GitHubUserRepository>,
                        response: Response<GitHubUserRepository>,
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { gitHubRepository ->
                                emitter.onSuccess(gitHubRepository)
                            }
                        } else {
                            emitter.onError(Exception(checkLimits(response)))
                        }
                    }

                    override fun onFailure(call: Call<GitHubUserRepository>, error: Throwable) {
                        emitter.onError(error)
                    }
                })
        }
    }

    override fun searchUser(searchingRequest: String): Single<GitHubUserSearchResult> {
        return Single.create { emitter ->
            retrofit.searchUser(searchingRequest)
                .enqueue(object : Callback<GitHubUserSearchResult> {
                    override fun onResponse(
                        call: Call<GitHubUserSearchResult>,
                        response: Response<GitHubUserSearchResult>,
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { gitHubUserSearchResult ->
                                emitter.onSuccess(gitHubUserSearchResult)
                            }
                        } else {
                            emitter.onError(Exception(checkLimits(response)))
                        }
                    }

                    override fun onFailure(call: Call<GitHubUserSearchResult>, error: Throwable) {
                        emitter.onError(error)
                    }
                })
        }
    }

    private fun createConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    private fun <T> checkLimits(response: Response<T>): String {
        val limitRemaining = response.headers()["x-ratelimit-remaining"]

        val resetTime = response.headers()["x-ratelimit-reset"]?.let { epochSecond ->
            getUnixTime(epochSecond.toLong())
        }

        return if (limitRemaining != null && limitRemaining.toInt() > 0) {
            "По данному запросу нет результатов"
        } else {
            "Лимит запросов исчерпан. \nОбнуление лимитов: $resetTime"
        }
    }
}