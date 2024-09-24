package ru.course.aston.servlet.dto;

public class MaxHPDTO {
    private Long maxHPId;
    private Long heroId;
    private Long maxHP;

    public MaxHPDTO(Long maxHPId, Long heroId, Long maxHP) {
        this.maxHPId = maxHPId;
        this.heroId = heroId;
        this.maxHP = maxHP;
    }

    public Long getMaxHPId() {
        return maxHPId;
    }

    public void setMaxHPId(Long maxHPId) {
        this.maxHPId = maxHPId;
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
