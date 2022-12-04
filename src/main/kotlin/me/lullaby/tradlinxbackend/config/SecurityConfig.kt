package me.lullaby.tradlinxbackend.config

import me.lullaby.tradlinxbackend.application.authenticate.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(private val jwtProvider: JwtProvider){

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.formLogin().disable() // Form Login 해제

        // TODO: CSRF 해제
        http.csrf().disable()

        // 엔드포인트 Auth 설정
        http.authorizeHttpRequests()
            .requestMatchers("")
            .and()
                .anyRequest()
                .authenticated()


            .requestMatchers()

        http.headers()
            .frameOptions()
            .sameOrigin()
            .and()

        return http.build()
    }

}