package com.flowbank.dev.account.adapter.out

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AccountRepositoryJpa : JpaRepository<AccountEntity, UUID>