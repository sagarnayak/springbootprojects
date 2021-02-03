package com.sagar.jpa.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "User")
data class User(
        @Id var id: String = UUID.randomUUID().toString(),
        var name: String = ""
)