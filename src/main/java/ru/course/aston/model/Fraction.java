package ru.course.aston.model;

public class Fraction {
    private Long fractionId;
    private String fractionName;

    public Fraction(Long fractionId, String fractionName) {
        this.fractionId = fractionId;
        this.fractionName = fractionName;
    }

    public Long getFractionId() {
        return fractionId;
    }

    public void setFractionId(Long fractionId) {
        this.fractionId = fractionId;
    }

    public String getFractionName() {
        return fractionName;
    }

    public void setFractionName(String fractionName) {
        this.fractionName = fractionName;
    }

    @Override
    public String toString() {
        return "Fraction{" +
               "fractionId=" + fractionId +
               ", fractionName='" + fractionName + '\'' +
               '}';
    }
}
