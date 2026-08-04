package com.flowbank.dev

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<DevApplication>().with(TestcontainersConfiguration::class).run(*args)
}
