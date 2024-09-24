package ru.course.aston.servlet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.course.aston.model.MaxHP;
import ru.course.aston.servlet.dto.MaxHPDTO;

@Mapper
public interface MaxHPMapper {
    MaxHPMapper INSTANCE = Mappers.getMapper(MaxHPMapper.class);

    MaxHP toModel(MaxHPDTO maxHPDTO);

    MaxHPDTO toDTO(MaxHP maxHP);
}
