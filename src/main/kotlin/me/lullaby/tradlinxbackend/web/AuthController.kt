package me.lullaby.tradlinxbackend.web

import jakarta.validation.Valid
import jakarta.validation.constraints.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @PostMapping("signup")
    fun signup(@Valid @RequestBody request: SignUpRequest) {
        println(request)
    }

    @PostMapping("signin")
    fun signin(@RequestBody request: SignInRequest): SignInResponse {
        println(request)
        return SignInResponse("accessToken", "refreshToken")
    }


}

data class SignUpRequest(
    @field:Size(min = 4, max = 20)
    val userid: String,

    @field:Size(min = 4, max = 20)
    val pw: String,

    @field:Size(min = 4, max = 20)
    val username: String,
)

data class SignInRequest(
    @field:NotBlank
    val userid: String,
    @field:NotBlank
    val pw: String,
)

data class SignInResponse(
    val accessToken: String,
    val refreshToken: String,
)


