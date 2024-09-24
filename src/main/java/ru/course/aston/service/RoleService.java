package ru.course.aston.service;

import ru.course.aston.servlet.dto.RoleDTO;

import java.util.List;

public interface RoleService extends Service <RoleDTO, Long>{
    @Override
    RoleDTO findById(Long id);

    @Override
    boolean deleteById(Long id);

    @Override
    RoleDTO save(RoleDTO roleDTO);

    @Override
    List<RoleDTO> findAll();

    @Override
    void update(RoleDTO roleDTO);
}
