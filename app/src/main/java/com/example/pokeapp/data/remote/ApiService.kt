package com.example.pokeapp.data.remote

import com.example.pokeapp.data.model.PokemonDetails
import com.example.pokeapp.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: String?,
        @Query("limit") limit: String?

    ): PokemonResponse

    @GET("pokemon/{pokemon}")
    suspend fun getPokemonDetail(
        @Path("pokemon") pokemon: String,
    ): PokemonDetails

}