package com.example.getflix.movieList.data.remote.response

data class Trailer(
    val @type: String,
    val description: String,
    val embedUrl: String,
    val name: String,
    val thumbnail: Thumbnail,
    val thumbnailUrl: String,
    val uploadDate: String
)