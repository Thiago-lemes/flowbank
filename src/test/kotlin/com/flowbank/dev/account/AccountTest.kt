package com.flowbank.dev.account

import com.flowbank.dev.account.domain.Account
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.util.UUID

class AccountTest {
    @Test
    fun `should not generate duplicate account numbers`() {
        val account1 = Account(userId = UUID.randomUUID())
        val account2 = Account(userId = UUID.randomUUID())

        assertNotEquals(account1.number, account2.number)
    }

    @Test
    fun `should create user and automatically open a CHECKING account`() {}
}