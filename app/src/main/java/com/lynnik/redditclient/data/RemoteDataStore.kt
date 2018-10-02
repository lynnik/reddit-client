package com.lynnik.redditclient.data

import com.lynnik.redditclient.domain.models.TopResponse
import io.reactivex.Flowable

object RemoteDataStore : Api {

    private val api: Api by lazy { RetrofitCreator.initApi() }

    override fun getTop(lastItemName: String, limit: Int): Flowable<TopResponse> =
            api.getTop(lastItemName, limit)
}