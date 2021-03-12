package com.sagar.demo

import com.sagar.demo.entities.Student
import com.sagar.demo.repositories.StudentRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class DemotwoApplicationTests {

    @Mock
    lateinit var studentRepository: StudentRepository

    @Test
    fun testOne() {
        Mockito.`when`(studentRepository.findAll()).thenReturn(arrayListOf(Student(),Student()))
        val result = studentRepository.findAll()
        Assertions.assertNotNull(result)
    }
}