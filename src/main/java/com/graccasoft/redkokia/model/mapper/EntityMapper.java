package com.graccasoft.redkokia.model.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface EntityMapper<E, D> {
    E toEntity(D dto);

    D toDto(E entity);
    void updateEntity(D dto, @MappingTarget E entity);
    List<D> toDtoList(List<E> entities);
}
