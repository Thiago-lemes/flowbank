package com.flowbank.dev.user

import com.flowbank.dev.account.application.AccountRepository
import com.flowbank.dev.user.application.UserRepository
import com.flowbank.dev.user.application.UserService
import com.flowbank.dev.user.domain.CreateUserRequest
import com.flowbank.dev.user.domain.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class UserServiceTest {
    @Test
    fun `should create user and automatically open a CHECKING account`() {
    }

    @Test
    fun `should throw exception when name is blank`() {
        val repository = mock(UserRepository::class.java)
        val account = mock(AccountRepository::class.java)
        val service = UserService(repository, account)
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
        val account = mock(AccountRepository::class.java)
        val service = UserService(repository, account)
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
        val account = mock(AccountRepository::class.java)
        val service = UserService(repository, account)
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
        val account = mock(AccountRepository::class.java)
        val service = UserService(repository, account)

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
        val account = mock(AccountRepository::class.java)
        val service = UserService(repository, account)

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

    @Test
    fun `should create user and automatically open a CHEKING account`() {
        val userRepository = mock(UserRepository::class.java)
        val accountRepository = mock(AccountRepository::class.java)
        val service = UserService(userRepository, accountRepository)

        val request = CreateUserRequest(
            name = "Jhon Doe",
            email = "email@teste.com",
            cpf = "123.456.789-00"
        )

        val userSaved = User(
            name = request.name,
            email = request.email,
            cpf = request.cpf
        )

        whenever(userRepository.existsByEmail(request.email)).thenReturn(false)
        whenever(userRepository.existsByCpf(request.cpf)).thenReturn(false)
        whenever(userRepository.save(any())).thenReturn(userSaved)
        val result = service.createUser(request)

        assertEquals(request.name, result.name)
        assertEquals(request.email, result.email)
        verify(accountRepository).save(any())
    }
}