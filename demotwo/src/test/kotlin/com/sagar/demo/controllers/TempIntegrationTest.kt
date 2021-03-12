package com.sagar.demo.controllers

import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TempIntegrationTest {

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun getTempItems() {
        val result = testRestTemplate.getForObject(
            "/temp",
            String::class.java
        )
        JSONAssert.assertEquals(
            "[{id:d78f6v9od8ifvyd},{id:d78f6v9od8idfvdfvfvyd},{id:d78f6v9odfvdfvdfvdfvd8ifvyd},{id:dfvdfvdfvdfvdfv},{id:fdvdfvdfvdf4564}]",
            result,
            false
        )
    }
}