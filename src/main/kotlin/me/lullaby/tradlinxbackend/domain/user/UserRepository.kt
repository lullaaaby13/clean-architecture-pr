package me.lullaby.tradlinxbackend.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserid(userid: String): Optional<User>
}