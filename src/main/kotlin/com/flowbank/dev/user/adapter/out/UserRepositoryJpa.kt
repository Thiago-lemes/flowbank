package com.flowbank.dev.user.adapter.out

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepositoryJpa : JpaRepository<UserEntity, UUID> {
    fun existsByEmail(email: String): Boolean
    fun existsByCpf(cpf: String): Boolean
    fun save(entity: UserEntity): UserEntity
}