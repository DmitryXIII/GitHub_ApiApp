package com.ineedyourcode.githubapiapp.data.datasourse.retrofit

import com.google.gson.GsonBuilder
import com.ineedyourcode.githubapiapp.data.datasourse.retrofit.dtomapper.DtoMapper
import com.ineedyourcode.githubapiapp.domain.entity.UserProfile
import com.ineedyourcode.githubapiapp.domain.entity.UserProjectRepository
import com.ineedyourcode.githubapiapp.domain.repository.UsecaseRepository
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

class RetrofitDataSource : UsecaseRepository {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(createConverterFactory())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(createClient())
            .build().create(RetrofitGitHubApi::class.java)
    }

    private val mapper = DtoMapper()

    override fun getMostPopularUsers(): Single<List<UserProfile>> {
        return retrofit.getMostPopularUsers()
            .map { mapper.convertGitHubSearchResultDtoToUserProfileList(it) }
    }

    override fun getUserProfile(login: String): Single<UserProfile> {
        return retrofit.getGitHubUser(login)
            .map { mapper.convertGitHubUserDtoToUserProfile(it) }
    }

    override fun getUserRepositoriesList(login: String): Single<List<UserProjectRepository>> {
        return retrofit.getGitHubUserRepositoriesList(login)
            .map { mapper.convertGitHubUserRepositoriesListDtoToUserProjectRepositoryList(it) }
    }

    override fun getProjectRepository(
        repoOwnerLogin: String, repoName: String,
    ): Single<UserProjectRepository> {
        return retrofit.getGitHubRepository(repoOwnerLogin, repoName)
            .map { mapper.convertGitHubUserRepositoryDtoToUserProjectRepository(it) }
    }

    override fun searchUser(searchingRequest: String): Single<List<UserProfile>> {
        return retrofit.searchUser(searchingRequest)
            .map { mapper.convertGitHubSearchResultDtoToUserProfileList(it) }
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