package me.lullaby.tradlinxbackend.application.authenticate

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class JwtAuthenticationFilter: OncePerRequestFilter() {

    val NOT_FILTERED_URI: List<String> = listOf("/signup", "/signin", "/health-check")
    val provider = JwtProvider()

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val bearerToken = request.getHeader("Authorization")
        if (bearerToken?.startsWith("Bearer ") == true) {
            val token = bearerToken.substringAfter("Bearer ")
            if (provider.validate(token)) {
                val userId = provider.parseSubject(token)
                filterChain.doFilter(request, response)
            }
        }

        println("JWTFilter 탔당ㅋㅋ")
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return this.NOT_FILTERED_URI.contains(request.requestURI)
    }
}
