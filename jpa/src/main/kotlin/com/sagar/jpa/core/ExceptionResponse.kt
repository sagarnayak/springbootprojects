package com.sagar.jpa.core

import java.util.*

data class ExceptionResponse(
        var message: String = "",
        var timeStamp: Date = Date()
)