package com.example.getflix.movieList.data.mappers

import com.example.getflix.movieList.data.local.movie.MovieEntity
import com.example.getflix.movieList.data.remote.response.MovieDto
import com.example.getflix.movieList.domain.model.Movie

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        context = context,
        type = type,
        actor = actor,
        aggregateRating = aggregateRating,
        contentRating = contentRating,
        creator = creator,
        datePublished = datePublished,
        description = description,
        director = director,
        duration = duration,
        genre = genre,
        image = image,
        keywords = keywords,
        name = name,
        trailer = trailer,
        url = url
    )
}
fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        context = context,
        type = type,
        actor = actor,
        aggregateRating = aggregateRating,
        contentRating = contentRating,
        creator = creator,
        datePublished = datePublished,
        description = description,
        director = director,
        duration = duration,
        genre = genre,
        image = image,
        keywords = keywords,
        name = name,
        trailer = trailer,
        url = url
    )
}