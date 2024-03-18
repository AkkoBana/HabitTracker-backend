package ru.habittracker.features.registration

import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun Application.configureRegisterRouting() {
    routing {
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
        post("/register") {
            RegisterController.registerNewUser(call)
        }
    }
}