package com.example.getflix.movieList.data.remote

import com.example.getflix.movieList.data.remote.response.MovieDto
import retrofit2.http.GET

interface MovieApi {

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/movie-monk-b0t/"
        const val IMAGE_BASE_URL = "https://m.media-amazon.com/images/M/"
    }

    @GET("top250/master/top250.json")
    suspend fun getMoviesList(): List<MovieDto>
}