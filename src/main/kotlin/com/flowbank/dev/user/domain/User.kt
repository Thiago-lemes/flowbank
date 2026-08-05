package com.flowbank.dev.user.domain

import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val email: String,
    val cpf: String
)