package com.lynnik.redditclient.domain.models

import com.google.gson.annotations.SerializedName

data class Preview(
        @SerializedName("images") val images: List<Image?>?,
        @SerializedName("reddit_video_preview") val redditVideoPreview: RedditVideoPreview?,
        @SerializedName("enabled") val enabled: Boolean?
)