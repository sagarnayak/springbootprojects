package com.sagar.jpa.security

import com.sagar.jpa.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AppUserService : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

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
               throw RuntimeException("no uer found")
            }
        }
    }
}
