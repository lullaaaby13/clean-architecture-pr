package me.lullaby.tradlinxbackend.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @GetMapping("profile")
    fun profile(): ProfileResponse {
        return ProfileResponse("username")
    }

    @GetMapping("profile")
    fun points() {

    }

}

data class ProfileResponse(
    val username: String
)


data class PointResponse (
    val point: Integer
)
