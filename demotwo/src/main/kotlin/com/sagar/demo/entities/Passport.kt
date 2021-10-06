package com.sagar.demo.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity(name = "passport")
data class Passport(
        @Id
        var id: String = UUID.randomUUID().toString(),
        @field:NotNull
        @field:NotBlank(message = "Please provide passport number")
        @field:Size(min = 5, message = "Please provide passport number")
        @Column(nullable = false)
        var number: String = "",
        @OneToOne(mappedBy = "passport", fetch = FetchType.LAZY)
        var student: Student? = null,
        @field:NotNull
        @CreationTimestamp
        var createdOn: Timestamp = Timestamp(Calendar.getInstance().timeInMillis),
        @field:NotNull
        @UpdateTimestamp
        var updatedOn: Timestamp = Timestamp(Calendar.getInstance().timeInMillis)
)