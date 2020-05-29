package com.pr656d.userslist.model

import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("large")
    val large: String? = null,

    @SerializedName("medium")
    val medium: String? = null,

    @SerializedName("thumbnail")
    val thumbnail: String? = null
)