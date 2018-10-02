package com.lynnik.redditclient.domain.models

import com.google.gson.annotations.SerializedName

data class Gildings(
        @SerializedName("gid_1") val gid1: Int?,
        @SerializedName("gid_2") val gid2: Int?,
        @SerializedName("gid_3") val gid3: Int?
)