package com.sagar.spring.microservice.controller

import com.sagar.spring.microservice.model.Limit
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/limits")
class Hello {

    @GetMapping
    fun hello(): Limit {
        return Limit(2, 657)
    }
}