package com.sagar.spring.microservice.controller

import com.sagar.spring.microservice.configuration.Configuration
import com.sagar.spring.microservice.model.Limit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/limits")
class Hello {

    @Autowired
    lateinit var configuration: Configuration

    @Autowired
    lateinit var environment: Environment

    @GetMapping
    fun hello(): Limit {
        return Limit(configuration.minimum, configuration.maximum, environment.getProperty("local.server.port")!!)
    }
}