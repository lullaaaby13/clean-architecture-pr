package me.lullaby.tradlinxbackend.assertions

import com.fasterxml.jackson.databind.ObjectMapper
import me.lullaby.tradlinxbackend.application.authenticate.dto.SignUpCommand
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

    @DisplayName("헬스 체크")
    @Test
    fun health_check_success() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/health-check")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    //@DisplayName("회원 가입 성공")
    @Test
    fun `회원 가입 성공`() {
        mockMvc.perform(회원가입_요청("test", "1234", "apple"))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(MockMvcResultHandlers.print())
    }

    @DisplayName("회원 가입 실패 / 아이디 길이 초과")
    @Test
    fun sing_up_fail_invalid_param_1() {
        mockMvc.perform(회원가입_요청("tooooooooooooooooooooooooooo_long_userid", "1234", "apple"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }

    @DisplayName("회원 가입 실패 / 패스워드 길이 초과")
    @Test
    fun sing_up_fail_invalid_param_2() {
        mockMvc.perform(회원가입_요청("test", "tooooooooooooooooooooooooooo_long_pw", "apple"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }

    @DisplayName("회원 가입 실패 / 이름 길이 초과")
    @Test
    fun sing_up_fail_invalid_param_3() {
        mockMvc.perform(회원가입_요청("test", "1234", "tooooooooooooooooooooooooooo_long_username"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }

    @DisplayName("회원 가입 실패 / 아이디 중복")
    @Test
    fun sing_up_fail_duplicate() {
        mockMvc.perform(회원가입_요청("test", "1234", "apple"))
        mockMvc.perform(회원가입_요청("test", "1234", "apple"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @DisplayName("로그인 성공")
    @Test
    fun sing_in_success() {
        mockMvc.perform(회원가입_요청("test", "1234", "apple"))
        mockMvc.perform(로그인_요청("test", "1234"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(jsonPath("$.accessToken").exists())
            .andExpect(jsonPath("$.refreshToken").exists())
            .andDo(MockMvcResultHandlers.print())
    }

    @DisplayName("로그인 실패 / 아이디 없음")
    @Test
    fun sing_in_fail_1() {
        mockMvc.perform(로그인_요청("test", "1234"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @DisplayName("로그인 실패 / 패스워드 틀림")
    @Test
    fun sing_in_fail_2() {
        mockMvc.perform(회원가입_요청("test", "1234", "apple"))
        mockMvc.perform(로그인_요청("test", "abcd"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }

}

