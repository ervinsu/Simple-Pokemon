package com.ervin.pokemonervin.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
            "file:///android_asset/sprites/00${pokemonId}MS.png"
        }
        pokemonId in 10..99 -> {
            "file:///android_asset/sprites/0${pokemonId}MS.png"
        }
        else -> {
            "file:///android_asset/sprites/${pokemonId}MS.png"
        }
    }

fun ImageView.loadImagesFromFile(context: Context, filePath: String) {
    Glide.with(context)
        .load(Uri.parse(filePath))
        .into(this)
}

fun ImageView.loadCircleImagesFromFile(context: Context, filePath: String) {
    Glide.with(context)
        .load(Uri.parse(filePath))
        .apply(RequestOptions().circleCrop())
        .into(this)
}

fun getGradientColorPokemon(listTypes: List<Type>): GradientDrawable {
    val arrayColorTypes = IntArray(2)
    for (i in listTypes.indices) {
        arrayColorTypes[i] = Color.parseColor(listTypes[i].color)
        if (listTypes.size == 1) {
            arrayColorTypes[1] = Color.parseColor(listTypes[0].color)
        }
    }

    val gd = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, arrayColorTypes)
    gd.cornerRadius = 0f
    return gd
}