package com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.githubapiapp.domain.usecase.GetProjectRepositoryUsecase
import com.ineedyourcode.githubapiapp.ui.utils.MessageMapper
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class UserRepositoryViewModel(
    private val repository: GetProjectRepositoryUsecase,
) : ViewModel() {

    private val liveData: MutableLiveData<UserRepositoryDetailsState> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    fun getLiveData(): MutableLiveData<UserRepositoryDetailsState> = liveData

    fun getGitHubRepository(owner: String, name: String) {
        liveData.postValue(UserRepositoryDetailsState.UserRepositoryDetailsProgress)
        compositeDisposable.add(repository.getProjectRepository(owner, name).subscribeBy(
            onSuccess = { gitHubRepository ->
                if (gitHubRepository.id.isNotEmpty()) {
                    liveData.postValue(
                        UserRepositoryDetailsState.UserRepositoryDetailsSuccess(
                            gitHubRepository))
                } else {
                    liveData.postValue(
                        UserRepositoryDetailsState.UserRepositoryDetailsError(
                            MessageMapper.StringResource(
                                MessageMapper.ResponseState.RESPONSE_IS_EMPTY)))
                }
            },
            onError = {
                liveData.postValue(
                    UserRepositoryDetailsState.UserRepositoryDetailsError(
                        MessageMapper.DirectString(it.message.toString())))
            }
        ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}