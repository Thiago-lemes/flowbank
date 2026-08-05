package com.flowbank.dev.account.adapter.out

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepositoryJpa : JpaRepository<AccountEntity, UUID> {
    fun findByUserId(userId: UUID): AccountEntity?
}