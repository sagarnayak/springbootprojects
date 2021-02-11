package com.sagar.hibernate.repositories

import com.sagar.hibernate.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {}