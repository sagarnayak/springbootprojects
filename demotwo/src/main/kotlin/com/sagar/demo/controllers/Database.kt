package com.sagar.demo.controllers

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.sagar.demo.entities.Student
import com.sagar.demo.repositories.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.json.MappingJacksonValue
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/database")
class Database {

    @Autowired
    private lateinit var studentRepository: StudentRepository

    @GetMapping
    fun getUsers(): MappingJacksonValue {
        val students = studentRepository.findAll()
        val mappingJacksonValueClass = MappingJacksonValue(students)
        val filter = SimpleFilterProvider().addFilter(
            "someFilter",
            SimpleBeanPropertyFilter.filterOutAllExcept("first_name", "last_name")
        )
        mappingJacksonValueClass.filters = filter
        print(students.toString())
        return mappingJacksonValueClass
    }

    @PostMapping
    fun addUser(@Valid @RequestBody student: Student): ResponseEntity<Student> {
        val savedStudent = studentRepository.save(student)
        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.id).toUri()
        ).build()
    }
}