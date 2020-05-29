package com.pr656d.userslist.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("street")
    @Embedded
    val street: Street? = null,

    @SerializedName("city")
    val city: String? = null,

    @SerializedName("state")
    val state: String? = null,

    @SerializedName("country")
    val country: String? = null,

    @SerializedName("postcode")
    val postcode: String? = null,

    @SerializedName("coordinates")
    @Embedded
    val coordinates: Coordinates? = null,

    @SerializedName("timezone")
    @Embedded
    val timezone: Timezone? = null
)