package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Fraction;
import ru.course.aston.model.Hero;
import ru.course.aston.model.HeroToFraction;
import ru.course.aston.repository.FractionRepository;
import ru.course.aston.repository.HeroRepository;
import ru.course.aston.repository.HeroToFractionRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HeroToFractionRepositoryImpl implements HeroToFractionRepository {
    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private PreparedStatement statement;
    HeroRepository heroRepository = new HeroRepositoryImpl();
    FractionRepository fractionRepository = new FractionRepositoryImpl();

    @Override
    public HeroToFraction findById(Long id) {
        try {
            String sql = "SELECT * FROM wow_db.heroes_fractions WHERE heroes_fraction_id =" + id;
            statement = connectionManager.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Hero hero = heroRepository.findById(resultSet.getLong("hero_id"));
                Fraction fraction = fractionRepository.findById(resultSet.getLong("fraction_id"));
                return new HeroToFraction(
                        resultSet.getLong("heroes_fraction_id"),
                        hero,
                        fraction);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            String sql = "DELETE FROM wow_db.heroes_fractions WHERE heroes_fraction_id=" + id;
            statement = connectionManager.getConnection().prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HeroToFraction save(HeroToFraction heroToFraction) {
        try {
            String sql = "INSERT INTO wow_db.heroes_fractions (hero_id, fraction_id) VALUES (?, ?)";
            statement = connectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, heroToFraction.getHero().getHeroId());
            statement.setLong(2, heroToFraction.getFraction().getFractionId());
            statement.executeUpdate();
            Hero hero = heroRepository.findById(heroToFraction.getHero().getHeroId());
            Fraction fraction = fractionRepository.findById(heroToFraction.getFraction().getFractionId());
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                heroToFraction = new HeroToFraction(
                        generatedKeys.getLong(1),
                        hero,
                        fraction
                );
            }
            return heroToFraction;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HeroToFraction> findAll() {
        List<HeroToFraction> HeroToFractionList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM wow_db.heroes_fractions";
            statement = connectionManager.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                HeroToFractionList.add(new HeroToFraction(
                        resultSet.getLong("heroes_fraction_id"),
                        heroRepository.findById(resultSet.getLong("hero_id")),
                        fractionRepository.findById(resultSet.getLong("fraction_id"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return HeroToFractionList;

    }

    @Override
    public void update(HeroToFraction models) {
        try {
            String sql = "UPDATE wow_db.heroes_fractions SET hero_id = ?, fraction_id = ? WHERE heroes_fraction_id = ?";
            statement = connectionManager.getConnection().prepareStatement(sql);
            statement.setLong(1, models.getHero().getHeroId());
            statement.setLong(2, models.getFraction().getFractionId());
            statement.setLong(3, models.getHeroToFractionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
