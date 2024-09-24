package ru.course.aston.model;

public class HeroToFraction {
    private Long heroToFractionId;
    private Long heroId;
    private Long fractionId;

    public HeroToFraction(Long heroToFractionId, Long heroId, Long fractionId) {
        this.heroToFractionId = heroToFractionId;
        this.heroId = heroId;
        this.fractionId = fractionId;
    }

    public Long getHeroToFractionId() {
        return heroToFractionId;
    }

    public void setHeroToFractionId(Long heroToFractionId) {
        this.heroToFractionId = heroToFractionId;
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
