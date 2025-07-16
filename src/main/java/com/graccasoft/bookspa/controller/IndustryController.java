package com.graccasoft.bookspa.controller;

import com.graccasoft.bookspa.model.dto.IndustryDto;
import com.graccasoft.bookspa.model.entity.Industry;
import com.graccasoft.bookspa.service.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/industries")
@RequiredArgsConstructor
public class IndustryController implements BaseCRUDController<Industry, IndustryDto, IndustryService> {

    private final IndustryService industryService;

    @Override
    public IndustryService getService() {
        return industryService;
    }
}
