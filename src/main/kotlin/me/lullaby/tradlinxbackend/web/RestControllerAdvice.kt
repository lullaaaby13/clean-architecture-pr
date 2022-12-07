package me.lullaby.tradlinxbackend.web

import me.lullaby.tradlinxbackend.common.HttpException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(HttpException::class)
    fun httpExceptionHandler(exception: HttpException): ResponseEntity<ErrorResponse> {
        val name: String = exception::javaClass::get.name
        return ResponseEntity.status(exception.httpStatus)
            .body(ErrorResponse(error = name, message = exception.message ?: ""))
    }

}

data class ErrorResponse(val error: String, val message: String)