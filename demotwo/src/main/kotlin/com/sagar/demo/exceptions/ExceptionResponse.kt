package com.sagar.demo.exceptions

import java.util.*

data class ExceptionResponse(
        var message: String = "",
        var timeStamp: Date = Date()
)