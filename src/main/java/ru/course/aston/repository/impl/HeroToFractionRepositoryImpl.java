package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Fraction;
import ru.course.aston.model.Hero;
import ru.course.aston.model.HeroToFraction;
import ru.course.aston.repository.FractionRepository;
import ru.course.aston.repository.HeroRepository;
import ru.course.aston.repository.HeroToFractionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroToFractionRepositoryImpl implements HeroToFractionRepository {
    private final ConnectionManager connectionManager;
    HeroRepository heroRepository;
    FractionRepository fractionRepository;
    HeroToFraction heroToFraction;
    public HeroToFractionRepositoryImpl() {
        connectionManager = new ConnectionManagerImpl();
        this.fractionRepository = new FractionRepositoryImpl();
        this.heroRepository = new HeroRepositoryImpl();
    }
    public HeroToFractionRepositoryImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.fractionRepository = new FractionRepositoryImpl(connectionManager);
        this.heroRepository = new HeroRepositoryImpl(connectionManager);
    }

    @Override
    public HeroToFraction findById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        String sql = "SELECT * FROM wow_db.heroes_fractions WHERE heroes_fraction_id =" + id;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Hero hero = heroRepository.findById(resultSet.getLong("hero_id"));
                Fraction fraction = fractionRepository.findById(resultSet.getLong("fraction_id"));
                heroToFraction = new HeroToFraction(
                        resultSet.getLong("heroes_fraction_id"),
                        hero,
                        fraction);
                return heroToFraction;

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        String sql = "DELETE FROM wow_db.heroes_fractions WHERE heroes_fraction_id=" + id;
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HeroToFraction save(HeroToFraction heroToFraction) {
        if (heroToFraction == null) {
            throw new NullPointerException();
        }
        String sql = "INSERT INTO wow_db.heroes_fractions (hero_id, fraction_id) VALUES (?, ?)";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
                this.heroToFraction = heroToFraction;
                this.heroToFraction.setHeroes(heroRepository.findAll());
                this.heroToFraction.setFractions(fractionRepository.findAll());
            }
            return this.heroToFraction;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HeroToFraction> findAll() {
        List<HeroToFraction> heroToFractionList = new ArrayList<>();
        String sql = "SELECT * FROM wow_db.heroes_fractions";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                heroToFractionList.add(new HeroToFraction(
                        resultSet.getLong("heroes_fraction_id"),
                        heroRepository.findById(resultSet.getLong("hero_id")),
                        fractionRepository.findById(resultSet.getLong("fraction_id"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return heroToFractionList;

    }

    @Override
    public void update(HeroToFraction models) {
        if (models == null) {
            throw new NullPointerException();
        }
        String sql = "UPDATE wow_db.heroes_fractions SET hero_id = ?, fraction_id = ? WHERE heroes_fraction_id = ?";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, models.getHero().getHeroId());
            statement.setLong(2, models.getFraction().getFractionId());
            statement.setLong(3, models.getHeroToFractionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
