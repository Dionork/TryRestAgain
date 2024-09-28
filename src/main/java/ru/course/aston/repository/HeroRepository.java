package ru.course.aston.repository;

import ru.course.aston.model.Hero;

import java.util.List;

public interface HeroRepository extends Repository<Hero,Long>{
    @Override
    Hero findById(Long id);

    @Override
    boolean deleteById(Long id);

    @Override
    Hero save(Hero entity);

    @Override
    List<Hero> findAll();

    @Override
    void update(Hero models);
}
