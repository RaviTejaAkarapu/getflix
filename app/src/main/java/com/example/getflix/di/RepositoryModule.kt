package com.example.getflix.di

import com.example.getflix.movieList.data.repo.MovieListRepoImpl
import com.example.getflix.movieList.domain.repo.MovieListRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieListRepo(
        movieListRepoImpl: MovieListRepoImpl
    ): MovieListRepo
}