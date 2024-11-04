package org.acme.week.first.day.third.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.week.first.day.third.aggregate.entity.Product;
import org.acme.week.first.day.third.aggregate.entity.dto.ProductDTO;
import org.acme.week.first.day.third.aggregate.entity.mapper.ProductMapper;
import org.acme.week.first.day.third.exceptions.ProductNotFoundException;
import org.acme.week.first.day.third.repository.ProductRepository;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository productRepository;
    @Inject
    ProductMapper productMapper;
    private static AtomicLong id = new AtomicLong(0);

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public ArrayList<ProductDTO> findAll() {
        ArrayList<Product> all = productRepository.findAll();
        ArrayList<ProductDTO> dtos = new ArrayList<>();
        for (Product product : all) {
            dtos.add(productMapper.toDTO(product));
        }
        return dtos;
    }

    @Transactional
    public Product save(ProductDTO product) {
        Product entity = productMapper.toEntity(product);
        System.out.printf("Entity: { %s }%n", entity.toString());
        entity.setId(id.incrementAndGet());
        return productRepository.save(entity);
    }

    @Transactional
    public ProductDTO update(ProductDTO product) {
        Product entity = productMapper.toEntity(product);

        Product updated = productRepository.update(entity);
        return productMapper.toDTO(updated);
    }

    @Transactional
    public boolean delete(Long id) {
        return productRepository.deleteById(id);
    }

}
