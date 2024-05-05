package com.example.pokeapp.domain.repository

import com.example.pokeapp.data.model.PokemonDetails
import com.example.pokeapp.data.model.PokemonResponse

interface ApiRepository {

    suspend fun getPokemons(
        offset: String?,
       limit: String?
    ): PokemonResponse

    suspend fun getPokemonDetails(
        pokemon: String,
    ): PokemonDetails
}