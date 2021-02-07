package com.sagar.jpa.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class AppSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun configure(http: HttpSecurity?) {
        @Suppress("SimpleRedundantLet")
        http?.let {
            it
                .csrf().disable()
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

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val user1 = User
            .builder()
            .username("user1")
            .password(passwordEncoder.encode("password"))
            .authorities(AppUserRole.USER.getGrantedAuthorities())
            .build()

        val admin1 = User
            .builder()
            .username("admin1")
            .password(passwordEncoder.encode("password"))
            .authorities(AppUserRole.ADMIN.getGrantedAuthorities())
            .build()

        val adminTrainee1 = User
            .builder()
            .username("adminTrainee1")
            .password(passwordEncoder.encode("password"))
            .authorities(AppUserRole.ADMIN_TRAINEE.getGrantedAuthorities())
            .build()

        return InMemoryUserDetailsManager(user1, admin1, adminTrainee1)
    }
}