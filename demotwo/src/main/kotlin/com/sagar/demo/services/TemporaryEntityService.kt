package com.sagar.demo.services

import com.sagar.demo.entities.TemporaryEntity
import com.sagar.demo.repositories.TemporaryEntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TemporaryEntityService {

    @Autowired
    private lateinit var temporaryEntityRepository: TemporaryEntityRepository

    fun getAllItems(): MutableList<TemporaryEntity> {
        val result = temporaryEntityRepository.findAll()
        result.forEach {
            it?.let { tempEntity ->
                tempEntity.name += "_updated"
            }
        }
        return result
    }
}