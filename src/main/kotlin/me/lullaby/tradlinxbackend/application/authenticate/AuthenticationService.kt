package me.lullaby.tradlinxbackend.application.authenticate

import me.lullaby.tradlinxbackend.domain.user.CreateUserCommand
import me.lullaby.tradlinxbackend.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class AuthenticationService (
    val userService: UserService
){


    fun signUp(command: SignUpCommand) {
        val createUserCommand = CreateUserCommand(command.userid, command.pw, command.username)
        this.userService.createUser(createUserCommand)
    }

    fun signIn(command: SignInCommand) {

        val findUser = this.userService.getUser(command.userid)

        if (command.pw != findUser.pw) {
            throw Error("아이디 또는 패스워드가 일치하지 않습니다. (userid = ${command.userid})")
        }

        // TODO 토큰 발행 & 인증 처리


    }

}

data class SignUpCommand(
    val userid: String,
    val pw: String,
    val username: String,
)

data class SignInCommand(
    val userid: String,
    val pw: String,
)