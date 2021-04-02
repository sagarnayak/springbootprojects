package com.sagar.unittesting

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItems
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test

class HamcrestMatcherTest {

    @Test
    fun testOne() {
        val data = arrayListOf(1, 3, 4, 2, 4)

        assertThat(data, hasSize(5))
        assertThat(data, hasItems(1, 3))
    }
}