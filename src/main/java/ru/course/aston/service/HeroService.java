package ru.course.aston.service;

import ru.course.aston.servlet.dto.HeroDTO;

import java.util.List;

public interface HeroService extends Service<HeroDTO, Long>{
    @Override
    HeroDTO findById(Long id);

    @Override
    boolean deleteById(Long id);

    @Override
    HeroDTO save(HeroDTO heroDTO);

    @Override
    List<HeroDTO> findAll();

    @Override
    void update(HeroDTO heroDTO);
}
