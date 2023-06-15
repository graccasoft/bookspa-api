package com.graccasoft.redkokia.controller;

import com.graccasoft.redkokia.model.dto.TreatmentCategoryDto;
import com.graccasoft.redkokia.service.TreatmentCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class TreatmentCategoryController {

    private final TreatmentCategoryService treatmentCategoryService;

    @GetMapping
    public List<TreatmentCategoryDto> getCategories(){
        return treatmentCategoryService.getAll();
    }

}
