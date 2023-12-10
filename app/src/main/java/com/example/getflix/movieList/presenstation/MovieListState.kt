package com.example.getflix.movieList.presenstation

import com.example.getflix.movieList.domain.model.Movie
import com.example.getflix.movieList.util.Resource

data class MovieListState(
    val isLoading: Boolean = false,
    val movieList: List<Movie> = emptyList()
)
