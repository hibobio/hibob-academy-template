package com.hibob.academy.resource


import com.hibob.academy.dao.PetNoId
import com.hibob.academy.service.PetService
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

@Component
@Path("/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PetResource(
    private val petService: PetService
) {
    @GET
    @Path("/by-type/{type}/{companyId}")
    fun petsByType(@PathParam("companyId") companyId: Long, @PathParam("type") type: String): Response {
        return Response.ok(petService.petsByType(type, companyId)).build()
    }

    @GET
    @Path("/by-owner/{ownerId}/{companyId}")
    fun getPetsByOwnerId(@PathParam("companyId") ownerId: UUID, @PathParam("ownerId") companyId: Long): Response {
        return Response.ok(petService.getPetsByOwnerId(ownerId, companyId)).build()
    }

    @POST
    fun createPet(@RequestBody pet: PetNoId): Response {
        return Response.ok(petService.createPet(pet)).build()
    }

    @PUT
    @Path("/{petId}/owner/{ownerId}/{companyId}")
    fun assignOwnerIdToPet(@PathParam("petId") petId: UUID, @PathParam("ownerId") ownerId: UUID): Response {
        return Response.ok(petService.assignOwnerIdToPet(petId, ownerId)).build()
    }

}