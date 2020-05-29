package com.pr656d.userslist.model

import com.google.gson.annotations.SerializedName

data class Result (
    @SerializedName("results")
    val results: List<User>? = null,

    @SerializedName("info")
    val info: Info? = null
)