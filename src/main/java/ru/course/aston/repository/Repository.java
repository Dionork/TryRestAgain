package ru.course.aston.repository;

import java.util.List;

public interface Repository <T, K>{
    T findById(K id);
    boolean deleteById(K id);
    T save(T entity);
    List<T> findAll();
    void update(T models);
}
