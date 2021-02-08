package com.sagar.jpa.security

import com.google.common.base.Strings
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTVerifier(private val jwtConfig: JWTConfig) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")

        if (Strings.isNullOrEmpty(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        try {
            val token = header.replace("Bearer ", "")

            val claimsJWS: Jws<Claims> = Jwts
                .parserBuilder()
                .setSigningKey(jwtConfig.secretkey)
                .build()
                .parseClaimsJws(token)

            val body = claimsJWS.body
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        filterChain.doFilter(request, response)
    }
}