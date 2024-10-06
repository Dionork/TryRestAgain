package ru.course.aston.service.impl;

import ru.course.aston.model.Fraction;
import ru.course.aston.repository.FractionRepository;
import ru.course.aston.repository.impl.FractionRepositoryImpl;
import ru.course.aston.service.FractionService;
import ru.course.aston.servlet.dto.FractionDTO;
import ru.course.aston.servlet.mapper.FractionMapper;

import java.util.ArrayList;
import java.util.List;

public class FractionServiceImpl implements FractionService {
    private FractionRepository fractionRepository = new FractionRepositoryImpl();
    private static FractionServiceImpl instance = new FractionServiceImpl();

    @Override
    public FractionDTO findById(Long id) {

        return FractionMapper.INSTANCE.toDto(fractionRepository.findById(id));
    }

    @Override
    public boolean deleteById(Long id) {
        return fractionRepository.deleteById(id);
    }

    @Override
    public FractionDTO save(FractionDTO fractionDTO) {
        Fraction fraction = FractionMapper.INSTANCE.toModel(fractionDTO);
        return FractionMapper.INSTANCE.toDto(fractionRepository.save(fraction));
    }

    @Override
    public List<FractionDTO> findAll() {
        List<Fraction> fractionsList = fractionRepository.findAll();
        List<FractionDTO> fractionDTOList = new ArrayList<>();
        for (Fraction fraction : fractionsList) {
            FractionDTO fractionDTO = FractionMapper.INSTANCE.toDto(fraction);
            fractionDTOList.add(fractionDTO);
        }
        return fractionDTOList;
    }

    @Override
    public void update(FractionDTO fractionDTO) {
        Fraction fraction = FractionMapper.INSTANCE.toModel(fractionDTO);
        fractionRepository.update(fraction);
    }
}
