package com.flowbank.dev.user.application

interface UserRepository {
    fun existsByEmail(email: String): Boolean
    fun existsByCpf(cpf: String): Boolean
}