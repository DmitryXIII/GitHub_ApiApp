package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.data.retrofit.IRetrofitGitHubRepository
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryViewModel(
    private val retrofitRepository: IRetrofitGitHubRepository,
    private val liveData: MutableLiveData<UserRepositoryDetailsState> = MutableLiveData(),
) : ViewModel() {
    fun getLiveData(): MutableLiveData<UserRepositoryDetailsState> = liveData

    fun getGitHubRepository(owner: String, name: String) {
        liveData.postValue(UserRepositoryDetailsState.UserRepositoryDetailsProgress)
        retrofitRepository.getRepository(owner, name, object : Callback<GitHubUserRepositoryDto> {
            override fun onResponse(
                call: Call<GitHubUserRepositoryDto>,
                response: Response<GitHubUserRepositoryDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { gitHubRepository ->
                        liveData.postValue(UserRepositoryDetailsState.UserRepositoryDetailsSuccess(
                            gitHubRepository))
                    }
                } else {
                    liveData.postValue(
                        UserRepositoryDetailsState.UserRepositoryDetailsError(
                            MessageMapper.StringResource(
                                MessageMapper.ResponseState.RESPONSE_IS_EMPTY)))
                }
            }

            override fun onFailure(call: Call<GitHubUserRepositoryDto>, t: Throwable) {
                liveData.postValue(UserRepositoryDetailsState.UserRepositoryDetailsError(
                    MessageMapper.DirectString(t.message.toString())))
            }
        })
    }
}