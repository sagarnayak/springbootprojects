package com.sagar.demo.repositories

import com.sagar.demo.entities.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface StudentRepository : JpaRepository<Student, String> {
    fun findByEmail(email: String): List<Student>

    @Query("select u from student u")
    fun customQuery(): List<Student>

    @Query("select s from student s where s.id = :id")
    fun getStudentWithId(id: String): List<Student>
}