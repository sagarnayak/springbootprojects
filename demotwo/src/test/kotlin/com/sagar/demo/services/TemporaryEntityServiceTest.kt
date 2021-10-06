package com.sagar.demo.services

import com.sagar.demo.entities.TemporaryEntity
import com.sagar.demo.repositories.TemporaryEntityRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class TemporaryEntityServiceTest {

    @InjectMocks
    lateinit var temporaryEntityService: TemporaryEntityService

    @Mock
    private lateinit var temporaryEntityRepository: TemporaryEntityRepository

    @Test
    fun testOne() {
        Mockito.`when`(temporaryEntityRepository.findAll()).thenReturn(
                arrayListOf(
                        TemporaryEntity(
                                name = "dfbvdfbdfb"
                        ),
                        TemporaryEntity(
                                name = "dfv76dfy7v"
                        )
                )
        )
        val result = temporaryEntityService.getAllItems()
        Assertions.assertNotNull(result)
    }
}