package com.sagar.unittesting.services

import com.sagar.unittesting.entities.User
import com.sagar.unittesting.repositories.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    lateinit var userRepository: UserRepository

    @InjectMocks
    lateinit var userService: UserService

    @Test
    fun testOne() {
        Mockito.`when`(userRepository.findAll()).thenReturn(
                arrayListOf(
                        User(1, "tester01@testing.com", "tester01"),
                        User(2, "tester02@testing.com", "tester02"),
                        User(3, "tester03@testing.com", "tester03")
                )
        )

        val result = userService.getAllUser()

        Assertions.assertEquals(3, result.size)

        result.forEach {
            Assertions.assertEquals(
                    "_",
                    it.name.substring(0, 1)
            )
        }
    }
}