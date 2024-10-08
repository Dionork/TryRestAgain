package ru.course.aston.repository;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.model.Fraction;

import java.util.List;

public interface FractionRepository extends Repository<Fraction, Long> {
    @Override
    Fraction findById(Long id);

    @Override
    boolean deleteById(Long id);

    @Override
    Fraction save(Fraction entity);

    @Override
    List<Fraction> findAll();

    @Override
    void update(Fraction fraction);
}
