package com.sagar.demo.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.collections.ArrayList

@Entity
data class Course(
        @Id
        var id: String = UUID.randomUUID().toString(),
        @field:NotNull
        @field:NotBlank(message = "Please provide name")
        @field:Size(min = 5, message = "Please provide name")
        @Column(nullable = false)
        var name: String = "",
        @OneToMany(mappedBy = "course")
        var reviews: List<Review> = ArrayList(),
        @ManyToMany(mappedBy = "courses")
        var students: List<Student> = ArrayList(),
        @field:NotNull
        @CreationTimestamp
        var createdOn: Timestamp = Timestamp(Calendar.getInstance().timeInMillis),
        @field:NotNull
        @UpdateTimestamp
        var updatedOn: Timestamp = Timestamp(Calendar.getInstance().timeInMillis)
)