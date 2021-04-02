package com.sagar.unittesting

import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun testOne() {
        val result = testRestTemplate.getForObject("/all-items-from-database", String::class.java)
        JSONAssert.assertEquals(
                "[{name:tester01},{name:tester02},{name:tester03}]",
                result,
                false
        )
    }
}