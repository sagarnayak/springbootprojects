package com.sagar.demo.exceptions

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
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

    override fun handleMethodArgumentNotValid(
            ex: MethodArgumentNotValidException,
            headers: HttpHeaders,
            status: HttpStatus,
            request: WebRequest
    ): ResponseEntity<Any> {
        var errorMessage = "Bad Req"
        ex.bindingResult.fieldErrors.forEach {
            errorMessage = it.defaultMessage ?: "Bad Req"
        }
        return ResponseEntity(
                ExceptionResponse(
                        errorMessage
                ),
                HttpStatus.BAD_REQUEST
        )
    }

    override fun handleHttpMessageNotReadable(
            ex: HttpMessageNotReadableException,
            headers: HttpHeaders,
            status: HttpStatus,
            request: WebRequest
    ): ResponseEntity<Any> {
        val errorMessage = "Invalid arguments"
        return ResponseEntity(
                ExceptionResponse(
                        errorMessage
                ),
                HttpStatus.BAD_REQUEST
        )
    }
}