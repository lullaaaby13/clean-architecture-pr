package me.lullaby.tradlinxbackend.assertions

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

val objectMapper: ObjectMapper = ObjectMapper()

fun 회원가입_요청(userid: String?, pw: String, username: String): MockHttpServletRequestBuilder {
    val requestBody = mapOf("userid" to userid, " pw" to pw, "username" to username)
    return MockMvcRequestBuilders.post("/signup")
        .content(objectMapper.writeValueAsString(requestBody))
        .contentType(MediaType.APPLICATION_JSON)
}

fun 로그인_요청(userid: String, pw: String): MockHttpServletRequestBuilder {
    val requestBody = mapOf("userid" to userid, "pw" to pw)
    return MockMvcRequestBuilders.post("/signin")
        .content(objectMapper.writeValueAsString(requestBody))
        .contentType(MediaType.APPLICATION_JSON)
}
