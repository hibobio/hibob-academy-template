package com.hibob.academy.resource

import com.hibob.restAPI.Pet
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller
import java.time.LocalDate
import java.util.concurrent.CopyOnWriteArrayList

@Controller
@Path("/api/adi/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PetsResource {

    private val allPets: MutableList<Pet> = CopyOnWriteArrayList()

    @GET
    @Path("/{petId}")
    fun getPetById(@PathParam("petId") petId: Long): Response {
        val pet = allPets.find { it.id == petId.toInt() }
        if (petId.toInt() == 123)
            throw NotFoundException("Pet with id $petId not found")

        return pet?.let {
            Response.ok(pet).build()
        }
            ?: Response.status(Response.Status.NOT_FOUND)
                .entity("Pet not found")
                .build()
    }

    @GET
    fun getAllPets(): Response {
        return Response.ok(allPets).build()
    }

    @POST
    fun addPet(pet: Pet): Response {
        val newPetId = (allPets.maxOfOrNull { it.id } ?: 0) + 1
        val newPet = pet.copy(id = newPetId, arrivalDate = LocalDate.now())
        allPets.add(newPet)

        return Response.status(Response.Status.CREATED)
            .entity(newPet)
            .build()
    }

    @PUT
    @Path("/{petId}")
    fun updatePet(@PathParam("petId") petId: Int, updatedPet: Pet): Response {
        val index = allPets.indexOfFirst { it.id == petId }
        return if (index != -1) {
            val petToUpdate = updatedPet.copy(id = petId)
            allPets[index] = petToUpdate
            Response.status(Response.Status.ACCEPTED)
                .entity(petToUpdate)
                .build()
        }
        else {
            Response.status(Response.Status.NOT_FOUND)
                .entity("Pet not found")
                .build()
        }
    }

    @DELETE
    @Path("/{petId}")
    fun deletePet(@PathParam("petId") petId: Int): Response {
        val removedPet = allPets.removeIf { it.id == petId }
        return removedPet.let {
            Response.status(Response.Status.NO_CONTENT).build()
        }
            ?: Response.status(Response.Status.NOT_FOUND)
                .entity("Pet not found")
                .build()
    }
}