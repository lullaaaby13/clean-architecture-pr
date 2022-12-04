package me.lullaby.tradlinxbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TradlinxBackendApplication

fun main(args: Array<String>) {
    runApplication<TradlinxBackendApplication>(*args)
}
