package com.sagar.unittesting.controller

import com.sagar.unittesting.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloWorldController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun hello() = userService.getUserData()

    @GetMapping("/all")
    fun getAll() = userService.getAllUser()
}