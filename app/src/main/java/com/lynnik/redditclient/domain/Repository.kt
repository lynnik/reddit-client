package com.lynnik.redditclient.domain

import com.lynnik.redditclient.data.Api
import com.lynnik.redditclient.data.RemoteDataStore
import com.lynnik.redditclient.domain.models.TopResponse
import io.reactivex.Flowable

object Repository : Api {

    private val remoteDataStore = RemoteDataStore

    override fun getTop(lastItemName: String, limit: Int): Flowable<TopResponse> =
            remoteDataStore.getTop(lastItemName, limit)
}