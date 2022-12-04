package me.lullaby.tradlinxbackend.application.authenticate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class JwtProviderTest {

    val provider = JwtProvider()

    @Test
    internal fun generate() {
        val token = provider.accessToken(1L)
        println(token)
        assertTrue(provider.validate(token))
    }

    @Test
    internal fun parse() {
        val token = provider.accessToken(1L)
        assertEquals("1", provider.parseSubject(token))
    }

}
