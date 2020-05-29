package com.pr656d.userslist.model

import com.google.gson.annotations.SerializedName

data class Street(
    @SerializedName("number")
    val number: Int? = null,

    @SerializedName("name")
    val name: String? = null
) {
    override fun toString(): String = "$number, $name"
}