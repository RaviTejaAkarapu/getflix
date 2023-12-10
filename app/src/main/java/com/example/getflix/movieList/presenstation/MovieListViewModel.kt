package com.example.getflix.movieList.presenstation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getflix.movieList.domain.repo.MovieListRepo
import com.example.getflix.movieList.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListRepo: MovieListRepo
) : ViewModel() {
    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    init {
        getMovieList(false)
    }

    fun onEvent(event: MovieListUiEvent) {
        when (event) {
            MovieListUiEvent.Navigate -> {
                _movieListState.update { it }
            }

            is MovieListUiEvent.Paginate -> {
                getMovieList(true)
            }
        }
    }

    private fun getMovieList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }

            movieListRepo.getMovieList(forceFetchFromRemote)
                .collectLatest { result ->
                    when (result) {
                        is Resource.Error -> {
                            _movieListState.update {
                                it.copy(isLoading = false)
                            }
                        }

                        is Resource.Success -> {
                            result.data?.let { movieList ->
                                _movieListState.update {
                                    it.copy(
                                        movieList = movieList.shuffled() // shuffling to check UI
                                    )
                                }
                            }
                        }

                        is Resource.Loading -> {
                            _movieListState.update {
                                it.copy(isLoading = result.isLoading)
                            }
                        }
                    }
                }
        }
    }
}