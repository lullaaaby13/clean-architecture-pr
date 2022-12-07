package me.lullaby.tradlinxbackend.assertions

import com.fasterxml.jackson.databind.ObjectMapper
import me.lullaby.tradlinxbackend.application.authenticate.dto.SignUpCommand
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@AutoConfigureMockMvc
@SpringBootTest
class AuthAssertionTests {

    @Autowired
    private lateinit var mockMvc: MockMvc


    @AfterEach
    fun afterEach() {
        회원_탈퇴(mockMvc)
    }

    @DisplayName("회원 가입 성공")
    @Test
    fun sign_up_success() {
        회원_가입(mockMvc)
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(MockMvcResultHandlers.print())

        회원_탈퇴(mockMvc)
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @DisplayName("회원 가입 실패 / 아이디 길이 초과")
    @Test
    fun sing_up_fail_invalid_param_1() {
        회원_가입(mockMvc, userid = "tooooooooooooooooooooooooooo_long_string")
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }

    @DisplayName("회원 가입 실패 / 비밀번호 길이 초과")
    @Test
    fun sing_up_fail_invalid_param_2() {
        회원_가입(mockMvc, pw = "tooooooooooooooooooooooooooo_long_string")
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }
    @DisplayName("회원 가입 실패 / 이름 길이 초과")
    @Test
    fun sing_up_fail_invalid_param_3() {
        회원_가입(mockMvc, username = "tooooooooooooooooooooooooooo_long_string")
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }

    @DisplayName("회원 가입 실패 / 아이디 중복")
    @Test
    fun sing_up_fail_duplicate() {
        회원_가입(mockMvc)

        회원_가입(mockMvc)
            .andExpect(MockMvcResultMatchers.status().isConflict)
            .andDo(MockMvcResultHandlers.print())

        회원_탈퇴(mockMvc)
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @DisplayName("로그인 성공")
    @Test
    fun sing_in_success() {
        회원_가입(mockMvc)

        로그인(mockMvc)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(jsonPath("$.accessToken").exists())
            .andExpect(jsonPath("$.refreshToken").exists())
            .andDo(MockMvcResultHandlers.print())

        회원_탈퇴(mockMvc)
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @DisplayName("로그인 실패 / 아이디 없음")
    @Test
    fun sing_in_fail_1() {
        회원_가입(mockMvc)

        로그인(mockMvc, userid = "not_found_user")
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())

        회원_탈퇴(mockMvc)
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @DisplayName("로그인 실패 / 패스워드 틀림")
    @Test
    fun sing_in_fail_2() {
        //given
        회원_가입(mockMvc, pw = "unknown_password")

        // when & then
        로그인(mockMvc, pw = "unknown_password")
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())

        회원_탈퇴(mockMvc, pw = "unknown_password")
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

}

