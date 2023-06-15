package com.graccasoft.redkokia.service;

import com.graccasoft.redkokia.model.dto.TreatmentCategoryDto;
import com.graccasoft.redkokia.model.mapper.TreatmentCategoryMapper;
import com.graccasoft.redkokia.repository.TreatmentCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentCategoryService {
    private final TreatmentCategoryRepository treatmentCategoryRepository;
    private final TreatmentCategoryMapper treatmentCategoryMapper;
    public List<TreatmentCategoryDto> getAll(){
        return treatmentCategoryMapper.toDtoList( treatmentCategoryRepository.findAll() );
    }
}
