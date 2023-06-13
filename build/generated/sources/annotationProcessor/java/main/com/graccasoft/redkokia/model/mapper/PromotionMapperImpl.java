package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.PromotionDto;
import com.graccasoft.redkokia.model.entity.Promotion;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-12T19:04:51+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class PromotionMapperImpl implements PromotionMapper {

    @Override
    public Promotion toEntity(PromotionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Promotion promotion = new Promotion();

        return promotion;
    }

    @Override
    public PromotionDto toDto(Promotion entity) {
        if ( entity == null ) {
            return null;
        }

        PromotionDto promotionDto = new PromotionDto();

        return promotionDto;
    }

    @Override
    public void updateEntity(PromotionDto dto, Promotion entity) {
        if ( dto == null ) {
            return;
        }
    }

    @Override
    public List<PromotionDto> toDtoList(List<Promotion> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PromotionDto> list = new ArrayList<PromotionDto>( entities.size() );
        for ( Promotion promotion : entities ) {
            list.add( toDto( promotion ) );
        }

        return list;
    }
}
