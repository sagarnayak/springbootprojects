package com.sagar.unittesting

import com.jayway.jsonpath.JsonPath
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JsonPathTest {

    @Test
    fun testOne() {
        val responseFromService = "[" +
                "{\"id\":1,\"name\":\"user1\",\"quantity\":34}," +
                "{\"id\":2,\"name\":\"user2\",\"quantity\":34}," +
                "{\"id\":3,\"name\":\"user3\",\"quantity\":34}" +
                "]"

        val docContext = JsonPath.parse(responseFromService)
        val length = docContext.read<Int>("$.length()")
        assertThat(length).isEqualTo(3)
        val ids = docContext.read<ArrayList<Int>>("$..id")
        assertThat(ids).contains(1, 2, 3)
    }
}