package com.graccasoft.bookspa.service;


import com.graccasoft.bookspa.exception.RecordNotFoundException;
import com.graccasoft.bookspa.model.dto.BaseDto;
import com.graccasoft.bookspa.model.entity.BaseEntity;
import com.graccasoft.bookspa.model.mapper.EntityMapper;
import com.graccasoft.bookspa.repository.BaseRepository;

import java.util.List;

public interface BaseCRUDService<
        E extends BaseEntity,
        D extends BaseDto> {

    BaseRepository<E> getRepository();
    EntityMapper<E,D> getMapper();

    default List<D> getAll(){
        return  getMapper().toDtoList(  getRepository().findAll() );
    }

    default D get(Long id){
        return getMapper().toDto( getRepository().findById(id).orElseThrow(()-> new RecordNotFoundException("Record not found")) );
    }

    default D save(D dto){
        var e = getMapper().toEntity(dto);
        return getMapper().toDto(
                getRepository().save(e)
        );
    }

    default D update(D dto, Long id){
        var e = getMapper().toEntity(dto);
        return getMapper().toDto(
                getRepository().save(e)
        );
    }

    default void delete(Long id){
        getRepository().deleteById(id);
    }
}
