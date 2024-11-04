package org.acme.week.first.day.third.aggregate.entity.mapper;

import org.acme.week.first.day.third.aggregate.entity.Product;
import org.acme.week.first.day.third.aggregate.entity.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ProductMapper {
    ProductDTO toDTO(Product product);

    Product toEntity(ProductDTO productDTO);
}
