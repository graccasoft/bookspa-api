package com.graccasoft.redkokia.service;

import com.graccasoft.redkokia.model.dto.TreatmentDto;
import com.graccasoft.redkokia.model.entity.Treatment;
import com.graccasoft.redkokia.model.mapper.TreatmentMapper;
import com.graccasoft.redkokia.repository.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;

    public TreatmentDto saveTreatment(TreatmentDto treatmentDto){
        Treatment savedTreatment = treatmentRepository.save( treatmentMapper.toEntity(treatmentDto) );
        return treatmentMapper.toDto(savedTreatment);

    }

    public List<TreatmentDto> getTreatments(Long tenantId){
        return treatmentMapper.toDtoList( treatmentRepository.findAllByTenant_Id(tenantId) );
    }
}
