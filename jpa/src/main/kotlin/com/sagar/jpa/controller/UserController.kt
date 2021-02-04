package com.sagar.jpa.controller

import com.sagar.jpa.entities.User
import com.sagar.jpa.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userRepository: UserRepository

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

    @GetMapping("/{id}")
    fun findUser(@PathVariable id: String): User {
        userRepository.findById(id).apply {
            if (this.isPresent) {
                return this.get()
            } else {
                throw Exception("error")
            }
        }
    }

    @PostMapping
    fun addUser(): ResponseEntity<JvmType.Object> {
        val user = userRepository.save(
                User(
                        name = "Sagar Nayak"
                )
        )

        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(user.id)
                        .toUri()
        ).build()
    }
}