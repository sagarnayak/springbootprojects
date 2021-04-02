package com.sagar.unittesting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AssertJTest {

    @Test
    fun testOne() {
        val data = arrayListOf(1, 3, 4, 2, 4)

        assertThat(data).hasSize(5).contains(1, 3).allMatch { it > 0 }
    }
}