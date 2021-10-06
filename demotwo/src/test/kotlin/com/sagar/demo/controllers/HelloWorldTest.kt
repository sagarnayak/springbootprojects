package com.sagar.demo.controllers

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(value = [HelloWorld::class])
class HelloWorldTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testOne() {
        val requestBuilder = MockMvcRequestBuilders
                .get("/hello")

        mockMvc
                .perform(requestBuilder)
                .andExpect(status().isOk)
                .andExpect(content().string("Hello"))
                .andReturn()
    }

    @Test
    fun testTwo() {
        val requestBuilder = MockMvcRequestBuilders
                .get("/hello/item")

        mockMvc
                .perform(requestBuilder)
                .andExpect(status().isOk)
                .andExpect(content().json("{\"description\":\"testing...\"}"))
                .andReturn()
    }
}