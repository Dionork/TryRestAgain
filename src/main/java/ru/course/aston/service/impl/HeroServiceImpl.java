package ru.course.aston.service.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.model.Hero;
import ru.course.aston.repository.HeroRepository;
import ru.course.aston.repository.impl.HeroRepositoryImpl;
import ru.course.aston.service.HeroService;
import ru.course.aston.servlet.dto.HeroDTO;
import ru.course.aston.servlet.mapper.HeroMapper;

import java.util.ArrayList;
import java.util.List;

public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;
    public HeroServiceImpl() {
        heroRepository = new HeroRepositoryImpl();
    }
    public HeroServiceImpl(ConnectionManager connectionManager) {
        heroRepository = new HeroRepositoryImpl(connectionManager);
    }

    @Override
    public HeroDTO findById(Long id) {
        return HeroMapper.INSTANCE.toDTO(heroRepository.findById(id));
    }

    @Override
    public boolean deleteById(Long id) {
        return heroRepository.deleteById(id);
    }

    @Override
    public HeroDTO save(HeroDTO heroDTO) {
        Hero hero = HeroMapper.INSTANCE.toModel(heroDTO);
        return HeroMapper.INSTANCE.toDTO(heroRepository.save(hero));
    }

    @Override
    public List<HeroDTO> findAll() {
        List<Hero> heroList = heroRepository.findAll();
        List<HeroDTO> heroDTOList = new ArrayList<>();
        for (Hero hero : heroList) {
            HeroDTO heroDTO = HeroMapper.INSTANCE.toDTO(hero);
            heroDTO.setHeroId(hero.getHeroId());
            heroDTO.setHeroName(hero.getHeroName());
            heroDTO.setHeroLastName(hero.getHeroLastName());
            heroDTO.setRoleNameId(hero.getRoleNameId());
            heroDTOList.add(heroDTO);
        }
        return heroDTOList;
    }

    @Override
    public void update(HeroDTO heroDTO) {
        Hero hero = HeroMapper.INSTANCE.toModel(heroDTO);
        heroRepository.update(hero);
    }
}
