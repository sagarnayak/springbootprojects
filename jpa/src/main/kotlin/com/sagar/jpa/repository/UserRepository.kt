package com.sagar.jpa.repository

import com.sagar.jpa.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {

    fun findByName(name: String): User
}