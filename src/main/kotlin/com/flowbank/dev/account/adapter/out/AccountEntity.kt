package com.flowbank.dev.account.adapter.out

import com.flowbank.dev.account.domain.AccountStatus
import com.flowbank.dev.account.domain.AccountType
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "accounts")
class AccountEntity(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "user_id", nullable = false)
    val userId: UUID,

    @Column(nullable = false, unique = true)
    val number: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: AccountType,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: AccountStatus,

    @Column(nullable = false)
    val balance: BigDecimal = BigDecimal.ZERO
)