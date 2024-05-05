package com.example.pokeapp.data.model

data class AppError(
    val message: String,
    val error: Throwable?
)
