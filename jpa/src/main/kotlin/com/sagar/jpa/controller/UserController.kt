package com.sagar.jpa.controller

import com.fasterxml.jackson.databind.ser.FilterProvider
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.sagar.jpa.entities.User
import com.sagar.jpa.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.json.MappingJacksonValue
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var messageSource: MessageSource

    @GetMapping
    fun hello(
        @RequestHeader("Accept-Language", required = false) locale: Locale?
    ): String {
        userRepository.findAll().forEach {
            println(
                """
                        user >>
                        ${it.id} ${it.name}
                    """.trimIndent()
            )
        }
        val message = messageSource.getMessage("morning", null, LocaleContextHolder.getLocale())
        println(
            """
                    Lang >> $message
                """.trimIndent()
        )
        return message
    }

    @GetMapping("/all")
    fun getAllUser(): MappingJacksonValue {
        val mappingJacksonValue = MappingJacksonValue(userRepository.findAll())
        val filter: FilterProvider = SimpleFilterProvider().addFilter(
            "userFilter",
            SimpleBeanPropertyFilter.filterOutAllExcept(
                "name"
            )
        )
        mappingJacksonValue.filters = filter
        return mappingJacksonValue
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
    fun addUser(@Valid @RequestBody user: User): ResponseEntity<JvmType.Object> {
        val userCreated = userRepository.save(
            user
        )

        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.id)
                .toUri()
        ).build()
    }

    @DeleteMapping
    fun deleteAll() {
        userRepository.deleteAll()
    }
}