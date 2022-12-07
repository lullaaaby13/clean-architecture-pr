package me.lullaby.tradlinxbackend.common

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

open class HttpException(
    val httpStatus: HttpStatus,
    message: String,
): RuntimeException(message) {
}