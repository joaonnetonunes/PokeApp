package com.example.pokeapp.ui.details

import android.os.Build
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pokeapp.data.model.PokemonDetails
import com.example.pokeapp.databinding.ActivityDetailsBinding
import com.example.pokeapp.ui.details.adapter.DetailsAdapter

class DetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }

    private val adapter = DetailsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.main)
        configureView()
    }

    private fun configureView(){
        val pokemonDetails = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.intent.getSerializableExtra("pokemon", PokemonDetails::class.java)
        } else {
            this.intent.getSerializableExtra("pokemon")
        } as PokemonDetails

        adapter.abilitiesList = pokemonDetails.abilities

        with(binding){
            Glide.with(this@DetailsActivity).load(pokemonDetails.sprites.front_default).into(ivPokemonFront)
            Glide.with(this@DetailsActivity).load(pokemonDetails.sprites.back_default).into(ivPokemonBack)

            tvPokemonName.text = pokemonDetails.name

            rvAbilities.adapter = adapter
            rvAbilities.layoutManager = LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)

        }
    }

    private fun handleBackPressed() {
        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(OnBackInvokedDispatcher.PRIORITY_DEFAULT) {
                exitOnBackPressed()
            }
        } else {
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    exitOnBackPressed()
                }
            })
        }
    }

    private fun exitOnBackPressed() {
        finish()
    }
}