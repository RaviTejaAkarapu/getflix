package com.example.getflix.movieList.domain.repo

import com.example.getflix.movieList.domain.model.Movie
import com.example.getflix.movieList.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepo {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
//        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}