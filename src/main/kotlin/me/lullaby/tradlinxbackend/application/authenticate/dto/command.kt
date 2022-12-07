package me.lullaby.tradlinxbackend.application.authenticate.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignUpCommand(
    @field:Size(min = 4, max = 20)
    val userid: String,

    @field:Size(min = 4, max = 20)
    val pw: String,

    @field:Size(min = 4, max = 20)
    val username: String,
)

data class SignInCommand(
    @field:NotBlank
    val userid: String,
    @field:NotBlank
    val pw: String,
)

data class SingOutCommand(
    @field:NotBlank
    val userid: String,
    @field:NotBlank
    val pw: String,
)
