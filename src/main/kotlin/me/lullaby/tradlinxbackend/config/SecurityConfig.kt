package me.lullaby.tradlinxbackend.config

import me.lullaby.tradlinxbackend.application.authenticate.JwtAuthenticationFilter
import me.lullaby.tradlinxbackend.application.authenticate.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
class SecurityConfig(private val jwtProvider: JwtProvider) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {


        http
            .httpBasic().disable()
            .formLogin().disable() // FORM LOGIN 해제
            .csrf().disable() // CSRF 해체
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests {
                it
                    .requestMatchers("/signup", "/signin").permitAll()
                    .anyRequest()
                    .authenticated()
            }

        http.addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}
