package com.hibob.academy.resource

import com.hibob.academy.dao.OwnerDao
import com.hibob.academy.dao.OwnerInsert
import com.hibob.academy.service.OwnerService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller

@Controller
@Path("/api/matan/sabag/owner/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class OwnerResource(private val ownerService: OwnerService) {

    @GET
    @Path("getAllOwner/{companyId}")
    fun getAllOwner(@PathParam("companyId") companyId: Long): Response {
        return Response.ok(ownerService.getAllOwner(companyId)).build()
    }

    @POST
    @Path("createNewOwner")
    fun createNewOwner(newOwner: OwnerInsert): Response {
        return Response.ok(ownerService.createNewOwner(newOwner.name,newOwner.employeeId,newOwner.companyId)).build()
    }

    @GET
    @Path("getOwnerByPetId/{petId}/{companyId}")
    fun getOwnerByPetId(@PathParam("petId") petId: Long,@PathParam("companyId") companyId: Long): Response {
        return Response.ok(ownerService.getOwnerByPetId(petId,companyId)).build()
    }
}