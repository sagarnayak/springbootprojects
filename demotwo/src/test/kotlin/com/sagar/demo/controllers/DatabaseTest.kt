package com.sagar.demo.controllers

import com.sagar.demo.entities.Student
import com.sagar.demo.repositories.StudentRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(value = [Database::class])
class DatabaseTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var studentRepository: StudentRepository

    @Test
    fun testMocking() {
        Mockito.`when`(studentRepository.findAll())
                .thenReturn(arrayListOf(Student(first_name = "sagar", last_name = "kumar")))

        val requestBuilder = MockMvcRequestBuilders
                .get("/database")

        mockMvc
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().json("[{first_name:sagar,last_name:kumar}]"))
                .andReturn()
    }
}