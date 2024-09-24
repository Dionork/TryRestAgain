package ru.course.aston.repository;

import ru.course.aston.model.MaxHP;

import java.util.List;

public interface MaxHPRepository extends Repository<MaxHP,Long>{
    @Override
    MaxHP findById(Long id);

    @Override
    boolean deleteById(Long id);

    @Override
    MaxHP save(MaxHP entity);

    @Override
    List<MaxHP> findAll();
}
