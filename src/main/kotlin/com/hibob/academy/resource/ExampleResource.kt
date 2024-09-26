//package com.hibob.academy.resource
//
//import com.hibob.academy.dao.Example
//import com.hibob.academy.service.ExampleService
//import jakarta.ws.rs.GET
//import jakarta.ws.rs.Path
//import jakarta.ws.rs.PathParam
//import jakarta.ws.rs.Produces
//import jakarta.ws.rs.core.MediaType
//import jakarta.ws.rs.core.Response
//import org.springframework.stereotype.Component
//
//
//@Component
//@Path("/example")
//class ExampleResource(private val service: ExampleService) {
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{companyId}")
//    fun example(@PathParam("companyId") companyId: Long): Response {
//        val example = service.get(companyId)
//        return example?. let {
//            Response.ok(ExampleResponse(it)).build()
//        } ?: Response.status(Response.Status.NOT_FOUND).build()
//    }
//}
//
//data class ExampleResponse(val data: Example)
