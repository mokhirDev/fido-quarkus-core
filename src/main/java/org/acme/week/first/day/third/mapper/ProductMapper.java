package org.acme.week.first.day.third.mapper;

import org.acme.week.first.day.third.entity.Product;
import org.acme.week.first.day.third.model.ProductRequestDTO;
import org.acme.week.first.day.third.model.ProductResponseDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "cdi")
public interface ProductMapper extends BaseMapper<ProductRequestDTO, ProductResponseDTO, Product> {
}
