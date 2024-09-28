package ru.course.aston.service.impl;

import ru.course.aston.model.MaxHP;

import ru.course.aston.repository.MaxHPRepository;
import ru.course.aston.repository.impl.MaxHPRepositoryImpl;
import ru.course.aston.service.MaxHPService;
import ru.course.aston.servlet.dto.MaxHPDTO;

import ru.course.aston.servlet.mapper.MaxHPMapper;


import java.util.ArrayList;
import java.util.List;

public class MaxHPServiceImpl implements MaxHPService {
    private MaxHPRepository maxHPRepository = new MaxHPRepositoryImpl();

    @Override
    public MaxHPDTO findById(Long id) {
        return MaxHPMapper.INSTANCE.toDTO(maxHPRepository.findById(id));
    }

    @Override
    public boolean deleteById(Long id) {
        return maxHPRepository.deleteById(id);
    }

    @Override
    public MaxHPDTO save(MaxHPDTO maxHPDTO) {
        MaxHP maxHP = MaxHPMapper.INSTANCE.toModel(maxHPDTO);
        return MaxHPMapper.INSTANCE.toDTO(maxHPRepository.save(maxHP));

    }

    @Override
    public List<MaxHPDTO> findAll() {
        List<MaxHP> maxHPList = maxHPRepository.findAll();
        List<MaxHPDTO> maxHPDTOList = new ArrayList<>();
        for (MaxHP maxHP : maxHPList)  {
            MaxHPDTO maxHPDTO = MaxHPMapper.INSTANCE.toDTO(maxHP);
            maxHPDTOList.add(maxHPDTO);
        }
        return maxHPDTOList;
    }

    @Override
    public void update(MaxHPDTO maxHPDTO) {
        MaxHP maxHP = MaxHPMapper.INSTANCE.toModel(maxHPDTO);
        maxHPRepository.update(maxHP);
    }
}
