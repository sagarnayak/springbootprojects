package com.sagar.jpa.security

import com.google.common.collect.Sets
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class AppUserRole(val permissions: Set<AppUserPermission>) {
    USER(
        Sets.newHashSet()
    ),
    ADMIN(
        Sets.newHashSet(
            AppUserPermission.ADMIN_READ,
            AppUserPermission.ADMIN_WRITE,
            AppUserPermission.COURSE_READ,
            AppUserPermission.COURSE_WRITE
        )
    ),
    ADMIN_TRAINEE(
        Sets.newHashSet(
            AppUserPermission.ADMIN_READ,
            AppUserPermission.COURSE_READ
        )
    );

    fun getGrantedAuthorities(): Set<SimpleGrantedAuthority> {
        val grantedAuthorities: HashSet<SimpleGrantedAuthority> = HashSet()
        this.permissions.stream().forEach {
            grantedAuthorities.add(
                SimpleGrantedAuthority(it.permission)
            )
        }
        grantedAuthorities.add(SimpleGrantedAuthority("ROLE_${this.name}"))
        return grantedAuthorities
    }
}