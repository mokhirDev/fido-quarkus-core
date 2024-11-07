package org.acme.week.first.day.third.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.week.first.day.third.entity.Product;
import org.acme.week.first.day.third.exceptions.AlreadyProductExistException;
import org.acme.week.first.day.third.exceptions.ProductNotFoundException;
import org.acme.week.first.day.third.mapper.ProductMapper;
import org.acme.week.first.day.third.model.ProductRequestDTO;
import org.acme.week.first.day.third.model.ProductResponseDTO;
import org.acme.week.first.day.third.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository productRepository;
    @Inject
    ProductMapper productMapper;
    private static AtomicLong id = new AtomicLong(0);

    @Transactional
    public ProductResponseDTO findById(Long id) {
        Product byId = productRepository.findById(id);
        if (byId == null) {
            throw new ProductNotFoundException("Product with id: %d not found".formatted(id));
        }
        return productMapper.toResponseDTO(byId);
    }


    @Transactional
    public ArrayList<ProductResponseDTO> findAll() {
        ArrayList<Product> all = productRepository.findAll();
        ArrayList<ProductResponseDTO> dtos = new ArrayList<>();
        for (Product product : all) {
            dtos.add(productMapper.toResponseDTO(product));
        }
        return dtos;
    }

    @Transactional
    public ProductResponseDTO save(ProductRequestDTO product) {
        Product entity = productMapper.toEntity(product);
        if (existProduct(entity)) {
            throw new AlreadyProductExistException("Product with name: %s, already exist!".formatted(product .getName()));
        }
        entity.setId(id.incrementAndGet());
        Product saved = productRepository.save(entity);
        return productMapper.toResponseDTO(saved);
    }

    @Transactional
    public ProductResponseDTO update(ProductRequestDTO product) {
        Product entity = productMapper.toEntity(product);

        Product updated = productRepository.update(entity);
        return productMapper.toResponseDTO(updated);
    }

    @Transactional
    public boolean delete(Long id) {
        return productRepository.deleteById(id);
    }


    public boolean existProduct(Product product) {
        ArrayList<Product> all = productRepository.findAll();
        Optional<Product> exist = all.stream().filter(p -> {
            if (p.getName() != null && product.getName() != null) {
                return p.getName().trim().equals(product.getName().trim());
            }
            return false;
        }).findFirst();
        return exist.isPresent();
    }

    public List<ProductResponseDTO> makeOrder(List<ProductRequestDTO> products) {
        ArrayList<Product> all = productRepository.findAll();

        all.forEach(product -> {
            for (ProductRequestDTO p : products) {
                Product entity = productMapper.toEntity(p);
                if (product.getId().equals(entity.getId())) {
                    int inStock = product.getQuantity();
                    Integer orderedQuantity = p.getQuantity();
                    int result = inStock - (orderedQuantity <= inStock ? orderedQuantity : 0);
                    product.setQuantity(result);
                    productRepository.save(product);
                } else {
                    products.remove(p);
                }
            }
        });
        return productMapper.toResponseDTOList(productMapper.toEntityList(products));
    }
}
