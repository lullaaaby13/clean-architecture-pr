package me.lullaby.tradlinxbackend.application.authenticate

import me.lullaby.tradlinxbackend.application.authenticate.dto.SignInCommand
import me.lullaby.tradlinxbackend.application.authenticate.dto.SignInResponse
import me.lullaby.tradlinxbackend.application.authenticate.dto.SignUpCommand
import me.lullaby.tradlinxbackend.application.authenticate.dto.SingOutCommand
import me.lullaby.tradlinxbackend.domain.user.CreateUserCommand
import me.lullaby.tradlinxbackend.domain.user.User
import me.lullaby.tradlinxbackend.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    val userService: UserService,
    val provider: JwtProvider,
) {

    fun signUp(command: SignUpCommand) {
        val createUserCommand = CreateUserCommand(command.userid, command.pw, command.username)

        val findUser = this.userService.findUser(command.userid)

        if (findUser.isPresent) {
            throw DuplicatedAccountException()
        }

        this.userService.createUser(createUserCommand)
    }

    fun signIn(command: SignInCommand): SignInResponse {
        val findUser: User = this.userService.findUser(command.userid)
            .orElseThrow { AuthenticateFailException() }

        if (findUser.password != command.pw || findUser.id == null) {
            throw AuthenticateFailException()
        }

        return SignInResponse(provider.accessToken(findUser.id), provider.refreshToken(findUser.id))
    }

    fun signOut(command: SingOutCommand) {
        val findUser: User = this.userService.findUser(command.userid)
            .orElseThrow { AuthenticateFailException() }

        if (findUser.password != command.pw || findUser.id == null) {
            throw AuthenticateFailException()
        }

        this.userService.deleteUser(findUser.id)
    }

}
