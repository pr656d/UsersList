package com.pr656d.userslist.model

import com.google.gson.annotations.SerializedName

data class Registered(
    @SerializedName("date")
    val date: String? = null,

    @SerializedName("age")
    val age: Int? = null
)