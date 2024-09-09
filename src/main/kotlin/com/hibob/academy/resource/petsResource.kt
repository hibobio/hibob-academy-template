package com.hibob.academy.resource

import com.hibob.academy.types.Owner
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
    @Path("/type")
    @Consumes(MediaType.APPLICATION_JSON)
    fun createPet(@RequestBody pets: Pets): Response {
        return Response.ok("POST OK").build()
    }

    @GET
    @Path("/{petId}/type")
    fun getPetType(@PathParam("petId") id: String): Response {
        return Response.ok("GET OK").build()
    }

    fun fetchPet(id: String): Owner? {
        return null     //Just for Practice
    }

    @PUT
    @Path("/{petId}/type")
    @Consumes(MediaType.APPLICATION_JSON)
    fun updatePetType(@PathParam("petId") id: String, petType: String): Response {
        if(fetchPet(id) == null) {
            return Response.status(404).build()
        }
        return Response.ok("PUT OK").build()
    }

    @DELETE
    @Path("/{petId}/type")
    fun deletePet(@PathParam("petId") id: String): Response {
        return Response.ok("DELETE OK").build()
    }
}