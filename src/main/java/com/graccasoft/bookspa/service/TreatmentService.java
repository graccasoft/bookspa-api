package com.graccasoft.bookspa.service;

import com.graccasoft.bookspa.model.dto.CategorisedTreatmentsDto;
import com.graccasoft.bookspa.model.dto.TreatmentDto;
import com.graccasoft.bookspa.model.entity.Treatment;
import com.graccasoft.bookspa.model.entity.TreatmentCategory;
import com.graccasoft.bookspa.model.mapper.TreatmentCategoryMapper;
import com.graccasoft.bookspa.model.mapper.TreatmentMapper;
import com.graccasoft.bookspa.repository.TreatmentCategoryRepository;
import com.graccasoft.bookspa.repository.TreatmentRepository;
import jakarta.transaction.Transactional;
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
        return treatmentMapper.toDtoList( treatmentRepository.findAllByTenant_IdAndIsPromotion(tenantId, Boolean.FALSE) );
    }

    public List<TreatmentDto> getPromotions(Long tenantId){
        return treatmentMapper.toDtoList( treatmentRepository.findAllByTenant_IdAndIsPromotion(tenantId, Boolean.TRUE) );
    }

    public List<TreatmentDto> getPromotionsAndTreatments(Long tenantId){
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

    @Transactional
    public void deleteTreatment(Long id){
        this.treatmentRepository.deleteById(id);
    }
}
