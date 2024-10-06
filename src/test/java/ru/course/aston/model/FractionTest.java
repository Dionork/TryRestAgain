package ru.course.aston.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @Test
    void getFractionId() {
        Fraction fraction = new Fraction(1L, "Fraction");
        assertEquals(1, fraction.getFractionId());
    }

    @Test
    void setFractionId() {
        Fraction fraction = new Fraction(1L, "Fraction");
        fraction.setFractionId(2L);
        assertEquals(2, fraction.getFractionId());
    }

    @Test
    void getFractionName() {
        Fraction fraction = new Fraction(1L, "Fraction");
        assertEquals("Fraction", fraction.getFractionName());
    }

    @Test
    void setFractionName() {
        Fraction fraction = new Fraction(1L, "Fraction");
        fraction.setFractionName("Fraction2");
        assertEquals("Fraction2", fraction.getFractionName());
    }

    @Test
    void testToString() {
        Fraction fraction = new Fraction(1L, "Fraction");
        assertEquals("Fraction{fractionId=1, fractionName='Fraction'}", fraction.toString());
    }
}