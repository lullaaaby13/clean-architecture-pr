package me.lullaby.tradlinxbackend.domain.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun findUser(account: String): Optional<User> {
        return this.userRepository.findByAccount(account)
    }

    @Transactional
    fun createUser(command: CreateUserCommand): User {
        val user = User(id = null, account = command.userid, password = command.pw, nickname = command.username)
        return this.userRepository.save(user)
    }

    @Transactional
    fun deleteUser(userId: Long) {
        this.userRepository.deleteById(userId)
    }
}

data class CreateUserCommand(
    val userid: String,
    val pw: String,
    val username: String,
)
