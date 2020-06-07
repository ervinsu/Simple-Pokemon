package com.ervin.pokemonervin.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.ervin.pokemonervin.R
import com.ervin.pokemonervin.model.Pokemon
import com.ervin.pokemonervin.ui.detail.DetailActivity
import com.ervin.pokemonervin.ui.detail.DetailActivity.Companion.INTENT_POKEMON
import com.ervin.pokemonervin.utils.convertIdPokemonToImageAssets
import com.ervin.pokemonervin.utils.getGradientColorPokemon
import com.ervin.pokemonervin.utils.loadImagesFromFile
import kotlinx.android.synthetic.main.pokemon_card_view.view.*

class HomeAdapter(private val listPokemon: List<Pokemon>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) {
            itemView.tv_pokemon_name.text = pokemon.pokemonName

            itemView.cv_pokemon_container.background = getGradientColorPokemon(pokemon.pokemonTypes)

            itemView.iv_pokemon_image.loadImagesFromFile(
                itemView.context,
                convertIdPokemonToImageAssets(pokemon.pokemonID)
            )

            itemView.cardViewPokemonItem.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                val pPokePicture =
                    Pair.create<View, String>(itemView.iv_pokemon_image, "pokePicture")
                val pPokeContainer =
                    Pair.create<View, String>(itemView.cardViewPokemonItem, "pokeContainer")
                val pBgDetail = Pair.create<View, String>(
                    (itemView.context as Activity).findViewById(R.id.bg_detail),
                    "bgDetail"
                )
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    itemView.context as Activity,
                    pPokeContainer,
                    pPokePicture,
                    pBgDetail
                )
                intent.putExtra(INTENT_POKEMON, pokemon)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    itemView.context.startActivity(intent, options.toBundle())
                else
                    itemView.context.startActivity(intent)
            }
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

