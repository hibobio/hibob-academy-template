package com.hibob.academy.resource

import com.hibob.academy.types.Pets
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller
import jakarta.ws.rs.core.MediaType
import org.springframework.web.bind.annotation.RequestBody

@Controller
@Path("api/pets")
@Produces(MediaType.APPLICATION_JSON)
class PetsResource {

    @POST
    @Path("/{petId}/type")
    @Consumes(MediaType.APPLICATION_JSON)
    fun createPet(@RequestBody pets: Pets): Response {
        Response.status(Response.Status.OK).build()
        return Response.ok("POST OK").build()
    }

    @GET
    @Path("/{petId}/type")
    fun getPetType(@PathParam("petId") id: String): Response {
        Response.status(Response.Status.OK).build()
        return Response.ok("GET OK").build()
    }

    @PUT
    @Path("/{petId}/type")
    @Consumes(MediaType.APPLICATION_JSON)
    fun updatePetType(@PathParam("petId") id: String, petType: String): Response {
        Response.status(Response.Status.OK).build()
        return Response.ok("PUT OK").build()
    }

    @DELETE
    @Path("/{petId}/type")
    fun deletePetType(@PathParam("petId") id: String): Response {
        Response.status(Response.Status.OK).build()
        return Response.ok("DELETE OK").build()
    }
}