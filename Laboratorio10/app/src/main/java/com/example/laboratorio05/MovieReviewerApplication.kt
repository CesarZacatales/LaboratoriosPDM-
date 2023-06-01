package com.example.laboratorio05

import android.app.Application
import com.example.laboratorio05.data.MovieReviewerDatabase
import com.example.laboratorio05.repositories.ActorRepository
import com.example.laboratorio05.repositories.CastRepository
import com.example.laboratorio05.repositories.MovieRepository

class MovieReviewerApplication : Application() {
    private val database: MovieReviewerDatabase by lazy{
        MovieReviewerDatabase.newInstance(this)
    }

    val movieRepository : MovieRepository by lazy {
        MovieRepository(database.movieDao())
    }
    val actorRepository : ActorRepository by lazy{
        ActorRepository(database.actorDao())
    }

    val castRepository : CastRepository by lazy {
        CastRepository(database.castDao())
    }
}