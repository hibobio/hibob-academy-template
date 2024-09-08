package com.hibob.academy.resource
import com.hibob.kotlinEx.Pet
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller


@Controller
@Path("/api/gilad/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PetResource() {

    @GET
    fun get(): Response {
        return Response.ok(listOf(Pet(1, "Murphy", "dog", 1, 20240908), Pet(2, "Pepe", "dog", 1, 20240909))).build()
    }

    @POST
    fun post(pet: Pet): Response {
        return Response.ok("Posted").build()
    }

    @PUT
    fun put(pet: Pet): Response {
        return Response.ok("Put").build()
    }

    @DELETE
    fun delete(pet: Pet): Response {
        return Response.ok("Deleted").build()
    }
}

//data class ExampleResponse(val data: Example)
