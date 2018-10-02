package com.lynnik.redditclient.domain.models

import com.google.gson.annotations.SerializedName

data class Child(
        @SerializedName("kind") val kind: String?,
        @SerializedName("data") val data: ChildData?
)