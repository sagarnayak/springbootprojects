package com.sagar.jpa.controller

import com.sagar.jpa.entities.Admin
import com.sagar.jpa.repository.AdminRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@RestController
@RequestMapping("/admin")
class AdminController {

    @Autowired
    private lateinit var adminRepository: AdminRepository

    @GetMapping
    public fun getAll(): MutableList<Admin> {
        return adminRepository.findAll()
    }

    @PostMapping
    public fun addAdmin(@Valid @RequestBody admin: Admin): ResponseEntity<JvmType.Object> {
        admin.id = UUID.randomUUID().toString()
        val createdAdmin = adminRepository.save(
            admin
        )

        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAdmin.id)
                .toUri()
        ).build()
    }

    @DeleteMapping
    public fun deleteAll() {
        adminRepository.deleteAll()
    }
}