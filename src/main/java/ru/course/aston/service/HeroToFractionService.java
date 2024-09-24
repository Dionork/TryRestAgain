package ru.course.aston.service;

import ru.course.aston.servlet.dto.HeroToFractionDTO;

import java.util.List;

public interface HeroToFractionService extends Service<HeroToFractionDTO, Long>{
    @Override
    HeroToFractionDTO findById(Long id);

    @Override
    boolean deleteById(Long id);

    @Override
    HeroToFractionDTO save(HeroToFractionDTO heroToFractionDTO);

    @Override
    List<HeroToFractionDTO> findAll();

    @Override
    void update(HeroToFractionDTO heroToFractionDTO);
}
