package ru.course.aston.servlet.dto;

import org.junit.jupiter.api.Test;
import ru.course.aston.model.Fraction;
import ru.course.aston.servlet.mapper.FractionMapper;

import static org.junit.jupiter.api.Assertions.*;

class FractionDTOTest {

    @Test
    void getFractionId() {
        Fraction fraction = new Fraction(1L,"fraction");
        FractionDTO fractionDTO = FractionMapper.INSTANCE.toDto(fraction);
        assertEquals(1, fractionDTO.getFractionId());
    }

    @Test
    void setFractionId() {
        Fraction fraction = new Fraction(1L,"fraction");
        FractionDTO fractionDTO = FractionMapper.INSTANCE.toDto(fraction);
        fractionDTO.setFractionId(2L);
        assertEquals(2, fractionDTO.getFractionId());
    }

    @Test
    void getFractionName() {
        Fraction fraction = new Fraction(1L,"fraction");
        FractionDTO fractionDTO = FractionMapper.INSTANCE.toDto(fraction);
        assertEquals("fraction", fractionDTO.getFractionName());
    }

    @Test
    void setFractionName() {
        Fraction fraction = new Fraction(1L,"fraction");
        FractionDTO fractionDTO = FractionMapper.INSTANCE.toDto(fraction);
        fractionDTO.setFractionName("new fraction");
        assertEquals("new fraction", fractionDTO.getFractionName());
    }
}