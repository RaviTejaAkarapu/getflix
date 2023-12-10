package com.example.getflix.movieList.data.remote.response

import androidx.room.PrimaryKey

data class MovieDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val context: String,
    val type: String,
    val actor: List<Actor>,
    val aggregateRating: AggregateRating,
    val contentRating: String,
    val creator: List<Creator>,
    val datePublished: String,
    val description: String,
    val director: List<Director>,
    val duration: String,
    val genre: List<String>,
    val image: String,
    val keywords: String,
    val name: String,
    val trailer: Trailer,
    val url: String
)