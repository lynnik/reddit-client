package com.lynnik.redditclient.presentation.main

import androidx.lifecycle.MutableLiveData
import com.lynnik.redditclient.data.error.ErrorHandler
import com.lynnik.redditclient.domain.models.Child
import com.lynnik.redditclient.domain.models.TopResponse
import com.lynnik.redditclient.extensions.bind
import com.lynnik.redditclient.presentation.base.BaseViewModel
import com.lynnik.redditclient.util.NetworkConnectivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer

class MainViewModel : BaseViewModel() {

    val itemFromTop = MutableLiveData<TopResponse>()
    val detailedPreview = MutableLiveData<String>()
    private var lastObjectName = DEFAULT_LAST_OBJECT_NAME

    fun getTop() {
        NetworkConnectivity.getStateFlowable()
                .flatMap { repository.getTop(lastObjectName, LIMIT) }
                .doOnSubscribe { showLoading() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        Consumer { onGetTop(it) },
                        ErrorHandler {
                            hideLoading()
                            showError(it.message.toString())
                        }
                ).bind(compositeDisposable)
    }

    fun preview(item: Child) {
        val url = item.data?.url
        url?.let { detailedPreview.postValue(it) }
    }

    private fun onGetTop(response: TopResponse) {
        itemFromTop.postValue(response)

        val children = response.data?.children
        lastObjectName = children?.last()?.data?.name ?: DEFAULT_LAST_OBJECT_NAME

        hideLoading()
    }

    companion object {
        private const val DEFAULT_LAST_OBJECT_NAME = ""
        private const val LIMIT = 10
    }
}