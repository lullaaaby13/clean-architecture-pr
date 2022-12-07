package me.lullaby.tradlinxbackend.application.authenticate

import me.lullaby.tradlinxbackend.application.authenticate.dto.SignInCommand
import me.lullaby.tradlinxbackend.application.authenticate.dto.SignInResponse
import me.lullaby.tradlinxbackend.application.authenticate.dto.SignUpCommand
import me.lullaby.tradlinxbackend.domain.user.CreateUserCommand
import me.lullaby.tradlinxbackend.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class AuthenticationService (
    val userService: UserService,
    val provider: JwtProvider,
){

    fun signUp(command: SignUpCommand) {
        val createUserCommand = CreateUserCommand(command.userid, command.pw, command.username)
        val createUser = this.userService.createUser(createUserCommand)

    }

    fun signIn(command: SignInCommand): SignInResponse {

        val findUser = this.userService.getUser(command.userid)


        return SignInResponse("", "")
    }

}
