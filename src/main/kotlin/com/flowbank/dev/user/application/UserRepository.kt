package com.flowbank.dev.user.application

import com.flowbank.dev.user.domain.User

interface UserRepository {
    fun existsByEmail(email: String): Boolean
    fun existsByCpf(cpf: String): Boolean
    fun save(user: User): User
}