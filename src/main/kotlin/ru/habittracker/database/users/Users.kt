package ru.habittracker.database.users

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Users : IdTable<Int>("users") {
    override val id = Users.integer("id").entityId()
    val login = Users.varchar("login", 25)
    val password = Users.varchar("password", 25)
    val email = Users.varchar("email", 25).nullable()
    val userName = Users.varchar("username", 25)

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[email] = userDTO.email
                it[userName] = userDTO.userName
            }

        }
    }

    fun fetchUser(login: String): UserDTO? {
        return try {
            val userModel = Users.select(column = Users.login.eq(login)).single()
            UserDTO(
                login = userModel[Users.login],
                password = userModel[password],
                userName = userModel[userName],
                email = userModel[email]
            )
        } catch (e: Exception) {
            null
        }

    }
}