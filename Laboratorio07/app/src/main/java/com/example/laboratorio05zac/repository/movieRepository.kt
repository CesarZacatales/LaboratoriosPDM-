package com.example.laboratorio05zac.repository

import com.example.laboratorio05zac.data.model.MovieModel

class MovieRepository(private val movies: MutableList<MovieModel>) {

    fun getMovies()= movies

    fun addMovies(movie: MovieModel) = movies.add(movie)
}