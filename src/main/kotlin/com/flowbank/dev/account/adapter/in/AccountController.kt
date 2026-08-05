package com.flowbank.dev.account.adapter.`in`

import com.flowbank.dev.account.application.AccountService
import com.flowbank.dev.account.domain.Account
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/accounts")
class AccountController(private val accountService: AccountService) {

    @GetMapping("/user/{userId}")
    fun getAccountByUserId(@PathVariable userId: UUID): Account {
        return accountService.findByUserId(userId)
    }

    @GetMapping
    fun findAll(): List<Account> {
        return accountService.findAll()
    }
}