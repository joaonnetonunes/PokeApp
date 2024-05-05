package com.example.pokeapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapp.R
import com.example.pokeapp.data.model.Result

class MainAdapter(val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<MainViewHolder>() {

    var pokemonList: List<Result> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var pokemonImageUrls: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))


    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.tvPokeName.text = pokemonList[position].name

        val pokemonImageUrl = pokemonImageUrls?.get(position)
        if (!pokemonImageUrl.isNullOrEmpty()){
            Glide.with(holder.itemView).load(pokemonImageUrl).into(holder.ivPokePicture)
        }else{
            holder.ivPokePicture.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.ic_no_preview
                )
            )
        }

        holder.itemView.setOnClickListener{
            onItemClick(position)
        }

    }
}

class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val ivPokePicture: ImageView
    val tvPokeName: TextView

    init {
        ivPokePicture = itemView.findViewById(R.id.ivPokePicture)
        tvPokeName = itemView.findViewById(R.id.tvPokeName)
    }
}