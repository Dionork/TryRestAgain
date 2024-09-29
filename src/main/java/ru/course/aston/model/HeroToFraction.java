package ru.course.aston.model;

import ru.course.aston.repository.FractionRepository;
import ru.course.aston.repository.HeroRepository;
import ru.course.aston.repository.impl.FractionRepositoryImpl;
import ru.course.aston.repository.impl.HeroRepositoryImpl;

import java.util.List;

/** Класс Many to Many
 * Hero to Fraction
 * */
public class HeroToFraction {
    private HeroRepository heroRepository = new HeroRepositoryImpl();
    private FractionRepository fractionRepository = new FractionRepositoryImpl();
    private List<Fraction> fractions = fractionRepository.findAll();
    private List<Hero> heroes = heroRepository.findAll();
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

    public List<Fraction> getFractionsList() {
        return fractions;
    }

    public List<Hero> getHeroesList() {
        return heroes;
    }
}
