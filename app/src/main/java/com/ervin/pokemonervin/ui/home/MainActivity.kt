package com.ervin.pokemonervin.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ervin.pokemonervin.R
import com.ervin.pokemonervin.utils.loadPokemon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadPokemon()?.let {
            val pokemons = it
            with(rvHomePokemon){
                adapter = HomeAdapter(pokemons)
                layoutManager = GridLayoutManager(this@MainActivity,2)
            }
        }

    }
}
