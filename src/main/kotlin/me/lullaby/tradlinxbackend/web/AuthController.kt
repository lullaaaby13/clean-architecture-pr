package me.lullaby.tradlinxbackend.web

import jakarta.validation.Valid
import jakarta.validation.constraints.*
import me.lullaby.tradlinxbackend.application.authenticate.AuthenticationService
import me.lullaby.tradlinxbackend.application.authenticate.dto.SignInCommand
import me.lullaby.tradlinxbackend.application.authenticate.dto.SignInResponse
import me.lullaby.tradlinxbackend.application.authenticate.dto.SignUpCommand
import me.lullaby.tradlinxbackend.application.authenticate.dto.SingOutCommand
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("signup")
    fun signup(@Valid @RequestBody command: SignUpCommand) {

        authenticationService.signUp(command)

    }

    @PostMapping("signin")
    fun signin(@RequestBody command: SignInCommand): SignInResponse {
        return authenticationService.signIn(command)
    }

    @DeleteMapping("signout")
    fun signout(command: SingOutCommand) {

    }
}


