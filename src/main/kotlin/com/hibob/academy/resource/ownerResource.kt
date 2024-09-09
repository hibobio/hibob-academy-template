package com.hibob.academy.resource

import com.hibob.academy.types.Owner
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller
import jakarta.ws.rs.core.MediaType
import org.springframework.web.bind.annotation.RequestBody

@Controller
@Path("api/owners")
@Produces(MediaType.APPLICATION_JSON)
class OwnerResource {

    @POST
    @Path("/type")
    @Consumes(MediaType.APPLICATION_JSON)
    fun createOwner(@RequestBody owner: Owner): Response {
        return Response.ok("POST OK").build()
    }

    @GET
    @Path("/{Id}/type")
    fun getOwnersPetType(@PathParam("Id") id: String): Response {
        return Response.ok("GET OK").build()
    }

    fun fetchOwner(id: String): Owner? {
        return null     //Just for Practice
    }

    @PUT
    @Path("/{Id}/type")
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateOwnersPetType(@PathParam("Id") id: String, ownersPetType: String): Response {
        if(fetchOwner(id) == null){
            return Response.status(404).build()
        }
        return Response.ok("PUT OK").build()
    }

    @DELETE
    @Path("/{Id}/type")
    fun deleteOwner(@PathParam("Id") id: String): Response {
        return Response.ok("DELETE OK").build()
    }
}