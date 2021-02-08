package com.sagar.jpa.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.sagar.jpa.entities.UsernameAndPassword
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.sql.Date
import java.time.LocalDate
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTUsernameAndPasswordAuthenticationFilter(
    private val authManager: AuthenticationManager,
    private val jwtConfig: JWTConfig
) :
    UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val usernameAndPassword: UsernameAndPassword =
            ObjectMapper().readValue(request.inputStream, UsernameAndPassword::class.java)

        return authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                usernameAndPassword.userName,
                usernameAndPassword.password
            )
        )
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val token = Jwts
            .builder()
            .setSubject(authResult.name)
            .claim("authorities", "value")
            .setExpiration(Date.valueOf(LocalDate.now().plusWeeks(2)))
            .signWith(
                Keys.hmacShaKeyFor(
                    jwtConfig.secretkey.toByteArray()
                )
            )
            .compact()

        response.addHeader(
            "Authorization",
            "Bearer $token"
        )
    }
}