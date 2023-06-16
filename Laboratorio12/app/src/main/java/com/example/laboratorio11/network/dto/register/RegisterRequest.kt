package com.example.laboratorio11.network.dto.register


data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)