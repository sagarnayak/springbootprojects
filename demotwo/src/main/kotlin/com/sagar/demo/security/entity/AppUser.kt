package com.sagar.demo.security.entity

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AppUser(
    private val grantedAuthority: Set<SimpleGrantedAuthority>,
    private val password: String,
    private val userName: String,
    private val isAccountNonExpired: Boolean,
    private val isAccountNonLocked: Boolean,
    private val isCredentialsNonExpired: Boolean,
    private val isEnabled: Boolean
) : UserDetails {

    override fun getAuthorities(): Set<SimpleGrantedAuthority> {
        return grantedAuthority
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return userName
    }

    override fun isAccountNonExpired(): Boolean {
        return isAccountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return isAccountNonLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return isCredentialsNonExpired
    }

    override fun isEnabled(): Boolean {
        return isEnabled
    }
}