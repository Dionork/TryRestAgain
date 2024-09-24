package ru.course.aston.servlet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.course.aston.model.HeroToFraction;
import ru.course.aston.servlet.dto.FractionDTO;
import ru.course.aston.servlet.dto.HeroToFractionDTO;

@Mapper
public interface HeroToFractionMapper {
    HeroToFractionMapper INSTANCE = Mappers.getMapper(HeroToFractionMapper.class);

    HeroToFractionDTO toDTO(HeroToFraction heroToFraction);

    HeroToFraction toModel(HeroToFractionDTO heroToFractionDTO);
}
