package com.sagar.jpa.entities

import com.fasterxml.jackson.annotation.JsonFilter
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity(name = "User")
@ApiModel("This is the user model")
@JsonFilter("userFilter")
data class User(
    @ApiModelProperty("This is the id") @Id var id: String = UUID.randomUUID().toString(),
    @field:Size(min = 5, max = 8, message = "Please provide min 5, max 8") @field:NotBlank var name: String = ""
)