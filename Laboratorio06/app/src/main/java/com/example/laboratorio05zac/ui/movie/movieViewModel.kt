package com.example.laboratorio05zac.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.laboratorio05zac.MovieReviewerApplication
import com.example.laboratorio05zac.data.model.MovieModel
import com.example.laboratorio05zac.repository.MovieRepository

class movieViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getMovies() = repository.getMovies()

    fun addMovies(movie: MovieModel) = repository.addMovies(movie)

    companion object{
        val factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as MovieReviewerApplication
                movieViewModel(app.movieRepository)
            }
        }
    }
}

