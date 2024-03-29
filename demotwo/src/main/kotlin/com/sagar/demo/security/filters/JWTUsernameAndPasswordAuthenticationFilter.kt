package com.sagar.demo.security.filters

import com.fasterxml.jackson.databind.ObjectMapper
import com.sagar.demo.security.entity.JWTConfig
import com.sagar.demo.security.entity.UsernameAndPassword
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.sql.Date
import java.util.*
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

        try {
            return authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    usernameAndPassword.userName,
                    usernameAndPassword.password
                )
            )
        } catch (ex: Exception) {
            ex.printStackTrace()

            throw Exception("This is a test")
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        try {
            val key = Keys.hmacShaKeyFor(
                jwtConfig.secretkey.toByteArray()
            )
            val cal = Calendar.getInstance()
            cal.add(Calendar.MINUTE, 2)
            val date = Date(cal.timeInMillis)
            val token = Jwts
                .builder()
                .setSubject(authResult.name)
                .claim("authorities", "value")
                .setExpiration(date)
                .signWith(
                    key
                )
                .compact()
            val claimsJWS: Jws<Claims> = Jwts
                .parserBuilder()
                .setSigningKey(
                    key
                )
                .build()
                .parseClaimsJws(token)
            var sign = ""
            sign = claimsJWS.signature
            println("the result ${sign}")
            response.addHeader(
                "Authorization",
                "Bearer $token"
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}