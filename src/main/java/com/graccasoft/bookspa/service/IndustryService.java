package com.graccasoft.bookspa.service;

import com.graccasoft.bookspa.model.dto.IndustryDto;
import com.graccasoft.bookspa.model.entity.Industry;
import com.graccasoft.bookspa.model.mapper.EntityMapper;
import com.graccasoft.bookspa.model.mapper.IndustryMapper;
import com.graccasoft.bookspa.repository.BaseRepository;
import com.graccasoft.bookspa.repository.IndustryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndustryService implements BaseCRUDService<Industry, IndustryDto> {
    private final IndustryRepository industryRepository;
    private final IndustryMapper industryMapper;
    @Override
    public BaseRepository<Industry> getRepository() {
        return industryRepository;
    }

    @Override
    public EntityMapper<Industry, IndustryDto> getMapper() {
        return industryMapper;
    }
}
