package com.flowbank.dev.user.application

import com.flowbank.dev.user.domain.CreateUserRequest

class UserService(private val userRepository: UserRepository) {

    fun createUser(request: CreateUserRequest) {
        validatesUserData(request)
    }

    private fun validatesUserData(request: CreateUserRequest) {
        require(request.name.isNotBlank()) { "Nome não pode ser nulo ou vazio" }
        require(request.email.isNotBlank()) { "Email não pode ser nulo ou vazio" }
        require(request.cpf.isNotBlank()) { "CPF não pode ser nulo ou vazio" }

        check(!userRepository.existsByEmail(request.email)) { "Email já cadastrado" }
        check(!userRepository.existsByCpf(request.cpf)) { "CPF já cadastrado" }

    }
}