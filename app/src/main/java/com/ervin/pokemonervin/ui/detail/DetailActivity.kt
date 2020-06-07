package com.ervin.pokemonervin.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ervin.pokemonervin.R
import com.ervin.pokemonervin.model.Pokemon
import com.ervin.pokemonervin.utils.convertIdPokemonToImageAssets
import com.ervin.pokemonervin.utils.getGradientColorPokemon
import com.ervin.pokemonervin.utils.loadImagesFromFile
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            supportStartPostponedEnterTransition()
            val fade = Fade()
            setSupportActionBar(detail_toolbar)
            fade.excludeTarget(iv_poke_picture_detail, true)
            fade.excludeTarget(poke_container, true)
            fade.excludeTarget(bg_detail, true)
            window.enterTransition = fade
            window.exitTransition = fade
        }

        val pokemon = intent.getParcelableExtra<Pokemon>(INTENT_POKEMON)!!

        val pokemonGradientColor = getGradientColorPokemon(pokemon.pokemonTypes)

        supportActionBar?.title = pokemon.pokemonName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detail_toolbar.background = pokemonGradientColor

        poke_container.background = pokemonGradientColor

        iv_poke_picture_detail.loadImagesFromFile(
            this@DetailActivity,
            convertIdPokemonToImageAssets(pokemon.pokemonID)
        )

        poke_id.text =
            String.format(resources.getString(R.string.detail_id_pokemon), pokemon.pokemonID)
        tv_type_name1.text = pokemon.pokemonTypes[0].typeName
        tv_type_name1.background = ColorDrawable(Color.parseColor(pokemon.pokemonTypes[0].color))

        if (pokemon.pokemonTypes.size > 1) {
            tv_type_name2.text = pokemon.pokemonTypes[1].typeName
            tv_type_name2.background =
                ColorDrawable(Color.parseColor(pokemon.pokemonTypes[1].color))
        } else {
            tv_type_name2.visibility = View.GONE
        }
        //set all status
        progressbar_hp.progress = pokemon.pokemonBaseStatus.hp
        tv_poke_hp.text = pokemon.pokemonBaseStatus.hp.toString()

        progressbar_defense.progress = pokemon.pokemonBaseStatus.defense
        tv_poke_defense.text = pokemon.pokemonBaseStatus.defense.toString()

        progressbar_attack.progress = pokemon.pokemonBaseStatus.attack
        tv_poke_attack.text = pokemon.pokemonBaseStatus.attack.toString()

        progressbar_speed.progress = pokemon.pokemonBaseStatus.speed
        tv_poke_speed.text = pokemon.pokemonBaseStatus.speed.toString()

        progressbar_sp_attack.progress = pokemon.pokemonBaseStatus.spAttack
        tv_poke_sp_attack.text = pokemon.pokemonBaseStatus.spAttack.toString()

        progressbar_sp_defense.progress = pokemon.pokemonBaseStatus.spDefense
        tv_poke_sp_defense.text = pokemon.pokemonBaseStatus.spDefense.toString()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    companion object {
        const val INTENT_POKEMON = "Intent_Pokemon"
    }
}
