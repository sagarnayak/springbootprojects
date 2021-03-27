package com.sagar.spring.microservice.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("limits-service")
data class Configuration(
    var minimum: Int = 0,
    var maximum: Int = 0
)