package com.sagar.demo.controllers

import com.sagar.demo.core.SendEmail
import com.sagar.demo.entities.Review
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloWorld {

    @Autowired
    private lateinit var sendEmail: SendEmail

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

    @GetMapping("/sendEmail")
    fun sendEmail(): String {
        sendEmail.sendEmail()
        return "sent email"
    }
}