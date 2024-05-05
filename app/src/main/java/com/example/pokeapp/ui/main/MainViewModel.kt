package com.example.pokeapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.core.util.StringUtils
import com.example.pokeapp.data.model.AppError
import com.example.pokeapp.data.model.PokemonDetails
import com.example.pokeapp.data.model.PokemonResponse
import com.example.pokeapp.domain.repository.ApiRepository
import com.example.pokeapp.ui.main.intent.MainIntent
import com.example.pokeapp.ui.main.intent.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun processIntent(intent: MainIntent){
        when(intent){
            is MainIntent.OnLoadListIntent -> fetchList(null)
            is MainIntent.onNextClicked -> fetchList(intent.next)
            is MainIntent.onPreviousClicked -> fetchList(intent.previous)
        }
    }

    private fun fetchList(url: String?){

        viewModelScope.launch {
            runCatching {
                _state.update { it.copy(isLoading = true, response = null, pokemonDetailList = null, error = null ) }

                val response: PokemonResponse = if (url.isNullOrEmpty()){
                    repository.getPokemons(null, null)
                }else{
                    repository.getPokemons(StringUtils.returnOffset(url), StringUtils.returnLimit(url))
                }
                val pokemonDetailList: List<PokemonDetails> = mutableListOf()
                response.results.forEach { pokemon ->
                 pokemonDetailList.toMutableList().add(repository.getPokemonDetails(pokemon.name))
                }

                _state.update { it.copy(isLoading = false, response = response, pokemonDetailList = pokemonDetailList, error = null) }

            }.onFailure {error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        response = null,
                        error = error.message?.let { errorMessage ->
                            AppError(
                                errorMessage,
                                error = error
                            )
                        }
                    )
                }

            }
        }

    }


}