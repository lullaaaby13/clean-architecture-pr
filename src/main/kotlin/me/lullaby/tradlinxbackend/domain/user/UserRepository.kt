package me.lullaby.tradlinxbackend.domain.user

import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository: CrudRepository<User, Long> {
    fun findByAccount(account: String): Optional<User>
}
