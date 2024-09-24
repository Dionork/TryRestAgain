package ru.course.aston.servlet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.course.aston.model.Fraction;
import ru.course.aston.servlet.dto.FractionDTO;

@Mapper
public interface FractionMapper {
    FractionMapper INSTANCE = Mappers.getMapper(FractionMapper.class);

    FractionDTO toDto(Fraction fraction);

    Fraction toModel(FractionDTO fractionDto);
}
