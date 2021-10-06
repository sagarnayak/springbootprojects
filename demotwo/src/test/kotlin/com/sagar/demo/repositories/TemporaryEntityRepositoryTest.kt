package com.sagar.demo.repositories

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class TemporaryEntityRepositoryTest {

    @Autowired
    private lateinit var temporaryEntityRepository: TemporaryEntityRepository

    @Test
    fun testOne() {
        val result = temporaryEntityRepository.findAll()
        Assertions.assertEquals(
                result.size,
                5
        )
    }
}