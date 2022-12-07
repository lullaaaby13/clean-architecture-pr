package me.lullaby.tradlinxbackend.assertions

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


fun 회원가입_요청(userid: String?, pw: String, username: String): MockHttpServletRequestBuilder {
    val requestBody = mapOf("userid" to userid, " pw" to pw, "username" to username)
    val json = jacksonObjectMapper().writeValueAsString(requestBody)
    println(json)
    return MockMvcRequestBuilders.post("/signup")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
}

fun 로그인_요청(userid: String, pw: String): MockHttpServletRequestBuilder {
    val requestBody = mapOf("userid" to userid, "pw" to pw)
    return MockMvcRequestBuilders.post("/signin")
        .content(jacksonObjectMapper().writeValueAsString(requestBody))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
}
