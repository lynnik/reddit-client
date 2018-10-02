package com.lynnik.redditclient.domain.models

import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("modhash") val modhash: String?,
        @SerializedName("dist") val dist: Int?,
        @SerializedName("children") val children: List<Child?>?,
        @SerializedName("after") val after: String?,
        @SerializedName("before") val before: Any?
)