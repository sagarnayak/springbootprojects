package com.sagar.demo.repositories

import com.sagar.demo.entities.Passport
import org.springframework.data.jpa.repository.JpaRepository

interface PassportRepository : JpaRepository<Passport, String>