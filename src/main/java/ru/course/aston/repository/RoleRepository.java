package ru.course.aston.repository;

import ru.course.aston.model.Role;

import java.util.List;

public interface RoleRepository extends Repository<Role, Long>{
    @Override
    Role findById(Long id);

    @Override
    boolean deleteById(Long id);

    @Override
    Role save(Role entity);

    @Override
    List<Role> findAll();
}
