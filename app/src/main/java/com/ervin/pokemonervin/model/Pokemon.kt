package com.ervin.pokemonervin.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    val pokemonID: Int,
    var pokemonName: String,
    var pokemonBaseStatus: BaseStatus,
    var pokemonTypes: List<Type>
)