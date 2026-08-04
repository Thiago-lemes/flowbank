package com.flowbank.dev.user.domain

data class CreateUserRequest(
    val name: String,
    val email: String,
    val cpf: String
)