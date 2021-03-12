package com.sagar.demo.controllers

import com.sagar.demo.entities.TemporaryEntity
import com.sagar.demo.repositories.TemporaryEntityRepository
import com.sagar.demo.services.TemporaryEntityService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(value = [Temp::class])
class TempTest {

    @MockBean
    private lateinit var temporaryEntityService: TemporaryEntityService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testOne() {
        Mockito.`when`(temporaryEntityService.getAllItems())
            .thenReturn(
                arrayListOf(
                    TemporaryEntity(
                        name = "dfbvdfbdfb"
                    ),
                    TemporaryEntity(
                        name = "dfv76dfy7v"
                    )
                )
            )

        val requestBuilder = MockMvcRequestBuilders
            .get("/temp")

        mockMvc
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content()
                    .json("[{name:dfbvdfbdfb},{name:dfv76dfy7v}]")
            )
            .andReturn()
    }
}