package com.sagar.jpa.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AppUser(
    private val grantedAuthority: MutableCollection<out GrantedAuthority>,
    private val password: String,
    private val userName: String,
    private val isAccountNonExpired: Boolean,
    private val isAccountNonLocked: Boolean,
    private val isCredentialsNonExpired: Boolean,
    private val isEnabled: Boolean
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
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