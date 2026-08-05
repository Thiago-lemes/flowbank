package com.flowbank.dev.account.application

import com.flowbank.dev.account.domain.Account
import java.util.*

interface AccountRepository {
    fun save(account: Account): Account
    fun findByUserId(userId: UUID): Account?
    fun findAll(): List<Account>

}