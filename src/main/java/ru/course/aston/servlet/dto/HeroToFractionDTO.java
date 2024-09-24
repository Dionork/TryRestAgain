package ru.course.aston.servlet.dto;

public class HeroToFractionDTO {
    private Long heroId;
    private Long fractionId;

    public HeroToFractionDTO(Long heroId, Long fractionId) {
        this.heroId = heroId;
        this.fractionId = fractionId;
    }

    public Long getHeroId() {
        return heroId;
    }

    public void setHeroId(Long heroId) {
        this.heroId = heroId;
    }

    public Long getFractionId() {
        return fractionId;
    }

    public void setFractionId(Long fractionId) {
        this.fractionId = fractionId;
    }
}
