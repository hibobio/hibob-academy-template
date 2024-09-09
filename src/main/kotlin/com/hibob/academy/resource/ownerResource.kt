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

    //    temporary database simulation
    private val allOwners: MutableList<Owner> = mutableListOf()

    //
    @GET
    @Path("/{ownerId}")
    fun getOwner(@PathParam("ownerId") ownerId: Int): Response {
        val owner = allOwners.find { owner: Owner -> owner.id == ownerId }
        owner?.let {
            return Response.ok(owner).build()
        } ?: return Response.status(Response.Status.NOT_FOUND).entity("owner not found").build()
    }


    @POST
    fun postOwner(owner: Owner): Response {
        allOwners.add(owner.copy(id = owner.id, name = owner.name, companyId = owner.companyId, employeeId = owner.employeeId))
        return Response.status(Response.Status.CREATED).entity(Response.Status.CREATED).build()
    }

    @PUT
    @Path("/{ownerId}")
    fun putPet(@PathParam("ownerId") ownerId: Int, owner: Owner): Response {
        val index = allOwners.indexOfFirst { owner -> owner.id == ownerId }
        if (index >= 0) {
            val ownerToUpdate = allOwners.removeAt(index).copy(name = owner.name, companyId = owner.companyId, employeeId = owner.employeeId)
            allOwners.add(ownerToUpdate)
            return Response.ok(ownerToUpdate).build()
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("owner not found").build()
        }
    }

    @DELETE
    @Path("/{ownerId}")
    fun deleteOwner(@PathParam("ownerId") ownerId: Int, owner: Owner): Response {
        val index = allOwners.indexOfFirst { owner -> owner.id == ownerId }
        if (index >= 0) {
            allOwners.removeAt(index)
            return Response.ok(owner).build()
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("owner not found").build()
        }
    }
}