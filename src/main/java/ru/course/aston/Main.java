package ru.course.aston;


import ru.course.aston.model.Role;
import ru.course.aston.repository.FractionRepository;
import ru.course.aston.repository.RoleRepository;
import ru.course.aston.repository.impl.FractionRepositoryImpl;
import ru.course.aston.repository.impl.RoleRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        FractionRepository fractionRepository = new FractionRepositoryImpl();
        System.out.println(fractionRepository.findById(1L).toString());
        // fractionRepository.findAll().forEach(System.out::println);

    }
}
