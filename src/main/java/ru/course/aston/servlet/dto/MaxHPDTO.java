package ru.course.aston.servlet.dto;

public class MaxHPDTO {
    private Long heroId;
    private Long maxHP;

    public MaxHPDTO(Long heroId, Long maxHP) {
        this.heroId = heroId;
        this.maxHP = maxHP;
    }

    public Long getHeroId() {
        return heroId;
    }

    public void setHeroId(Long heroId) {
        this.heroId = heroId;
    }

    public Long getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(Long maxHP) {
        this.maxHP = maxHP;
    }
}
