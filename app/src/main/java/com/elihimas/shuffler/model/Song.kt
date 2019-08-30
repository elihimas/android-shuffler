package com.elihimas.shuffler.model

import com.google.gson.annotations.SerializedName

data class Song(
    val trackName: String,
    val artistName: String,
    val artworkUrl: String,
    @SerializedName("primaryGenreName") val genreName: String
)