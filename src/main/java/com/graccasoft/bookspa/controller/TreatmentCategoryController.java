package com.graccasoft.bookspa.controller;

import com.graccasoft.bookspa.model.dto.TreatmentCategoryDto;
import com.graccasoft.bookspa.model.entity.TreatmentCategory;
import com.graccasoft.bookspa.service.TreatmentCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class TreatmentCategoryController implements BaseCRUDController<TreatmentCategory, TreatmentCategoryDto, TreatmentCategoryService> {

    private final TreatmentCategoryService treatmentCategoryService;

    @Override
    public TreatmentCategoryService getService() {
        return treatmentCategoryService;
    }
}
