package org.acme.week.first.day.second;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.week.first.day.second.service.ProductCatalogService;

@Path("/products")
public class ProductCatalogResource {

    @Inject
    ProductCatalogService productCatalogService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProducts() {
        return productCatalogService.fetchProducts();
    }
}
