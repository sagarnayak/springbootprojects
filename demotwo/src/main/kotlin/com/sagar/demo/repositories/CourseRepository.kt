package com.sagar.demo.repositories

import com.sagar.demo.entities.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, String>