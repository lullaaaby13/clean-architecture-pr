package me.lullaby.tradlinxbackend.domain.user

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun createUser(command: CreateUserCommand): User {
        val user = User(id = null, account = command.userid, password = command.pw, nickname = command.username)
        return this.userRepository.save(user)
    }

    fun getUser(account: String): User {
        return this.userRepository.findByAccount(account)
            .orElseThrow { Error("사용자를 찾을 수 없습니다. (account = ${account})") }
    }


}

data class CreateUserCommand(
    val userid: String,
    val pw: String,
    val username: String,
)
