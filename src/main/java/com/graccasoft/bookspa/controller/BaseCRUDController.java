package com.graccasoft.bookspa.controller;

import com.graccasoft.bookspa.model.dto.ApiResponse;
import com.graccasoft.bookspa.model.dto.BaseDto;
import com.graccasoft.bookspa.model.entity.BaseEntity;
import com.graccasoft.bookspa.service.BaseCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseCRUDController<
        E extends BaseEntity,
        D extends BaseDto,
        S extends BaseCRUDService<E,D>> {

    S getService();

    @GetMapping
    default List<D> getAll() {
        return getService().getAll();
    }

    @GetMapping("/{id}")
    default D get(@PathVariable Long id) {
        return getService().get(id);
    }

    @PostMapping
    default D create(@RequestBody D dto) {
        return getService().save(dto);
    }

    @PutMapping("/{id}")
    default D update(@RequestBody D dto, @PathVariable Long id) {
        return getService().update(dto, id);
    }

    @DeleteMapping("/{id}")
    default ApiResponse delete(@PathVariable Long id) {
        getService().delete(id);
        return new ApiResponse(true, "Record deleted successfully");
    }
}

