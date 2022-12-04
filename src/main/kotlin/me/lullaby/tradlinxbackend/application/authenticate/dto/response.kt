package me.lullaby.tradlinxbackend.application.authenticate.dto

data class SignInResponse(
    val accessToken: String,
    val refreshToken: String,
)

