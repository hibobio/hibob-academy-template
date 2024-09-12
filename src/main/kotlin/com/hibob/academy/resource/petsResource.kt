package com.hibob.academy.resource

import com.hibob.academy.types.Pets
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller
import jakarta.ws.rs.core.MediaType
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

@Controller
@Path("api/pets")
@Produces(MediaType.APPLICATION_JSON)
class PetsResource {

    @POST
    @Path("/{petId}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun createPet(@RequestBody pets: Pets): Response {
        return Response.ok(pets).build()
    }

    @GET
    @Path("/type/{petId}")
    fun getPetType(@PathParam("petId") id: String): Response {
        return Response.ok("lab").build()
    }

    @PUT
    @Path("/type/{petId}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun updatePetType(@PathParam("petId") id: String, @QueryParam("newType") newType: String?): Response {
        val pet = Pets(
            name = "dog", type = "lab",
            companyId = UUID.randomUUID(), dateofArrival = Date()
        )
        pet.type = newType ?: pet.type
        return Response.ok(pet).build()
    }

    @DELETE
    @Path("/{petId}")
    fun deletePet(@PathParam("petId") id: String): Response {
        return Response.ok("DELETE OK").build()
    }
}