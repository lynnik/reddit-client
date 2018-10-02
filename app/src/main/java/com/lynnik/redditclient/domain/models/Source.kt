package com.lynnik.redditclient.domain.models

import com.google.gson.annotations.SerializedName

data class Source(
        @SerializedName("url") val url: String?,
        @SerializedName("width") val width: Int?,
        @SerializedName("height") val height: Int?
)