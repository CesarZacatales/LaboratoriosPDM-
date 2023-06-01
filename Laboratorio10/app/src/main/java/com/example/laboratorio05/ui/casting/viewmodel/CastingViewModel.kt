package com.example.laboratorio05.ui.casting.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.laboratorio05.MovieReviewerApplication
import com.example.laboratorio05.data.model.ActorModel
import com.example.laboratorio05.data.model.CastModel
import com.example.laboratorio05.data.model.MovieModel
import com.example.laboratorio05.repositories.CastRepository
import kotlinx.coroutines.launch

class CastingViewModel(private val repository: CastRepository): ViewModel() {
    var movieId = MutableLiveData("")
    var actorId = MutableLiveData("")
    var status = MutableLiveData("")

    fun selectMovie(movie: MovieModel) {
        movieId.value = movie.movieID.toString()
    }

    fun selectActor(actor: ActorModel) {
        actorId.value = actor.actorID.toString()
    }

    private fun addCasting(casting: CastModel) {
        viewModelScope.launch {
            repository.addCasting(casting)
        }
    }

    fun createCasting() {
        if (!validateData()) {
            status.value = WRONG_INFORMATION
            return
        }

        val casting = CastModel(
            movieID = movieId.value?.toInt()!!,
            actorID = actorId.value?.toInt()!!,
        )

        addCasting(casting)
        clearData()

        status.value = CAST_CREATED
    }

    private fun validateData(): Boolean {
        when {
            movieId.value.isNullOrEmpty() -> return false
            actorId.value.isNullOrEmpty() -> return false
            movieId.value!!.toInt() == 0 -> return false
            actorId.value!!.toInt() == 0 -> return false
        }
        return true
    }

    private fun clearData() {
        movieId.value = ""
        movieId.value = ""
    }

    fun clearStatus() {
        status.value = INACTIVE
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as MovieReviewerApplication
                CastingViewModel(app.castRepository)
            }
        }

        const val CAST_CREATED = "Cast created"
        const val WRONG_INFORMATION = "Wrong information"
        const val INACTIVE = ""
    }
}