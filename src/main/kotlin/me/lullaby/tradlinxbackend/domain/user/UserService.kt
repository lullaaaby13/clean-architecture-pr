package me.lullaby.tradlinxbackend.domain.user

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun createUser(command: CreateUserCommand): User {
        val user = User(id = null, userid = command.userid, pw = command.pw, username = command.username)
        return this.userRepository.save(user)
    }

    fun getUser(userid: String): User {
        return this.userRepository.findByUserid(userid)
            .orElseThrow { Error("사용자를 찾을 수 없습니다. (userId = ${userid})") }
    }


}

data class CreateUserCommand(
    val userid: String,
    val pw: String,
    val username: String,
)