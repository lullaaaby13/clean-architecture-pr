package me.lullaby.tradlinxbackend.application.authenticate

import me.lullaby.tradlinxbackend.application.authenticate.dto.SignInCommand
import me.lullaby.tradlinxbackend.application.authenticate.dto.SignInResponse
import me.lullaby.tradlinxbackend.application.authenticate.dto.SignUpCommand
import me.lullaby.tradlinxbackend.domain.user.CreateUserCommand
import me.lullaby.tradlinxbackend.domain.user.UserService
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticationService (
    val userService: UserService,
    val provider: JwtProvider,
){

    fun signUp(command: SignUpCommand) {
        val createUserCommand = CreateUserCommand(command.userid, command.pw, command.username)
        val createUser = this.userService.createUser(createUserCommand)
        if (createUser.id != null) {
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(AuthorizedUser(createUser.id.toLong()), null)
        }
    }

    fun signIn(command: SignInCommand): SignInResponse {

        val findUser = this.userService.getUser(command.userid)

        if (command.pw != findUser.password) {
            throw BadCredentialsException("아이디 또는 패스워드가 일치하지 않습니다. (userid = ${command.userid})")
        }

        if (findUser.id == null) {
            throw BadCredentialsException("올바르지 않은 유저 입니다. (userid = ${command.userid})")
        }

        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(findUser.id.toString(), null)

        return SignInResponse(
            accessToken = provider.accessToken(findUser.id),
            refreshToken = provider.refreshToken(findUser.id),
        )
    }

}
