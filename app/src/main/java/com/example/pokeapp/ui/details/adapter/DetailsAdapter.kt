package com.example.pokeapp.ui.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.data.model.Ability

class DetailsAdapter: RecyclerView.Adapter<DetailsViewHolder>() {

    var abilitiesList: List<Ability> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder =
        DetailsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ability_item, parent, false))

    override fun getItemCount(): Int {
        return abilitiesList.size
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
       holder.tvAbility.text = abilitiesList[position].ability.name
    }
}

class DetailsViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){

    val tvAbility: TextView

    init {
        tvAbility = itemview.findViewById(R.id.tvAbility)
    }
}