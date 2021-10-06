package com.sagar.demo.security

import com.sagar.demo.security.authorities.AppUserPermission
import com.sagar.demo.security.entity.JWTConfig
import com.sagar.demo.security.filters.CustomFilter
import com.sagar.demo.security.filters.JWTUsernameAndPasswordAuthenticationFilter
import com.sagar.demo.security.roles.AppUserRole
import com.sagar.demo.security.services.AppUserService
import com.sagar.demo.security.verifier.JWTVerifier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor

@Configuration
@EnableWebSecurity(debug = true)
class AppSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var appUserService: AppUserService

    @Autowired
    private lateinit var jwtConfig: JWTConfig

    override fun configure(http: HttpSecurity?) {
        @Suppress("SimpleRedundantLet")
        http?.let {
            it
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(JWTUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig))
                .addFilterAfter(JWTVerifier(jwtConfig), JWTUsernameAndPasswordAuthenticationFilter::class.java)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/database").hasAuthority(AppUserPermission.ADMIN_WRITE.permission)
                .antMatchers(HttpMethod.PUT, "/database").hasAuthority(AppUserPermission.ADMIN_WRITE.permission)
                .antMatchers(HttpMethod.DELETE, "/database").hasAuthority(AppUserPermission.ADMIN_WRITE.permission)
                .antMatchers(HttpMethod.GET, "/database")
                .hasAnyRole(AppUserRole.ADMIN.name, AppUserRole.ADMIN_TRAINEE.name)
                .and()
                .addFilterAfter(CustomFilter(), FilterSecurityInterceptor::class.java)
                .authorizeRequests()
                .antMatchers(
                    "/v2/api-docs",
                    "/swagger-ui/index.html",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/hello/sendEmail"
                ).permitAll()
                .anyRequest().authenticated()
        }
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authProvider())
    }

    @Bean
    fun authProvider(): DaoAuthenticationProvider {
        val daoAuthProvider = DaoAuthenticationProvider()
        daoAuthProvider.setPasswordEncoder(passwordEncoder)
        daoAuthProvider.setUserDetailsService(appUserService)
        return daoAuthProvider
    }
}