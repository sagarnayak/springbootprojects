package com.sagar.restapis.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class Launcher {

    @GetMapping
    fun launch():String{
        return "Hello There!! Welcome to rest apis app."
    }
}