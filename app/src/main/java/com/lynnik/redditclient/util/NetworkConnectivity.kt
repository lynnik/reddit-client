package com.lynnik.redditclient.util

import com.lynnik.redditclient.App
import com.lynnik.redditclient.data.error.NoInternetConnectionException
import com.lynnik.redditclient.extensions.hasNetworkConnection
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

object NetworkConnectivity {

    fun getStateFlowable(): Flowable<Boolean> = Flowable.create({ emitter ->
        if (App.applicationContext().hasNetworkConnection()) {
            emitter.onNext(true)
        } else {
            emitter.onError(NoInternetConnectionException())
        }
        emitter.onComplete()
    }, BackpressureStrategy.LATEST)
}