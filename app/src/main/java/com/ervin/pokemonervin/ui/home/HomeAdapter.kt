package com.ervin.pokemonervin.ui.home

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ervin.pokemonervin.R
import com.ervin.pokemonervin.model.Pokemon
import com.ervin.pokemonervin.utils.convertIdPokemonToImageAssets
import kotlinx.android.synthetic.main.pokemon_card_view.view.*
import kotlin.math.log

class HomeAdapter(private val listPokemon: List<Pokemon>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) {
            itemView.tv_pokemon_name.text = pokemon.pokemonName

            val arrayColorTypes = IntArray(2)
            for (i in pokemon.pokemonTypes.indices) {
                arrayColorTypes[i] = Color.parseColor(pokemon.pokemonTypes[i].color)
                if (pokemon.pokemonTypes.size == 1){
                    arrayColorTypes[1] = Color.parseColor(pokemon.pokemonTypes[0].color)
                }
            }

            val gd = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, arrayColorTypes)
            gd.cornerRadius = 0f
            itemView.cv_pokemon_container.background = gd
            val a = convertIdPokemonToImageAssets(pokemon.pokemonID)
            Glide.with(itemView.context)
                .load(Uri.parse(a))
                .into(itemView.iv_pokemon_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pokemon_card_view, parent, false)
        )

    override fun getItemCount() = listPokemon.size


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(listPokemon[position])
    }
}

