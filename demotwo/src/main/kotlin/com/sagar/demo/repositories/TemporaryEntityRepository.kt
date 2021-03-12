package com.sagar.demo.repositories

import com.sagar.demo.entities.TemporaryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TemporaryEntityRepository : JpaRepository<TemporaryEntity, String> {

    fun findByName(name: String): List<TemporaryEntity>
}