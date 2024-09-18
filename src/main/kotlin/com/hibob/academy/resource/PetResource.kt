package com.hibob.academy.resource

//import com.hibob.academy.dao.Pet
import com.hibob.academy.dao.PetInsert
import com.hibob.academy.dao.PetType
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
import org.springframework.stereotype.Controller

@Controller
@Path("/api/matan/sabag/pets/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PetResource (private val petService: PetService) {

    @GET
    @Path("getAllPetsByType/{petType}/{companyId}")
    fun getPetsByType(@PathParam("petType") petType: String, @PathParam("companyId") companyId: Long): Response {
        return Response.ok(petService.getPetsByType(companyId,petType)).build()
    }

    @POST
    @Path("insertPet")
    fun insertPet(newPet: PetInsert): Response {
        return Response.ok(petService.insertPet(newPet.name,newPet.type,newPet.companyId,newPet.ownerId)).build()
    }

    @PUT
    @Path("adoptPet/{petId}/{newOwnerId}/{companyId}")
    fun adoptPet(
        @PathParam("petId") petId: Long,
        @PathParam("newOwnerId") newOwnerId: Long,
        @PathParam("companyId") companyId: Long
    ): Response {
        return Response.ok(petService.adoptPet(petId, newOwnerId, companyId)).build()
    }

}