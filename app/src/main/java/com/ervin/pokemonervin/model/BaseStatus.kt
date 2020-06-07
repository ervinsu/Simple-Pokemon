package com.ervin.pokemonervin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BaseStatus (
    @SerializedName("HP")
    val hp: Int,
    @SerializedName("Attack")
    val attack: Int,
    @SerializedName("Defense")
    val defense: Int,
    @SerializedName("Sp. Attack")
    val spAttack: Int,
    @SerializedName("Sp. Defense")
    val spDefense: Int,
    @SerializedName("Speed")
    val speed: Int
) : Parcelable