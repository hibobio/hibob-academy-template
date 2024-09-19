package com.hibob.academy.resource

import com.hibob.academy.dao.OwnerDataInsert
import com.hibob.academy.service.OwnerService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller

@Controller
@Path("/api/owner")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class OwnerResource(private val ownerService: OwnerService) {

    @GET
    @Path("/{companyId}")
    fun getAllOwners(companyId: Long): Response {
        val allOwners = ownerService.getAllOwners(companyId)
        return Response.ok(allOwners).build()
    }

    @POST
    fun creatOwner(owner: OwnerDataInsert): Response {
        ownerService.createOwnerIfNotExists(owner)
        return Response.ok().entity("new owner created").build()
    }

    @GET
    @Path("/{petId}/{companyId}")
    fun getOwnerByPetId(@PathParam("petId") petId: Long, @PathParam("companyId") companyId: Long): Response {
        val owner = ownerService.getOwnerByPetId(companyId, petId)
        return Response.ok(owner).build()
    }
}