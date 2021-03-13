package com.sagar.demo

import com.sagar.demo.security.entity.JWTConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableConfigurationProperties(
    JWTConfig::class
)
class DemotwoApplication

fun main(args: Array<String>) {
    runApplication<DemotwoApplication>(*args)
}
