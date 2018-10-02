package com.lynnik.redditclient.data.error

import com.lynnik.redditclient.App
import com.lynnik.redditclient.R

class NoInternetConnectionException
    : Exception(App.applicationContext().getString(R.string.exception_no_internet_connection))