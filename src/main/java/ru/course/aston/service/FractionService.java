package ru.course.aston.service;

import ru.course.aston.servlet.dto.FractionDTO;

import java.util.List;

public interface FractionService extends Service<FractionDTO,Long>{
    @Override
    FractionDTO findById(Long id);

    @Override
    boolean deleteById(Long id);

    @Override
    FractionDTO save(FractionDTO fractionDTO);

    @Override
    List<FractionDTO> findAll();

    @Override
    void update(FractionDTO fractionDTO);
}
