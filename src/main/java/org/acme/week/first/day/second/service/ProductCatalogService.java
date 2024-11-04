package org.acme.week.first.day.second.service;


import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ProductCatalogService {

    @ConfigProperty(name = "product.catalog.default-category", defaultValue = "General")
    String defaultCategory;

    @ConfigProperty(name = "product.catalog.page-size", defaultValue = "10")
    int pageSize;

    public String getDefaultCategory() {
        return defaultCategory;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String fetchProducts() {
        return "Fetching " + pageSize + " products from category: " + defaultCategory;
    }
}

