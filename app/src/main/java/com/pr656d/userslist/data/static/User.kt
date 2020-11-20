package com.pr656d.userslist.data.static

import com.pr656d.userslist.model.Location
import com.pr656d.userslist.model.Name
import com.pr656d.userslist.model.Street
import com.pr656d.userslist.model.User

val user = User(
    name = Name("", "A", "Person"),
    location = Location(
        street = Street(number = 1, name = "Street"),
        city = "Some city in the world",
        state = "State",
        country = "Country",
        postcode = "000000"
    )
)

val users: List<User> = mutableListOf<User>().apply {
    repeat(20) { add(user) }
}
