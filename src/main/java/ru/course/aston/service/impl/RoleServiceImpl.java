package ru.course.aston.service.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.model.Role;
import ru.course.aston.repository.RoleRepository;
import ru.course.aston.repository.impl.RoleRepositoryImpl;
import ru.course.aston.service.RoleService;
import ru.course.aston.servlet.dto.RoleDTO;
import ru.course.aston.servlet.mapper.RoleMapper;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    public RoleServiceImpl(){
        roleRepository = new RoleRepositoryImpl();
    }
    public RoleServiceImpl(ConnectionManager connectionManager) {
        roleRepository = new RoleRepositoryImpl(connectionManager);
    }

    @Override
    public RoleDTO findById(Long id) {
        return RoleMapper.INSTANCE.toDto(roleRepository.findById(id));

    }

    @Override
    public boolean deleteById(Long id) {
        return roleRepository.deleteById(id);
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role = RoleMapper.INSTANCE.toModel(roleDTO);
        return RoleMapper.INSTANCE.toDto(roleRepository.save(role));

    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for (Role role : roles) {
            RoleDTO roleDTO = RoleMapper.INSTANCE.toDto(role);
            roleDTOList.add(roleDTO);
        }
        return roleDTOList;

    }

    @Override
    public void update(RoleDTO roleDTO) {
        Role role = RoleMapper.INSTANCE.toModel(roleDTO);
        roleRepository.update(role);
    }
}
