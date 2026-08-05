package com.flowbank.dev.account.application

import com.flowbank.dev.account.domain.Account
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AccountService(private val accountRepository: AccountRepository) {

    fun findByUserId(userId: UUID): Account {
        return accountRepository.findByUserId(userId)
            ?: throw IllegalArgumentException("Account not found for user $userId")
    }

    fun findAll(): List<Account> {
        return accountRepository.findAll()
    }
}