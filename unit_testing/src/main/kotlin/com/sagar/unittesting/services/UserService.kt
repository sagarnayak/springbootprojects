package com.sagar.unittesting.services

import com.sagar.unittesting.entities.User
import com.sagar.unittesting.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun getUserData() = User(1, "testemail@gmail.com", "sagar")

    fun getAllUser(): MutableList<User> {
        val results = userRepository.findAll()
        results.forEach {
            it.name = "_${it.name}"
        }
        return results
    }
}