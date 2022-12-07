package me.lullaby.tradlinxbackend.common

data class ErrorResponse(
    val error: String,
    val message: String,
    val reason: String,
)