package org.acme.week.first.day.third.mapper;

import java.util.List;

public interface BaseMapper<Req, Res, E> {
    E toEntity(Req dto);
    Res toResponseDTO(E entity);
    List<E> toEntityList(List<Req> dtoList);
    List<Res> toResponseDTOList(List<E> entityList);
}