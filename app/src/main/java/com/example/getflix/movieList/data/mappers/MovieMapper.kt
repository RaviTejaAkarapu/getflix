package com.example.getflix.movieList.data.mappers

import com.example.getflix.movieList.data.local.movie.MovieEntity
import com.example.getflix.movieList.data.remote.response.Actor
import com.example.getflix.movieList.data.remote.response.Creator
import com.example.getflix.movieList.data.remote.response.Director
import com.example.getflix.movieList.data.remote.response.MovieDto
import com.example.getflix.movieList.domain.model.Movie

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        context = context,
        type = type,
//        aggregateRating = aggregateRating,
        contentRating = contentRating,
        datePublished = datePublished,
        description = description,
        duration = duration,
        image = image,
        keywords = keywords,
        name = name,
//        trailer = trailer,
        url = url,
        actor = try {
            actor.split(",").map { Actor(it) }
        } catch (e: Exception) {
            emptyList()
        },
        director = try {
            director.split(",").map { Director(it) }
        } catch (e: Exception) {
            emptyList()
        },
        creator = try {
            creator.split(",").map { Creator(it) }
        } catch (e: Exception) {
            emptyList()
        },
        genre = try {
            genre.split(",")
        } catch (e: Exception) {
            emptyList()
        }
    )
}

fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        context = context,
        type = type,
//        aggregateRating = aggregateRating,
        contentRating = contentRating,
        datePublished = datePublished,
        description = description,
        duration = duration,
        image = image,
        keywords = keywords,
        name = name,
//        trailer = trailer,
        url = url,
        actor = try {
            actor.joinToString(",") { it.name }
        } catch (e: Exception) {
            ""
        },
        director = try {
            director.joinToString(",") { it.name }
        } catch (e: Exception) {
            ""
        },
        creator = try {
            creator.joinToString(",") { it.name }
        } catch (e: Exception) {
            ""
        },
        genre = try {
            genre.joinToString(",")
        } catch (e: Exception) {
            ""
        },
    )
}