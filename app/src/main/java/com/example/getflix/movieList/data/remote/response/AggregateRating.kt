package com.example.getflix.movieList.data.remote.response

data class AggregateRating(
    val @type: String,
    val bestRating: String,
    val ratingCount: Int,
    val ratingValue: Double,
    val worstRating: String
)