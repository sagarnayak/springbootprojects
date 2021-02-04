package com.sagar.jpa.controller

import com.sagar.jpa.entities.User
import com.sagar.jpa.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userRepository : UserRepository

    @GetMapping
    fun hello(): String {
        userRepository.findAll().forEach {
            println(
                    """
                        user >>
                        ${it.id} ${it.name}
                    """.trimIndent()
            )
        }
        return "Hello There"
    }

    @PostMapping
    fun addUser(){
        userRepository.save(
                User(
                        name = "Sagar Nayak"
                )
        )
    }
}