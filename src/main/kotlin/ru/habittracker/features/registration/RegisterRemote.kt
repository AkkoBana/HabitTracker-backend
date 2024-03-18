package ru.habittracker.features.registration

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val login: String,
    val email: String,
    val password: String,
    @SerialName("username")
    val userName: String
)

@Serializable
data class RegisterResponseRemote(
    val token: String
)