package com.graccasoft.bookspa.service;

import com.graccasoft.bookspa.model.dto.TreatmentCategoryDto;
import com.graccasoft.bookspa.model.entity.TreatmentCategory;
import com.graccasoft.bookspa.model.mapper.EntityMapper;
import com.graccasoft.bookspa.model.mapper.TreatmentCategoryMapper;
import com.graccasoft.bookspa.repository.BaseRepository;
import com.graccasoft.bookspa.repository.TreatmentCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentCategoryService implements BaseCRUDService<TreatmentCategory, TreatmentCategoryDto> {
    private final TreatmentCategoryRepository treatmentCategoryRepository;
    private final TreatmentCategoryMapper treatmentCategoryMapper;
    @Override
    public BaseRepository<TreatmentCategory> getRepository() {
        return treatmentCategoryRepository;
    }

    @Override
    public EntityMapper<TreatmentCategory, TreatmentCategoryDto> getMapper() {
        return treatmentCategoryMapper;
    }
}
