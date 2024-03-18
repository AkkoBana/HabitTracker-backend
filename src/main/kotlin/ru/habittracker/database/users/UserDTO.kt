package ru.habittracker.database.users

data class UserDTO(
    val login: String,
    val password: String,
    val email: String?,
    val userName: String,
)