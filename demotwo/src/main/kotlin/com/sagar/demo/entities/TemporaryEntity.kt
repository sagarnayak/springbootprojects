package com.sagar.demo.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id


@Entity
data class TemporaryEntity(
    @Id var id: String = UUID.randomUUID().toString(),
    var name: String = ""
)