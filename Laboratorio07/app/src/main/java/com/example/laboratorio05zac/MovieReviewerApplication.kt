package com.example.laboratorio05zac

import android.app.Application
import com.example.laboratorio05zac.data.movies
import com.example.laboratorio05zac.repository.MovieRepository

class MovieReviewerApplication: Application() {
    val movieRepository: MovieRepository by lazy {
        MovieRepository(movies)
    }
}