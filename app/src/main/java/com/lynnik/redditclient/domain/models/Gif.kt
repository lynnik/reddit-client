package com.lynnik.redditclient.domain.models

import com.google.gson.annotations.SerializedName

data class Gif(
        @SerializedName("source") val source: Source?,
        @SerializedName("resolutions") val resolutions: List<Resolution?>?
)