package ru.course.aston.service.impl;

import ru.course.aston.model.HeroToFraction;
import ru.course.aston.repository.HeroToFractionRepository;
import ru.course.aston.repository.impl.HeroToFractionRepositoryImpl;
import ru.course.aston.service.HeroToFractionService;
import ru.course.aston.servlet.dto.HeroToFractionDTO;
import ru.course.aston.servlet.mapper.HeroToFractionMapper;

import java.util.ArrayList;
import java.util.List;

public class HeroToFractionServiceImpl implements HeroToFractionService {
    private HeroToFractionRepository heroToFractionRepository = new HeroToFractionRepositoryImpl();

    @Override
    public HeroToFractionDTO findById(Long id) {
        return HeroToFractionMapper.INSTANCE.toDTO(heroToFractionRepository.findById(id));
    }

    @Override
    public boolean deleteById(Long id) {
        return heroToFractionRepository.deleteById(id);
    }

    @Override
    public HeroToFractionDTO save(HeroToFractionDTO heroToFractionDTO) {
        HeroToFraction heroToFraction = HeroToFractionMapper.INSTANCE.toModel(heroToFractionDTO);
        return HeroToFractionMapper.INSTANCE.toDTO(heroToFractionRepository.save(heroToFraction));
    }

    @Override
    public List<HeroToFractionDTO> findAll() {
        List<HeroToFraction> heroToFractionsList = heroToFractionRepository.findAll();
        List<HeroToFractionDTO> heroToFractionDTOList = new ArrayList<>();
        for (HeroToFraction heroToFraction : heroToFractionsList) {
            HeroToFractionDTO heroToFractionDTO = HeroToFractionMapper.INSTANCE.toDTO(heroToFraction);
            heroToFractionDTOList.add(heroToFractionDTO);
        }
        return heroToFractionDTOList;
    }

    @Override
    public void update(HeroToFractionDTO heroToFractionDTO) {
        HeroToFraction heroToFraction = HeroToFractionMapper.INSTANCE.toModel(heroToFractionDTO);
        heroToFractionRepository.update(heroToFraction);
    }
}
