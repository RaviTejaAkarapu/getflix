package com.example.getflix.movieList.data.repo

import com.example.getflix.movieList.data.local.movie.MovieDatabase
import com.example.getflix.movieList.data.mappers.toMovie
import com.example.getflix.movieList.data.mappers.toMovieEntity
import com.example.getflix.movieList.data.remote.MovieApi
import com.example.getflix.movieList.domain.model.Movie
import com.example.getflix.movieList.domain.repo.MovieListRepo
import com.example.getflix.movieList.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieListRepoImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) : MovieListRepo {
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean
    ): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val localMovieList = movieDatabase.movieDao.getMovieList()

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(Resource.Success(
                    data = localMovieList.map { it.toMovie() }
                ))

                emit(Resource.Loading(isLoading = false))
                return@flow
            }

            val movieListFromApi = try {
                movieApi.getMoviesList()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Error Loading Movies: IOException"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Error Loading Movies: HttpException"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Error Loading Movies: Exception"))
                return@flow
            }

            val movieEntities = movieListFromApi.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity()
                }
            }

            movieDatabase.movieDao.upsertMovieList(movieEntities)

            emit(Resource.Success(movieEntities.map { it.toMovie() }))
            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
        return flow {
            emit(Resource.Loading(true))
            val movieEntity = movieDatabase.movieDao.getMovieById(id)
            if (movieEntity != null) {
                emit(Resource.Success(movieEntity.toMovie()))
                emit(Resource.Loading(isLoading = false))
                return@flow
            }
            emit(Resource.Error("No such movie"))
            emit(Resource.Loading(isLoading = false))
        }
    }
}