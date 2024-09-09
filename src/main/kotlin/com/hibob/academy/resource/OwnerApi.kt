package com.hibob.academy.resource

import com.hibob.restAPI.Owner
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller
import java.util.concurrent.CopyOnWriteArrayList

@Controller
@Path("/api/adi/owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class OwnerResource {

    private val allOwners: MutableList<Owner> = CopyOnWriteArrayList()

    @GET
    @Path("/{ownerId}")
    fun getOwnerById(@PathParam("ownerId") ownerId: Long): Response {
        val owner = allOwners.find { it.id == ownerId.toInt() }
        return if (owner != null)
            Response.ok(owner).build()
        else
            Response.status(Response.Status.NOT_FOUND)
                .entity("Owner not found")
                .build()
    }

    @GET
    fun getAllOwners(): Response {
        return Response.ok(allOwners).build()
    }

    @POST
    fun addOwner(owner: Owner): Response {
        val newOwnerId = (allOwners.maxOfOrNull { it.id } ?: 0) + 1

        val badRequest = Response.status(Response.Status.BAD_REQUEST).build()
        val firstName = owner.name?.split(" ")?.first() ?: owner.firstName ?: return badRequest
        val lastName = owner.name?.split(" ")?.last() ?: owner.lastName ?: return badRequest

        val newOwner = owner.copy(id = newOwnerId, name = firstName + " " + lastName, firstName = firstName, lastName = lastName)
        allOwners.add(newOwner)

        return Response.status(Response.Status.CREATED)
            .entity(allOwners)
            .build()
    }

    @PUT
    @Path("/{ownerId}")
    fun updateOwner(@PathParam("ownerId") ownerId: Int, updatedOwner: Owner): Response {
        val index = allOwners.indexOfFirst { it.id == ownerId }
        return if (index != -1) {
            val badRequest = Response.status(Response.Status.BAD_REQUEST).build()
            val firstName = updatedOwner.name?.split(" ")?.first() ?: updatedOwner.firstName ?: return badRequest
            val lastName = updatedOwner.name?.split(" ")?.last() ?: updatedOwner.lastName ?: return badRequest

            val ownerToUpdate = updatedOwner.copy(id = ownerId, name = firstName + " " + lastName, lastName = lastName)
            allOwners[index] = ownerToUpdate
            Response.status(Response.Status.ACCEPTED)
                .entity(ownerToUpdate)
                .build()
        }
        else {
            Response.status(Response.Status.NOT_FOUND)
                .entity("Owner not found")
                .build()
        }
    }

    @DELETE
    @Path("/{ownerId}")
    fun deleteOwner(@PathParam("ownerId") ownerId: Int): Response {
        val removedOwner = allOwners.removeIf { it.id == ownerId }
        return if (removedOwner) {
            Response.status(Response.Status.NO_CONTENT).build()
        } else {
            Response.status(Response.Status.NOT_FOUND)
                .entity("Owner not found")
                .build()
        }
    }

}