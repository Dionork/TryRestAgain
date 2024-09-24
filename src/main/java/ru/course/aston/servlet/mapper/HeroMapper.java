package ru.course.aston.servlet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.course.aston.model.Hero;
import ru.course.aston.servlet.dto.HeroDTO;

@Mapper
public interface HeroMapper {
    HeroMapper INSTANCE = Mappers.getMapper(HeroMapper.class);

    HeroDTO toDTO(Hero hero);

    Hero toModel(HeroDTO heroDTO);
}
