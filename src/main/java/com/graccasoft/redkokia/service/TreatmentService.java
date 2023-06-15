package com.graccasoft.redkokia.service;

import com.graccasoft.redkokia.model.dto.CategorisedTreatmentsDto;
import com.graccasoft.redkokia.model.dto.TreatmentDto;
import com.graccasoft.redkokia.model.entity.Treatment;
import com.graccasoft.redkokia.model.entity.TreatmentCategory;
import com.graccasoft.redkokia.model.mapper.TreatmentCategoryMapper;
import com.graccasoft.redkokia.model.mapper.TreatmentMapper;
import com.graccasoft.redkokia.repository.TreatmentCategoryRepository;
import com.graccasoft.redkokia.repository.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;
    private final TreatmentCategoryRepository treatmentCategoryRepository;
    private final TreatmentCategoryMapper treatmentCategoryMapper;
    public TreatmentDto saveTreatment(TreatmentDto treatmentDto){
        Treatment savedTreatment = treatmentRepository.save( treatmentMapper.toEntity(treatmentDto) );
        return treatmentMapper.toDto(savedTreatment);

    }

    public List<TreatmentDto> getTreatments(Long tenantId){
        return treatmentMapper.toDtoList( treatmentRepository.findAllByTenant_Id(tenantId) );
    }

    public List<CategorisedTreatmentsDto> getCategorisedTreatments(Long tenantId){
        List<CategorisedTreatmentsDto> categorisedTreatmentsDtos = new ArrayList<>();
        List<TreatmentCategory> categories = treatmentCategoryRepository.findAll();

        categories.forEach(category->{
            List<TreatmentDto> treatments = treatmentMapper.toDtoList( treatmentRepository.findAllByTenant_IdAndCategory_id(tenantId, category.getId()) );
            categorisedTreatmentsDtos.add( new CategorisedTreatmentsDto( treatmentCategoryMapper.toDto(category), treatments  ) );
        });

        return categorisedTreatmentsDtos;
    }
}
