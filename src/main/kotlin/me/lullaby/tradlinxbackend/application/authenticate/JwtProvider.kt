package me.lullaby.tradlinxbackend.application.authenticate

import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider {

    val logger = LoggerFactory.getLogger(JwtProvider::class.java)

    val JWT_SECRET = "my_secret"
    val ACCESS_TOKEN_EXPIRATION_MS = 1000 * 60 * 60 // 3600초
    val REFRESH_TOKEN_EXPIRATION_MS = 1000 * 60 * 60 * 24 * 7 // 7일

    val parser = Jwts.parser().setSigningKey(JWT_SECRET)

    fun accessToken(userId: Long): String  {

        val issuedAt = Date()
        val expiration = Date(issuedAt.time + ACCESS_TOKEN_EXPIRATION_MS)

        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
            .compact()
    }

    fun refreshToken(userId: Long): String  {

        val issuedAt = Date()
        val expiration = Date(issuedAt.time + REFRESH_TOKEN_EXPIRATION_MS)

        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
            .compact()
    }

    fun parseSubject(token: String): String {
        return parser.parseClaimsJws(token).body.get("sub") as String
    }

    fun parseClaim() {

    }

    // Jwt 토큰 유효성 검사
    fun validate(token: String?): Boolean {
        try {
            parser.parseClaimsJws(token)
            return true
        } catch (ex: SignatureException) {
            logger.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty.")
        }
        return false
    }
}
