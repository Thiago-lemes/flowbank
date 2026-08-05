package com.flowbank.dev.user.adapter.out

import com.flowbank.dev.user.application.UserRepository
import com.flowbank.dev.user.domain.User
import org.springframework.stereotype.Component

@Component
class UserRepositoryAdapter(
    private val jpa: UserRepositoryJpa
) : UserRepository {

    override fun existsByEmail(email: String): Boolean =
        jpa.existsByEmail(email)

    override fun existsByCpf(cpf: String): Boolean =
        jpa.existsByCpf(cpf)

    override fun save(user: User): User {
        val entity = UserEntity(
            id = user.id,
            name = user.name,
            email = user.email,
            cpf = user.cpf
        )
        val saved = jpa.save(entity)
        return User(
            id = saved.id,
            name = saved.name,
            email = saved.email,
            cpf = saved.cpf
        )
    }
}