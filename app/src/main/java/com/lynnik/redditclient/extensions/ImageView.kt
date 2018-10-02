package com.lynnik.redditclient.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

const val DEFAULT_THUMBNAIL_URL = "https://www.yourfirstpatient.com" +
        "/assets" +
        "/default-user-avatar-thumbnail-" +
        "117c4e02d0bee9a424842ebb2a903cf71468999f79ce30bad99c07f100764064" +
        ".png"

fun ImageView.loadFromUrl(imageUrl: String) {
    val url = if (imageUrl == "default") DEFAULT_THUMBNAIL_URL else imageUrl
    Glide.with(this).load(url).into(this)
}