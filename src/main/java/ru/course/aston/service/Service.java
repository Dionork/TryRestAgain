package ru.course.aston.service;

import java.util.List;

public interface Service <DTO,L>{
    DTO findById(L id);
    boolean deleteById(L id);
    DTO save(DTO dto);
    List<DTO> findAll();
    void update(DTO dto);
}
