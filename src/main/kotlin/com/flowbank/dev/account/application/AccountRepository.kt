package com.flowbank.dev.account.application

import com.flowbank.dev.account.domain.Account

fun interface AccountRepository {
    fun save(account: Account): Account
}