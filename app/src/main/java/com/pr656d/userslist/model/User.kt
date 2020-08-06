package com.pr656d.userslist.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "userList")
data class User(
    @SerializedName("gender")
    val gender: String? = null,

    @SerializedName("name")
    @Embedded(prefix = "name")
    val name: Name? = null,

    @SerializedName("location")
    @Embedded(prefix = "location")
    val location: Location? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("login")
    @Embedded(prefix = "login")
    val login: Login? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("picture")
    @Embedded(prefix = "picture")
    val picture: Picture? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "primaryKey")
    var primaryKey: Int = 0
}