package com.example.pokeapp.ui.main.intent

import com.example.pokeapp.data.model.AppError
import com.example.pokeapp.data.model.PokemonDetails
import com.example.pokeapp.data.model.PokemonResponse

data class MainState (
    val isLoading: Boolean = false,
    val response: PokemonResponse? = null,
    val pokemonDetailList: List<PokemonDetails>? = null,
    val error: AppError? = null,

)