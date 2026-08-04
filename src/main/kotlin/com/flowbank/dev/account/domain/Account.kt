package com.flowbank.dev.account.domain

import java.util.UUID

data class Account(
    val id: UUID = UUID.randomUUID(),
    val userId: UUID,
    val number: String = UUID.randomUUID().toString(),
    val type: AccountType = AccountType.CHECKING,
    val status: AccountStatus = AccountStatus.ACTIVE,
    val balance: Double = 0.0
)

enum class AccountType {
    CHECKING, SAVINGS
}

enum class AccountStatus {
    ACTIVE, INACTIVE
}