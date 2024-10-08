package ru.course.aston.repository.impl;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.repository.HeroRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
class HeroRepositoryImplTest {
    static HeroRepository heroRepository;
    static ConnectionManager connectionManager;
    private static JdbcDatabaseDelegate jdbcDatabaseDelegate;
    @Container
    public static final PostgreSQLContainer container = new PostgreSQLContainer<>("postgres:14-alpine");

    @BeforeAll
    public static void startContainer() {
        container.start();
        connectionManager = new ConnectionManagerImpl();
        connectionManager.setDriver(container.getDriverClassName());
        connectionManager.setJdbcUrl(container.getJdbcUrl());
        connectionManager.setUsername(container.getUsername());
        connectionManager.setPassword(container.getPassword());
        jdbcDatabaseDelegate = new JdbcDatabaseDelegate(container, "");
        heroRepository = new HeroRepositoryImpl(connectionManager);
    }

    @BeforeEach
    public void initSchema() {
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, "sql/schema.sql");

    }

    @Test
    void findById() {
        Hero hero = new Hero(2L, "Сильвана", "Ветрокрылова", 3L);
        heroRepository.findById(1L);
        Optional<Hero> result = Optional.ofNullable(heroRepository.findById(hero.getHeroId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(hero.getHeroName(), result.get().getHeroName());
    }

    @Test
    void deleteById() {
        heroRepository.deleteById(8L);
        Optional<Hero> result = Optional.ofNullable(heroRepository.findById(8L));
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void save() {
        Hero hero = new Hero(8L, "Hero", "HeroLastName", 1L);
        Long id = heroRepository.save(hero).getHeroId();
        Optional<Hero> result = Optional.ofNullable(heroRepository.findById(id));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(hero.getHeroName(), result.get().getHeroName());
        heroRepository.deleteById(id);
    }

    @Test
    void findAll() {
        Assertions.assertEquals(7, heroRepository.findAll().size());
    }

    @Test
    void update() {
        Hero hero = new Hero(1L, "NewHero", "NewHeroLastName", 2L);
        heroRepository.update(hero);
        Optional<Hero> result = Optional.ofNullable(heroRepository.findById(hero.getHeroId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(hero.getHeroName(), result.get().getHeroName());
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }

    @Test
    void findByHeroIDNull() {
        assertThrows(NullPointerException.class, () -> heroRepository.findById(null));
    }

    @Test
    void saveNull() {
        assertThrows(NullPointerException.class, () -> heroRepository.save(null));
    }

    @Test
    void updateNull() {
        assertThrows(NullPointerException.class, () -> heroRepository.update(null));
    }

    @Test
    void deleteByIdNull() {
        assertThrows(NullPointerException.class, () -> heroRepository.deleteById(null));
    }

    @Test
    void findByIdSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from hero where hero_id = ?");
                preparedStatement.setLong(1, 1L);
                preparedStatement.executeQuery();
            }
        });
    }

    @Test
    void saveSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into hero (hero_id, hero_name, hero_last_name, team_id) values (?,?,?,?)");
                preparedStatement.setLong(1, 1L);
                preparedStatement.setString(2, "Hero");
                preparedStatement.setString(3, "HeroLastName");
                preparedStatement.setLong(4, 1L);
                preparedStatement.executeUpdate();
            }
        });
    }

    @Test
    void updateSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("update hero set hero_name = ?, hero_last_name = ?, team_id = ? where hero_id = ?");
                preparedStatement.setString(1, "Hero");
                preparedStatement.setString(2, "HeroLastName");
                preparedStatement.setLong(3, 1L);
                preparedStatement.setLong(4, 1L);
                preparedStatement.executeUpdate();
            }
        });
    }

    @Test
    void deleteByIdSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("delete from hero where hero_id = ?");
                preparedStatement.setLong(1, 1L);
                preparedStatement.executeUpdate();
            }
        });
    }

    @Test
    void findAllSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from hero");
                preparedStatement.executeQuery();
            }
        });
    }
    @Test
    void constructor() {
        HeroRepository heroRepository = new HeroRepositoryImpl();
        Assertions.assertNotNull(heroRepository);
    }

}