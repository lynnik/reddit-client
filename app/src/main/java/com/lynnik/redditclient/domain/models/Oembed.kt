package com.lynnik.redditclient.domain.models

import com.google.gson.annotations.SerializedName

data class Oembed(
        @SerializedName("provider_url") val providerUrl: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("type") val type: String?,
        @SerializedName("thumbnail_width") val thumbnailWidth: Int?,
        @SerializedName("height") val height: Int?,
        @SerializedName("width") val width: Int?,
        @SerializedName("html") val html: String?,
        @SerializedName("version") val version: String?,
        @SerializedName("provider_name") val providerName: String?,
        @SerializedName("thumbnail_url") val thumbnailUrl: String?,
        @SerializedName("thumbnail_height") val thumbnailHeight: Int?
)