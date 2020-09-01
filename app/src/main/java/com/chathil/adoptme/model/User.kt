package com.chathil.adoptme.model

import com.chathil.adoptme.R

data class User(
    val firstName: String,
    val lastName: String,
    val photo: Int,
    val fullName: String = "$firstName $lastName",
    val email: String
) {
    companion object
}

val User.Companion.fake: User
    get() = User(
        firstName = "Abdul", lastName = "Chathil",
        photo = R.drawable.me, email = "chathil98@gmail.com"
    )

