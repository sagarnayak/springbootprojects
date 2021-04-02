package com.sagar.unittesting.repositories

import com.sagar.unittesting.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
}