package me.lullaby.tradlinxbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping

@SpringBootApplication
class TradlinxBackendApplication

fun main(args: Array<String>) {
    runApplication<TradlinxBackendApplication>(*args)
}
