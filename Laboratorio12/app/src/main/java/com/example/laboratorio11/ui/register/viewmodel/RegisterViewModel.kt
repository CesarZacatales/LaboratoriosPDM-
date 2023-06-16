package com.example.laboratorio11.ui.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.laboratorio11.RetrofitApplication
import com.example.laboratorio11.network.ApiResponse
import com.example.laboratorio11.repository.CredentialsRepository
import com.example.laboratorio11.ui.register.RegisterUiStatus
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: CredentialsRepository) : ViewModel() {
    var name = MutableLiveData("")
    var email = MutableLiveData("")
    var password = MutableLiveData("")

    private val _status = MutableLiveData<RegisterUiStatus>(RegisterUiStatus.Resume)
    val status: LiveData<RegisterUiStatus>
        get() = _status

    private fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _status.postValue(
                when (val response = repository.register(name,email,password)){
                    is ApiResponse.Error -> RegisterUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> RegisterUiStatus.ErrorWithMessage(response.message)
                    is ApiResponse.Success -> RegisterUiStatus.Success
                }
            )
        }
    }


    fun onRegister() {
        if(!validateData()){
            _status.value = RegisterUiStatus.ErrorWithMessage("Wrong information")
            return
        }
        register(name.value!!, email.value!!, password.value!!)
    }

    private fun validateData(): Boolean {
        when {
            name.value.isNullOrEmpty() -> return false
            email.value.isNullOrEmpty() -> return false
            password.value.isNullOrEmpty() -> return false
        }
        return true
    }

    fun clearStatus() {
        _status.value = RegisterUiStatus.Resume
    }

    fun clearData() {
        name.value = ""
        email.value = ""
        password.value = ""
    }

    companion object {

        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RetrofitApplication
                RegisterViewModel(app.credentialsRepository)
            }
        }
    }
}