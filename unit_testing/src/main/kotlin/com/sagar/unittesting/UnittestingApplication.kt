package com.sagar.unittesting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UnittestingApplication

fun main(args: Array<String>) {
    runApplication<UnittestingApplication>(*args)
}
