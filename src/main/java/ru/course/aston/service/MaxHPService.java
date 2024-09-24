package ru.course.aston.service;

import ru.course.aston.servlet.dto.MaxHPDTO;

import java.util.List;

public interface MaxHPService extends Service<MaxHPDTO, Long>{
    @Override
    MaxHPDTO findById(Long id);

    @Override
    boolean deleteById(Long id);

    @Override
    MaxHPDTO save(MaxHPDTO maxHPDTO);

    @Override
    List<MaxHPDTO> findAll();

    @Override
    void update(MaxHPDTO maxHPDTO);
}
