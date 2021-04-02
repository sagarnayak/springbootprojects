package com.sagar.unittesting

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class BusinessTest {

    @Mock
    private lateinit var dataService: DataService

    @InjectMocks
    private lateinit var business: Business

    @Test
    fun testForAddOne() {
        val result = Business().calculateSum(arrayListOf(1, 2, 3, 3, 3, 5, 34, 3, 43, 4, 34, 3, 4))
        Assertions.assertEquals(142, result)
    }

    @Test
    fun testTwo() {
        `when`(dataService.getNumbersToAdd()).thenReturn(arrayListOf(1, 4, 45, 654, 454, 54, 54))
        val result = business.calculateSumUsingWayTwo()
        Assertions.assertEquals(1266, result)
    }
}