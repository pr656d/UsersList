package com.pr656d.userslist.data.user.datasource.response

import com.google.gson.annotations.SerializedName
import com.pr656d.userslist.model.Info
import com.pr656d.userslist.model.User

data class UserResponse (
    @SerializedName("results")
    val results: List<User>? = null,

    @SerializedName("info")
    val info: Info? = null
)