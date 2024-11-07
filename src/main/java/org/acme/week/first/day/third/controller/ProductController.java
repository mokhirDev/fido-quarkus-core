package org.acme.week.first.day.third.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.week.first.day.third.model.ProductRequestDTO;
import org.acme.week.first.day.third.model.ProductResponseDTO;
import org.acme.week.first.day.third.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Path("/api/products")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {
    @Inject
    ProductService productService;

    @GET
    @Path("/findBy/{id}")
    public Response findById(@PathParam("id") Long id) {
        ProductResponseDTO byId = productService.findById(id);
        return Response.ok(byId).build();
    }

    @GET
    @Path("/findAll")
    public Response findAll() {
        ArrayList<ProductResponseDTO> all = productService.findAll();
        return Response.ok(all).build();
    }

    @POST
    @Path("/save")
    public Response save(ProductRequestDTO productRequestDTO) {
        ProductResponseDTO savedProduct = productService.save(productRequestDTO);
        if (savedProduct == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Product not saved").build();
        }
        return Response.ok(savedProduct).build();
    }


    @PUT
    @Path("/update")
    public Response update(ProductRequestDTO product) {
        ProductResponseDTO update = productService.update(product);
        return Response.ok(update).build();
    }

    @DELETE
    @Path("/delete")
    public Response delete(@QueryParam("byId") Long productId) {
        boolean deleted = productService.delete(productId);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Product with ID " + productId + " not found.")
                    .build();
        }
    }

    @POST
    @Path("/order")
    public Response makeOrder(List<ProductRequestDTO> products) {
        List<ProductResponseDTO> orderedProducts = productService.makeOrder(products);
        return Response.ok(orderedProducts).build();
    }


}
