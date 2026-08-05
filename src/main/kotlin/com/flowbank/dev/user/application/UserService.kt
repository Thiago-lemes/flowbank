package com.flowbank.dev.user.application

import com.flowbank.dev.account.application.AccountRepository
import com.flowbank.dev.account.domain.Account
import com.flowbank.dev.account.domain.AccountType
import com.flowbank.dev.user.domain.CreateUserRequest
import com.flowbank.dev.user.domain.User

class UserService(private val userRepository: UserRepository,
                  private val accountRepository: AccountRepository) {

    fun createUser(request: CreateUserRequest): User {
        validatesUserData(request)
        val user = User(
            name = request.name,
            email = request.email,
            cpf = request.cpf
        )
        val savedUser = userRepository.save(user)

        val account = Account(
            userId = savedUser.id,
            type = AccountType.CHECKING
        )

        accountRepository.save(account)
        return savedUser
    }

    private fun validatesUserData(request: CreateUserRequest) {
        require(request.name.isNotBlank()) { "Nome não pode ser nulo ou vazio" }
        require(request.email.isNotBlank()) { "Email não pode ser nulo ou vazio" }
        require(request.cpf.isNotBlank()) { "CPF não pode ser nulo ou vazio" }

        check(!userRepository.existsByEmail(request.email)) { "Email já cadastrado" }
        check(!userRepository.existsByCpf(request.cpf)) { "CPF já cadastrado" }

    }
}