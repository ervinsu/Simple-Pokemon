package com.ervin.pokemonervin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    @SerializedName("id")
    val pokemonID: Int,
    var pokemonName: String,
    var pokemonBaseStatus: BaseStatus,
    var pokemonTypes: List<Type>
) : Parcelable