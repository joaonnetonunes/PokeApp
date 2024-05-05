package com.example.pokeapp.data.repository

import com.example.pokeapp.data.model.PokemonDetails
import com.example.pokeapp.data.model.PokemonResponse
import com.example.pokeapp.data.remote.ApiService
import com.example.pokeapp.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
private val api: ApiService
) : ApiRepository {

    override suspend fun getPokemons(offset: String?, limit: String?): PokemonResponse = api.getPokemons(offset, limit)

    override suspend fun getPokemonDetails(pokemon: String): PokemonDetails = api.getPokemonDetail(pokemon)
}