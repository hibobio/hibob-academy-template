package com.hibob.academy.resource

import com.hibob.academy.dao.Owner
import com.hibob.academy.dao.OwnerNoId
import com.hibob.academy.service.OwnerService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID

@Component
@Path("/owner")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class OwnerResource(
    private val ownerService: OwnerService
) {
    @GET
    @Path("/{companyId}")
    fun getOwners(@PathParam("companyId") companyId: Long): Response {
        return Response.ok(ownerService.getOwners(companyId = companyId)).build()
    }

    @GET
    @Path("/{companyId}/{petId}")
    fun getOwnerByPetId(@PathParam("companyId") companyId: Long, @PathParam("petId") petId: UUID): Response {
        return Response.ok(ownerService.getOwnerByPetId(petId, companyId)).build()
    }

    @POST
    fun createOwner(@RequestBody owner: OwnerNoId): Response {
        val ownerId = ownerService.createOwner(owner)
        return Response.ok(ownerId).build()
    }


    @DELETE
    @Path("/{ownerId}/remove")
    fun deleteOwner(@PathParam("ownerId") id: String): Response {
        return Response.ok("DELETE OK").build()
    }
}