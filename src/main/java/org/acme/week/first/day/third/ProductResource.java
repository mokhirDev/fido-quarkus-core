package org.acme.week.first.day.third;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.week.first.day.third.aggregate.entity.Product;
import org.acme.week.first.day.third.aggregate.entity.dto.ProductDTO;
import org.acme.week.first.day.third.service.ProductService;

import java.util.ArrayList;

@Path("/api/products")
public class ProductResource {
    @Inject
    ProductService productService;

    @GET
    @Path("/findBy/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Product byId = productService.findById(id);
        return Response.ok(byId).build();
    }

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<ProductDTO> all = productService.findAll();
        return Response.ok(all).build();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(ProductDTO productDTO) {
        Product savedProduct = productService.save(productDTO);
        if (savedProduct == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Product not saved").build();
        }
        return Response.ok(savedProduct).build();
    }


    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(ProductDTO product) {
        ProductDTO update = productService.update(product);
        return Response.ok(update).build();
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
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
}
