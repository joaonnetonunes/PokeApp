package com.example.pokeapp.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pokeapp.R
import com.example.pokeapp.data.model.PokemonDetails
import com.example.pokeapp.databinding.ActivtyMainBinding
import com.example.pokeapp.ui.details.DetailsActivity
import com.example.pokeapp.ui.main.adapter.MainAdapter
import com.example.pokeapp.ui.main.intent.MainIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private val binding by lazy {
        ActivtyMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    private val adapter by lazy {
        MainAdapter(
            onItemClick = {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("pokemon", pokemonDetailsList[it])
                startActivity(intent)
            }

        )
    }

    private lateinit var pokemonDetailsList: List<PokemonDetails>
    private  var next: String? = null
    private  var previous: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.tbMain))
        collectState()
        setUpView()
        handleBackPressed()
    }

    private fun collectState(){
        lifecycleScope.launch {
            viewModel.state.collect{ state->
                binding.swpRefresh.isRefreshing = state.isLoading

                state.response?.let {
                    adapter.pokemonList += it.results

                    next = it.next
                    previous = it.previous

                    binding.flFoward.visibility =
                        if (it.next.isNullOrEmpty()){
                        View.VISIBLE
                        } else{
                        View.GONE
                    }

                    binding.flBack.visibility =
                        if (it.previous.isNullOrEmpty()){
                        View.VISIBLE
                        } else{
                        View.GONE
                    }
                }

                state.pokemonDetailList?.let {pokemonDetails ->
                    val imageUrls: List<String> = mutableListOf()
                    pokemonDetails.forEach {
                        imageUrls.toMutableList().add(it.sprites.front_default)
                    }
                    pokemonDetailsList = pokemonDetails
                    adapter.pokemonImageUrls = adapter.pokemonImageUrls?.plus(imageUrls)
                }

            }
        }
    }

    private fun setUpView(){
        with(binding){
            swpRefresh.setOnRefreshListener(this@MainActivity)
            rvPokeGrid.adapter = adapter
            rvPokeGrid.layoutManager = GridLayoutManager(this@MainActivity, 3)
        }
    }

    override fun onClick(v: View?) {
        when(v){
            binding.flFoward -> {
                next?.let {
                    MainIntent.onNextClicked(it)
                }?.let { nextValue ->
                    viewModel.processIntent(nextValue)
                }
            }

            binding.flBack -> {
                previous?.let {
                    MainIntent.onPreviousClicked(it)
                }?.let {
                    previousValue ->
                    viewModel.processIntent(previousValue)
                }
            }
        }
    }

    override fun onRefresh() {
        val hasNext = !next.isNullOrEmpty()
        val hasPrevious = !previous.isNullOrEmpty()
        viewModel.processIntent(
            MainIntent.OnLoadListIntent(hasNext, hasPrevious)
        )
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