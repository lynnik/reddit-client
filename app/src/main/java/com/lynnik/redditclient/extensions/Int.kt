package com.lynnik.redditclient.extensions

fun Int.toHoursAgo(): Int = ((System.currentTimeMillis() / 1000 - this) / 3600).toInt()