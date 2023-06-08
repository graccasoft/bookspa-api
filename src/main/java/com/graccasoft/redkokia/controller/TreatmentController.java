package com.graccasoft.redkokia.controller;

import com.graccasoft.redkokia.model.dto.TreatmentDto;
import com.graccasoft.redkokia.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class TreatmentController {
    private final TreatmentService treatmentService;

    @PostMapping
    public TreatmentDto saveTreatment(@RequestBody TreatmentDto treatmentDto){
        return treatmentService.saveTreatment(treatmentDto);
    }

    @GetMapping
    public List<TreatmentDto> getTreatments(@RequestParam Long tenantId){
        return treatmentService.getTreatments(tenantId);
    }
}
