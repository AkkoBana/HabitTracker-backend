 package ru.habittracker

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import ru.habittracker.features.login.configureLoginRouting
import ru.habittracker.features.registration.configureRegisterRouting
import ru.habittracker.plugins.configureSerialization
import ru.habittracker.plugins.*

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/habittracker", driver = "org.postgresql.Driver",
        user = "postgres",
        password = "perchikchill")
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureSerialization()
}

 fun Application.configureSwagger() {
     install()
 }