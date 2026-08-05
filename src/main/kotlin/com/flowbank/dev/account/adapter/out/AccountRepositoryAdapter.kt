package com.flowbank.dev.account.adapter.out

import com.flowbank.dev.account.application.AccountRepository
import com.flowbank.dev.account.domain.Account
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountRepositoryAdapter(
    private val repository: AccountRepositoryJpa
) : AccountRepository {

    override fun save(account: Account): Account {
        val entity = AccountEntity(
            id = account.id,
            userId = account.userId,
            number = account.number,
            type = account.type,
            status = account.status
        )
        val saved = repository.save(entity)
        return Account(
            id = saved.id,
            userId = saved.userId,
            number = saved.number,
            type = saved.type,
            status = saved.status
        )
    }

    override fun findByUserId(userId: UUID): Account? {
        return repository.findByUserId(userId)?.let {
            Account(
                id = it.id,
                userId = it.userId,
                number = it.number,
                type = it.type,
                status = it.status
            )
        }
    }

    override fun findAll(): List<Account> {
        return repository.findAll().map {
            Account(
                id = it.id,
                userId = it.userId,
                number = it.number,
                type = it.type,
                status = it.status
            )
        }
    }
}