package com.pr656d.userslist.model

import com.google.gson.annotations.SerializedName

data class Coordinates (
    @SerializedName("latitude")
    val latitude: String? = null,

    @SerializedName("longitude")
    val longitude: String? = null
)