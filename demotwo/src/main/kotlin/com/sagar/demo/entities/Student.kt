package com.sagar.demo.entities

import com.fasterxml.jackson.annotation.JsonFilter
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.collections.ArrayList

@Entity(name = "student")
@JsonFilter("someFilter")
data class Student(
        @Id
        var id: String = UUID.randomUUID().toString(),
        @field:NotNull
        @field:NotBlank(message = "Please provide first name")
        @field:Size(min = 5, message = "Please provide first name")
        @Column(nullable = false)
        var first_name: String = "",
        @field:NotNull
        @field:NotBlank(message = "Please provide last name")
        @field:Size(min = 5, message = "Please provide last name")
        @Column(nullable = false)
        var last_name: String = "",
        var email: String? = null,
        @field:NotNull(message = "Please provide gender")
        @field:NotBlank(message = "Please provide gender")
        @field:Size(min = 1, message = "Please provide gender")
        @Column(nullable = false)
        var gender: String = "",
        @OneToOne
        var passport: Passport? = null,
        @ManyToMany
        @JoinTable(
                name = "student_course_join",
                joinColumns = [JoinColumn(name = "student_id")],
                inverseJoinColumns = [JoinColumn(name = "course_id")]
        )
        var courses: List<Course> = ArrayList(),
        @field:NotNull
        @CreationTimestamp
        var createdOn: Timestamp = Timestamp(Calendar.getInstance().timeInMillis),
        @field:NotNull
        @UpdateTimestamp
        var updatedOn: Timestamp = Timestamp(Calendar.getInstance().timeInMillis)
)