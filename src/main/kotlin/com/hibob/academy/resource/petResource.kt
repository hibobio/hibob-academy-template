package com.hibob.academy.resource
import com.hibob.kotlinEx.Owner
import com.hibob.kotlinEx.Pet
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller
import java.sql.Date


@Controller
@Path("/api/gilad/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PetResource() {

    //    temporary database simulation
    private val allPets: MutableList<Pet> = mutableListOf()

    //
    @GET
    @Path("/{petId}")
    fun getPet(@PathParam("petId") petId: Int): Response {
        val pet = allPets.find { pet: Pet -> pet.id == petId }
        pet?.let {
            return Response.ok(pet).build()
        } ?: return Response.status(Response.Status.NOT_FOUND).entity("pet not found").build()
    }


    @POST
    fun postPet(pet: Pet): Response {
        val firstname : String = pet.name.split(" ").first()
        val lastname = pet.name.split(" ").last()
        allPets.add(pet.copy(id = pet.id, name = pet.name, firstname = firstname, lastname = lastname ,type = pet.type, companyId = pet.companyId, dateOfArrival = pet.dateOfArrival))
        return Response.status(Response.Status.CREATED).entity(Response.Status.CREATED).build()
    }

    @PUT
    @Path("/{petId}")
    fun putPet(@PathParam("petId") petId: Int, pet: Pet): Response {
        val index = allPets.indexOfFirst { pet -> pet.id == petId }
        if (index >= 0) {
            val petToUpdate = allPets.removeAt(index).copy(id = pet.id, name = pet.name, type = pet.type, companyId = pet.companyId, dateOfArrival = pet.dateOfArrival)
            allPets.add(petToUpdate)
            return Response.ok(petToUpdate).build()
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("pet not found").build()
        }
    }

    @DELETE
    @Path("/{petId}")
    fun deletePet(@PathParam("petId") petId: Int, pet: Pet): Response {
        val index = allPets.indexOfFirst { pet -> pet.id == petId }
        if (index >= 0) {
            allPets.removeAt(index)
            return Response.ok(pet).build()
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("pet not found").build()
        }
    }
}

//data class ExampleResponse(val data: Example)
