package com.lynnik.redditclient.data

import com.lynnik.redditclient.domain.models.TopResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/top.json")
    fun getTop(
            @Query("after") lastItemName: String,
            @Query("limit") limit: Int
    ): Flowable<TopResponse>
}