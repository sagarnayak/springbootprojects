package com.sagar.demo.controllers

import com.sagar.demo.entities.TemporaryEntity
import com.sagar.demo.services.TemporaryEntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/temp")
class Temp {

    @Autowired
    private lateinit var temporaryEntityService: TemporaryEntityService

    @GetMapping
    fun getTempData(): MutableList<TemporaryEntity> {
        return temporaryEntityService.getAllItems()
    }
}