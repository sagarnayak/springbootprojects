package com.sagar.jpa.security

import com.google.common.base.Strings
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
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

        val key = Keys.hmacShaKeyFor(
                jwtConfig.secretkey.toByteArray()
        )

        if (Strings.isNullOrEmpty(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        try {
            val token = header.replace("Bearer ", "")

            val claimsJWS: Jws<Claims> = Jwts
                    .parserBuilder()
                    .setSigningKey(
                            key
                    )
                    .build()
                    .parseClaimsJws(token)

            val body = claimsJWS.body

            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
                    "admin1",
                    null,
                    AppUserRole.ADMIN.getGrantedAuthorities()
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        filterChain.doFilter(request, response)
    }
}