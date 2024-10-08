package ru.course.aston.model;

import java.util.List;

/** Класс Many to Many
 * Hero to Fraction
 * */
public class HeroToFraction {
    private List<Fraction> fractions ;
    private List<Hero> heroes ;
    private Long heroToFractionId;
    private Hero hero;
    private Fraction fraction;

    public HeroToFraction(Long heroToFractionId, Hero hero, Fraction fraction)  {
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


    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public void setFractions(List<Fraction> fractions) {
        this.fractions = fractions;
    }
}
