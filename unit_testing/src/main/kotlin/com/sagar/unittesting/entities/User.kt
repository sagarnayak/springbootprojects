package com.sagar.unittesting.entities

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(
        @Id  var id: Int=0,
        var emailId: String="",
        var name: String=""
)