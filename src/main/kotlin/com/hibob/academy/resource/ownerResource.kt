package com.hibob.academy.resource
import com.hibob.kotlinEx.Owner
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller


@Controller
@Path("/api/gilad/owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class OwnerResource() {

    @GET
    fun get(): Response {
        return Response.ok(listOf(Owner(1, "Gilad", 1, 1), Owner(2, "Gali", 1, 2))).build()
    }

    @POST
    fun post(owner: Owner): Response {
        return Response.ok("Posted").build()
    }

    @PUT
    fun put(owner: Owner): Response {
        return Response.ok("Put").build()
    }

    @DELETE
    fun delete(owner: Owner): Response {
        return Response.ok("Deleted").build()
    }
}
