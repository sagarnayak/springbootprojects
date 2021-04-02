package com.sagar.unittesting.controller

import com.sagar.unittesting.entities.User
import com.sagar.unittesting.services.UserService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(value = [HelloWorldController::class])
class HelloWorldControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var userService: UserService

    @Test
    fun testOne() {
        Mockito.`when`(userService.getUserData()).thenReturn(User(2,"testemail@gmail.com", "sagar"))

        val result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/hello")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk)
                .andReturn()

        JSONAssert.assertEquals(
                "{emailId:testemail@gmail.com,name:sagar}",
                result.response.contentAsString,
                false)
    }

    @Test
    fun testTwo(){
        Mockito.`when`(userService.getAllUser()).thenReturn(
                arrayListOf(
                        User(1,"tester01@testing.com","tester01"),
                        User(2,"tester02@testing.com","tester02"),
                        User(3,"tester03@testing.com","tester03")
                )
        )

        val result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/hello/all")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk)
                .andReturn()

        JSONAssert.assertEquals(
                "[{name:tester01},{name:tester02},{name:tester03}]",
                result.response.contentAsString,
                false)
    }
}