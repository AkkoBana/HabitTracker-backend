package ru.habittracker.features.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.habittracker.database.tokens.TokenDTO
import ru.habittracker.database.tokens.Tokens
import ru.habittracker.database.users.UserDTO
import ru.habittracker.database.users.Users
import ru.habittracker.utils.isValidEmail
import java.util.*

object RegisterController {

    suspend fun registerNewUser(call: ApplicationCall) {
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()

        if(!registerReceiveRemote.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }

        val userDTO = Users.fetchUser(registerReceiveRemote.login)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()
            Users.insert(
                UserDTO(
                    login = registerReceiveRemote.login,
                    password = registerReceiveRemote.password,
                    email = registerReceiveRemote.email,
                    userName = registerReceiveRemote.userName
                )
            )

            Tokens.insert(
                TokenDTO(
                    rowId = UUID.randomUUID().toString(),
                    login = registerReceiveRemote.login,
                    token = token
                )
            )
            call.respond(RegisterResponseRemote(token))
        }
    }
}