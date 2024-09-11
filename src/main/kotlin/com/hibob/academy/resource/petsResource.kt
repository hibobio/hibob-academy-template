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
    @Path("/{petId}/type")
    @Consumes(MediaType.APPLICATION_JSON)
    fun createPet(@RequestBody pets: Pets): Response {
        Response.status(Response.Status.OK).build()
        return Response.ok(pets).build()
    }

    @GET
    @Path("/getPetType/{petId}")
    fun getPetType(@PathParam("petId") id: String): Response {
        Response.status(Response.Status.OK).build()
        return Response.ok("lab").build()
    }

    @PUT
    @Path("/updateType/{petId}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun updatePetType(@PathParam("petId") id: String, @QueryParam("newType") newType: String?): Response {
        val pet = Pets(
            name = "dog", type = "lab",
            company_id = UUID.randomUUID(), date_of_arrival = Date()
        )
        pet.type = newType ?: pet.type
        Response.status(Response.Status.OK).build()
        return Response.ok(pet).build()
    }

    @DELETE
    @Path("/deletePetType/{petId}/type")
    fun deletePetType(@PathParam("petId") id: String): Response {
        Response.status(Response.Status.OK).build()
        return Response.ok("DELETE OK").build()
    }
}