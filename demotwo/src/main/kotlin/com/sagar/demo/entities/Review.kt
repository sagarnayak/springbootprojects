package com.sagar.demo.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
data class Review(
    @Id
    var id: String = UUID.randomUUID().toString(),
    @ManyToOne
    var course: Course? = null,
    @field:NotNull
    @field:NotBlank(message = "Please provide description")
    @field:Size(min = 5, message = "Please provide description")
    @Column(nullable = false)
    var description: String = "",
    @field:NotNull
    @CreationTimestamp
    var createdOn: Timestamp = Timestamp(Calendar.getInstance().timeInMillis),
    @field:NotNull
    @UpdateTimestamp
    var updatedOn: Timestamp = Timestamp(Calendar.getInstance().timeInMillis)
)