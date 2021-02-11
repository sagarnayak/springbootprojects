package com.sagar.hibernate.controllers

import com.sagar.hibernate.entities.User
import com.sagar.hibernate.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import kotlin.collections.ArrayList

@RestController
@RequestMapping("/user")
class User {

    @Autowired
    private lateinit var userRepository: UserRepository

    @PostMapping
    fun addUser(@Valid @RequestBody user: User) {
        user.id = UUID.randomUUID().toString()
        userRepository.save(user)
    }

    @GetMapping
    fun getUsers(): ArrayList<User> {
        val users : ArrayList<User> = userRepository.findAll() as ArrayList<User>
        println(
                "useres length : ${users.size}"
        )
        users.forEach {
            println("User :: ${it.id} <<>> ${it.name}")
        }
        return users
    }
}