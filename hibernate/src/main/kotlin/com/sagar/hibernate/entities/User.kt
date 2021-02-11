package com.sagar.hibernate.entities


import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity(name = "User")
data class User(
        @Id var id: String = UUID.randomUUID().toString(),
        @field:Size(min = 5, max = 8, message = "Please provide min 5, max 8") @field:NotBlank var name: String = "",
        @NotNull var email: String,
        @field:Size(min = 10) var mobileNumber: String
)