package com.lynnik.redditclient.domain.models

import com.google.gson.annotations.SerializedName

data class Variants(
        @SerializedName("gif") val gif: Gif?,
        @SerializedName("mp4") val mp4: Mp4?
)