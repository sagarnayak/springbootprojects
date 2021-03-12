package com.sagar.demo.repositories

import com.sagar.demo.entities.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, String>