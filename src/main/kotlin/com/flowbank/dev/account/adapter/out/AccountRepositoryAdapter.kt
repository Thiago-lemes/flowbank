package com.flowbank.dev.account.adapter.out

import com.flowbank.dev.account.application.AccountRepository
import com.flowbank.dev.account.domain.Account
import org.springframework.stereotype.Component

@Component
class AccountRepositoryAdapter(
    private val jpa: AccountRepositoryJpa
) : AccountRepository {

    override fun save(account: Account): Account {
        val entity = AccountEntity(
            id = account.id,
            userId = account.userId,
            number = account.number,
            type = account.type,
            status = account.status
        )
        val saved = jpa.save(entity)
        return Account(
            id = saved.id,
            userId = saved.userId,
            number = saved.number,
            type = saved.type,
            status = saved.status
        )
    }
}