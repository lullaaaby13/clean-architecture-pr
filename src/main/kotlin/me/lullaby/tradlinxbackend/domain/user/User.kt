package me.lullaby.tradlinxbackend.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long?,

    @Column(name = "account")
    val account: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "nickname")
    val nickname: String,
)
