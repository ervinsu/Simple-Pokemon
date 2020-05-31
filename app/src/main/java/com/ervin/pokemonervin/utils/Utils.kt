package com.ervin.pokemonervin.utils

import android.app.Activity
import android.content.Context
import com.ervin.pokemonervin.model.BaseStatus
import com.ervin.pokemonervin.model.Pokemon
import com.ervin.pokemonervin.model.Type
import com.google.gson.Gson
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

private fun loadJSONFromAsset(context: Context, jsonFile: String): String? {
    return try {
        val `is`: InputStream = context.assets.open(jsonFile)
        val size: Int = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        val charset: Charset = Charsets.UTF_8
        String(buffer, charset)
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }
}

fun Activity.loadPokemon(): List<Pokemon>? {
    val jsonStringPokemon = loadJSONFromAsset(this, "pokedex.json")
    val returnedValue = arrayListOf<Pokemon>()
    return if (jsonStringPokemon != null) {
        val jsonArray = JSONArray(jsonStringPokemon)
        val gson = Gson()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val pokemon = gson.fromJson(jsonArray.getString(i), Pokemon::class.java)
            pokemon.pokemonName =
                jsonObject.getJSONObject("name").getString("english")
            pokemon.pokemonBaseStatus = gson.fromJson(
                jsonObject.getString("base"),
                BaseStatus::class.java
            )
            val pokemonStringTypes = jsonObject.getJSONArray("type")
            val pokemonType = arrayListOf<Type>()
            for(j in 0 until pokemonStringTypes.length()){
                val type = Type(pokemonStringTypes.getString(j))
                pokemonType.add(type)
            }
            pokemon.pokemonTypes = pokemonType
            returnedValue.add(pokemon)
        }
        returnedValue
    } else {
        null
    }
}

fun convertIdPokemonToImageAssets(pokemonId: Int)=
    when {
        pokemonId<10 -> {
            "file:///android_asset/images/00$pokemonId.png"
        }
        pokemonId in 10..99 -> {
            "file:///android_asset/images/0$pokemonId.png"
        }
        else -> {
            "file:///android_asset/images/$pokemonId.png"
        }
    }
