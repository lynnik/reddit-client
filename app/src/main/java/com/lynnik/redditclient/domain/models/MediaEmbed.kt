package com.lynnik.redditclient.domain.models

import com.google.gson.annotations.SerializedName

data class MediaEmbed(
        @SerializedName("content") val content: String?,
        @SerializedName("width") val width: Int?,
        @SerializedName("scrolling") val scrolling: Boolean?,
        @SerializedName("height") val height: Int?
)