package me.lullaby.tradlinxbackend.config

import me.lullaby.tradlinxbackend.application.authenticate.AuthInterceptor
import me.lullaby.tradlinxbackend.application.authenticate.JwtAuthenticationFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class ServletConfig(val authInterceptor: AuthInterceptor): WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor)
    }
}
