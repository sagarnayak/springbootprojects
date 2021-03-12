package com.sagar.demo.controllers

import com.sagar.demo.entities.Student
import com.sagar.demo.repositories.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/database")
class Database {

    @Autowired
    private lateinit var studentRepository: StudentRepository

    @GetMapping
    fun getUsers(): MutableList<Student> {
        val students = studentRepository.findByEmail("sagar@gmail.com")
        print(students.toString())
        return studentRepository.findAll()
    }

    @PostMapping
    fun addUser(@Valid @RequestBody student: Student): ResponseEntity<Student> {
        val savedStudent = studentRepository.save(student)
        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.id).toUri()
        ).build()
    }
}