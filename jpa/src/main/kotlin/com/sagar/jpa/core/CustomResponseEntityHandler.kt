package com.sagar.jpa.core

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@RestController
class CustomResponseEntityHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    public final fun handleAllException(ex: Exception, webRequest: WebRequest): ResponseEntity<Any> {
        return ResponseEntity(
                ExceptionResponse(
                        "error..."
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}