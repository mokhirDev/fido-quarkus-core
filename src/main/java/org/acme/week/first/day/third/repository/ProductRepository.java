package org.acme.week.first.day.third.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.week.first.day.third.aggregate.entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ProductRepository {
    Map<Long, Product> products = new HashMap<>();

    public Product findById(Long id) {
        return products.get(id);
    }

    public ArrayList<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product save(Product product) {
        products.put(product.getId(), product);
        return product;
    }


    public Product update(Product product) {
        return products.put(product.getId(), product);
    }

    public boolean deleteById(Long productId) {
        Product removed = products.remove(productId);
        return removed != null;
    }
}

