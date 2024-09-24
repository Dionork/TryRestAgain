package ru.course.aston.servlet.dto;

public class FractionDTO {
    private String fractionName;

    public FractionDTO(String fractionName) {
        this.fractionName = fractionName;
    }

    public String getFractionName() {
        return fractionName;
    }

    public void setFractionName(String fractionName) {
        this.fractionName = fractionName;
    }
}
