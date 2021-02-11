package com.sagar.hibernate.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class Welcome {

    @GetMapping
    fun hello(): String {
        return "Hello there"
    }
}