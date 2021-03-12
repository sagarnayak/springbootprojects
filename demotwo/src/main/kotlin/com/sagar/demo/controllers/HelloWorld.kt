package com.sagar.demo.controllers

import com.sagar.demo.entities.Review
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloWorld {

    @GetMapping
    fun hi(): String {
        return "Hello"
    }

    @GetMapping("/item")
    fun getItem(): Review {
        return Review(
            description = "testing..."
        )
    }
}