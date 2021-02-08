package com.sagar.jpa.security

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

@Configuration
@EnableWebSecurity
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
                .addFilter(JWTUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig))
                .addFilterAfter(JWTVerifier(jwtConfig), JWTUsernameAndPasswordAuthenticationFilter::class.java)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/admin").hasAuthority(AppUserPermission.ADMIN_WRITE.permission)
                .antMatchers(HttpMethod.PUT, "/admin").hasAuthority(AppUserPermission.ADMIN_WRITE.permission)
                .antMatchers(HttpMethod.DELETE, "/admin").hasAuthority(AppUserPermission.ADMIN_WRITE.permission)
                .antMatchers(HttpMethod.GET, "/admin")
                .hasAnyRole(AppUserRole.ADMIN.name, AppUserRole.ADMIN_TRAINEE.name)
                .antMatchers("/", "index", "/swagger-ui").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
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