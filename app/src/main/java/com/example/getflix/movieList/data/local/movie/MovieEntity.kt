package com.example.getflix.movieList.data.local.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.getflix.movieList.data.remote.response.Actor
import com.example.getflix.movieList.data.remote.response.AggregateRating
import com.example.getflix.movieList.data.remote.response.Creator
import com.example.getflix.movieList.data.remote.response.Director
import com.example.getflix.movieList.data.remote.response.Trailer

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val context: String,
    val type: String,
    val actor: String,
//    val aggregateRating: AggregateRating,
    val contentRating: String,
    val creator: String,
    val datePublished: String,
    val description: String,
    val director: String,
    val duration: String,
    val genre: String,
    val image: String,
    val keywords: String,
    val name: String,
//    val trailer: Trailer,
    val url: String
)
