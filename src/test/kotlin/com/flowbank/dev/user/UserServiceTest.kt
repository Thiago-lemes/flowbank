package com.flowbank.dev.user

import com.flowbank.dev.user.application.UserRepository
import com.flowbank.dev.user.application.UserService
import com.flowbank.dev.user.domain.CreateUserRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class UserServiceTest {
    @Test
    fun `should create user and automatically open a CHECKING account`() {
    }

    @Test
    fun `should throw exception when name is blank`() {
        val repository = mock(UserRepository::class.java)
        val service = UserService(repository)
        val request = CreateUserRequest(
            name = "",
            email = "teste@email.com",
            cpf = "123.456.789-00"
        )
        assertThrows<IllegalArgumentException> {
            service.createUser(request)
        }
    }

    @Test
    fun `should throw exception when email is invalid`() {
        val repository = mock(UserRepository::class.java)
        val service = UserService(repository)
        val request = CreateUserRequest(
            name = "Jhon Doe",
            email = "",
            cpf = "123.456.789-00"
        )
        assertThrows<IllegalArgumentException> {
            service.createUser(request)
        }
    }

    @Test
    fun `should throw exception when CPF is invalid`() {
        val repository = mock(UserRepository::class.java)
        val service = UserService(repository)
        val request = CreateUserRequest(
            name = "Jhon Doe",
            email = "teste@email.com",
            cpf = ""
        )
        assertThrows<IllegalArgumentException> {
            service.createUser(request)
        }
    }

    @Test
    fun `should throw exception when email already exists`() {
        val repository = mock(UserRepository::class.java)

        val service = UserService(repository)

        `when`(repository.existsByEmail("teste@email.com")).thenReturn(true)

        val request = CreateUserRequest(
            name = "John Doe",
            email = "teste@email.com",
            cpf = "123.456.789-00"
        )

        assertThrows<IllegalStateException> {
            service.createUser(request)
        }
    }

    @Test
    fun `should throw exception when CPF already exists`() {
        val repository = mock(UserRepository::class.java)

        val service = UserService(repository)

        `when`(repository.existsByCpf("123.456.789-00")).thenReturn(true)

        val request = CreateUserRequest(
            name = "John Doe",
            email = "teste@email.com",
            cpf = "123.456.789-00"
        )

        assertThrows<IllegalStateException> {
            service.createUser(request)
        }
    }
}