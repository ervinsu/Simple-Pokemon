package com.ervin.pokemonervin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Type(
    val typeName: String,
    val color: String = when (typeName) {
        "Psychic" -> "#F95587"
        "Normal" -> "#A8A77A"
        "Fighting" -> "#C22E28"
        "Flying" -> "#A98FF3"
        "Poison" -> "#A33EA1"
        "Ground" -> "#E2BF65"
        "Rock" -> "#B6A136"
        "Ghost" -> "#735797"
        "Steel" -> "#B7B7CE"
        "Bug" -> "#A6B91A"
        "Fire" -> "#EE8130"
        "Water" -> "#6390F0"
        "Grass" -> "#7AC74C"
        "Electric" -> "#F7D02C"
        "Ice" -> "#96D9D6"
        "Dragon" -> "#6F35FC"
        "Dark" -> "#705746"
        "Fairy" -> "#D685AD"
        else -> "#"
    }
) : Parcelable