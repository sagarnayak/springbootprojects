package com.sagar.jpa.repository

import com.sagar.jpa.entities.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository : JpaRepository<Admin, String> {}