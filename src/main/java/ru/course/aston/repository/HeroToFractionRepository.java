package ru.course.aston.repository;

import ru.course.aston.model.HeroToFraction;

import java.util.List;

public interface HeroToFractionRepository extends Repository<HeroToFraction, Long>{
    @Override
    HeroToFraction findById(Long id);

    @Override
    boolean deleteById(Long id);

    @Override
    HeroToFraction save(HeroToFraction entity);

    @Override
    List<HeroToFraction> findAll();
}
