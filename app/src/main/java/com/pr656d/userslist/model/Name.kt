package com.pr656d.userslist.model

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("title")
    val title: String? = null,

    @SerializedName("first")
    val first: String? = null,

    @SerializedName("last")
    val last: String? = null
) {
    override fun toString(): String = "$title $first $last"
}