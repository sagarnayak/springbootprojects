package com.sagar.demo.security.services

import com.sagar.demo.repositories.StudentRepository
import com.sagar.demo.security.entity.AppUser
import com.sagar.demo.security.roles.AppUserRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AppUserService : UserDetailsService {

    @Autowired
    private lateinit var studentRepository: StudentRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun loadUserByUsername(p0: String): UserDetails {
        return when (p0) {
            "user1" -> {
                AppUser(
                    AppUserRole.USER.getGrantedAuthorities(),
                    passwordEncoder.encode("password"),
                    "user1",
                    true,
                    true,
                    true,
                    true
                )
            }
            "admin1" -> {
                AppUser(
                    AppUserRole.ADMIN.getGrantedAuthorities(),
                    passwordEncoder.encode("password"),
                    "admin1",
                    true,
                    true,
                    true,
                    true
                )
            }
            "adminTrainee1" -> {
                AppUser(
                    AppUserRole.ADMIN_TRAINEE.getGrantedAuthorities(),
                    passwordEncoder.encode("password"),
                    "adminTrainee1",
                    true,
                    true,
                    true,
                    true
                )
            }
            else -> {
                throw RuntimeException("no user found")
            }
        }
    }
}