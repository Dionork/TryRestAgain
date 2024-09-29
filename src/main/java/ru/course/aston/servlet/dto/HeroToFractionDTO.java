package ru.course.aston.servlet.dto;

import ru.course.aston.model.Fraction;
import ru.course.aston.model.Hero;

public class HeroToFractionDTO {
    private Long heroToFractionId;
    private Hero hero;
    private Fraction fraction;

    public HeroToFractionDTO(Long heroToFractionId, Hero hero, Fraction fraction)  {
        this.heroToFractionId = heroToFractionId;
        this.hero = hero;
        this.fraction = fraction;
    }

    public Long getHeroToFractionId() {
        return heroToFractionId;
    }

    public void setHeroToFractionId(Long heroToFractionId) {
        this.heroToFractionId = heroToFractionId;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Fraction getFraction() {
        return fraction;
    }

    public void setFraction(Fraction fraction) {
        this.fraction = fraction;
    }
}
