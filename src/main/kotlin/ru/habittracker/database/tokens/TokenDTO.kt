package ru.habittracker.database.tokens

data class TokenDTO (
    val rowId: String,
    val login: String,
    val token: String
)