package com.hibob.academy.service

import com.hibob.academy.dao.*
import jakarta.ws.rs.BadRequestException
import org.springframework.stereotype.Component
import java.util.*

@Component
class PetService(private val petDao: PetDao) {

    fun petsByType(type: String, companyId: Long): List<Pet> {
        return petDao.petsByType(type, companyId)
    }

    fun getPetsByOwnerId(ownerId: UUID, companyId: Long): List<Pet> {
        return petDao.getPetsByOwnerId(ownerId, companyId)
    }

    fun createPet(pet: PetNoId): UUID {
        return petDao.createPet(pet)
    }

    fun assignOwnerIdToPet(petId: UUID, ownerId: UUID) {
        if (petDao.assignOwnerIdToPet(petId, ownerId) == 0) {
            throw BadRequestException("No pet with that id")
        }
    }

    fun countPetsByType(companyId: Long): Map<String, Int> {
        return petDao.countPetsByType(companyId)
    }
}