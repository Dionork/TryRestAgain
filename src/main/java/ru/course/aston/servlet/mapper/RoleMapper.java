package ru.course.aston.servlet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.course.aston.model.Role;
import ru.course.aston.servlet.dto.RoleDTO;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    RoleDTO toDto(Role role);
    Role toEntity(RoleDTO roleDTO);
}
