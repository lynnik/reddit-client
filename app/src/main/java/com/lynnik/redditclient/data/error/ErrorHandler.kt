package com.lynnik.redditclient.data.error

import androidx.annotation.StringRes
import com.lynnik.redditclient.App
import com.lynnik.redditclient.BuildConfig
import com.lynnik.redditclient.R
import io.reactivex.exceptions.CompositeException
import io.reactivex.functions.Consumer
import timber.log.Timber
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandler(
        private val onFailure: ((CommonThrowable) -> Unit)? = null
) : Consumer<Throwable> {

    override fun accept(throwable: Throwable) {
        if (BuildConfig.DEBUG) Timber.e(throwable)

        val error = when (throwable) {
            is SocketException, is UnknownHostException, is SocketTimeoutException -> {
                CommonThrowable(getString(R.string.error_handler_server_error))
            }
            is CompositeException -> {
                throwable.exceptions.forEach { Timber.e(it) }
                CommonThrowable(getString(R.string.error_handler_unknown_error))
            }
            is NoInternetConnectionException -> CommonThrowable(throwable.message!!)
            else -> CommonThrowable(getString(R.string.error_handler_unknown_error))
        }

        onFailure?.invoke(error)
    }

    private fun getString(@StringRes resId: Int) = App.applicationContext().getString(resId)
}