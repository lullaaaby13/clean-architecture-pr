package me.lullaby.tradlinxbackend.assertions

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

val test_account = "test_account"
val test_password = "1234"
val test_nickname = "lullaby"

var accessToken = ""
fun 회원_탈퇴(
    mockMvc: MockMvc,
    userid: String = test_account,
    pw: String = test_password,
): ResultActions {
    return mockMvc.perform(
        MockMvcRequestBuilders.delete("/signout")
            .queryParam("userid", userid)
            .queryParam("pw", pw)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
    )
}

fun 회원_가입(
    mockMvc: MockMvc,
    userid: String = test_account,
    pw: String = test_password,
    username: String = test_nickname
): ResultActions {
    val requestBody = mapOf("userid" to userid, "pw" to pw, "username" to username)
    return mockMvc.perform(
        MockMvcRequestBuilders.post("/signup")
            .content(jacksonObjectMapper().writeValueAsString(requestBody))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
    )
}

fun 로그인(
    mockMvc: MockMvc,
    userid: String = test_account,
    pw: String = test_password,
): ResultActions {
    val requestBody = mapOf("userid" to userid, "pw" to pw)
    return mockMvc.perform(
        MockMvcRequestBuilders.post("/signin")
            .content(jacksonObjectMapper().writeValueAsString(requestBody))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
    )
}