package ru.course.aston;

public final class InitSchemeSql {

    public static final String INIT_SCHEME_SQL = "DROP SCHEMA IF EXISTS wow_db CASCADE;\n" +
                                          "CREATE SCHEMA wow_db;\n" +
                                          "DROP TABLE IF EXISTS wow_db.heroes CASCADE;\n" +
                                          "DROP TABLE IF EXISTS wow_db.roles CASCADE;\n" +
                                          "DROP TABLE IF EXISTS wow_db.fractions CASCADE;\n" +
                                          "DROP TABLE IF EXISTS wow_db.hero_fractions CASCADE;\n" +
                                          "DROP TABLE IF EXISTS wow_db.maxHP CASCADE;\n" +
                                          "\n" +
                                          "CREATE TABLE IF NOT EXISTS wow_db.roles\n" +
                                          "(role_name_id  bigserial PRIMARY KEY,\n" +
                                          "role_name VARCHAR(255) NOT NULL UNIQUE);\n" +
                                          "\n" +
                                          "INSERT INTO wow_db.roles (role_name)\n" +
                                          "VALUES  ('Воин'), --1\n" +
                                          "        ('Маг'), --2\n" +
                                          "        ('Лучник'); --3\n" +
                                          "\n" +
                                          "CREATE TABLE IF NOT EXISTS wow_db.heroes\n" +
                                          "( hero_id bigserial PRIMARY KEY,\n" +
                                          "hero_name VARCHAR(255) NOT NULL UNIQUE,\n" +
                                          "hero_lastname VARCHAR(255) NOT NULL UNIQUE,\n" +
                                          "role_name_id BIGINT REFERENCES wow_db.roles(role_name_id));\n" +
                                          "\n" +
                                          "\n" +
                                          "CREATE TABLE IF NOT EXISTS wow_db.fractions\n" +
                                          "(fraction_id bigserial PRIMARY KEY,\n" +
                                          "fraction_name VARCHAR(255) NOT NULL);\n" +
                                          "\n" +
                                          "CREATE TABLE IF NOT EXISTS wow_db.heroes_fractions\n" +
                                          "( heroes_fraction_id bigserial PRIMARY KEY,\n" +
                                          "hero_id BIGINT REFERENCES wow_db.heroes(hero_id),\n" +
                                          "fraction_id BIGINT REFERENCES wow_db.fractions(fraction_id));\n" +
                                          "\n" +
                                          "CREATE TABLE IF NOT EXISTS wow_db.heroes_maxHP\n" +
                                          "( heroes_maxHP_id bigserial PRIMARY KEY,\n" +
                                          "hero_id BIGINT REFERENCES wow_db.heroes(hero_id),\n" +
                                          "maxHP BIGINT NOT NULL UNIQUE);\n" +
                                          "\n" +
                                          "INSERT INTO wow_db.heroes (hero_name,hero_lastname, role_name_id)\n" +
                                          "VALUES  ('Тралл', 'Огриммаров',  1), --1\n" +
                                          "        ('Сильвана', 'Ветрокрылова',  3), --2\n" +
                                          "        ('Иллидан', 'Яростьбурьев',  1), --3\n" +
                                          "        ('Артас', 'Менетилов',  1), --4\n" +
                                          "        ('Гарош', 'Адскокриков',  1), --5\n" +
                                          "        ('Джайна', 'Праудмур',  2), --6\n" +
                                          "        ('Кадгар', 'Верхомагов', 2); --7\n" +
                                          "INSERT INTO wow_db.fractions (fraction_name)\n" +
                                          "VALUES  ('Альянс'), --1\n" +
                                          "        ('Орда'); --2\n" +
                                          "\n" +
                                          "INSERT INTO wow_db.heroes_fractions (hero_id,fraction_id)\n" +
                                          "VALUES  (1,2), --1\n" +
                                          "        (2,1), --2\n" +
                                          "        (3,1), --3\n" +
                                          "        (4,1), --4\n" +
                                          "        (5,2), --5\n" +
                                          "        (6,1), --6\n" +
                                          "        (7,1); --7\n" +
                                          "\n" +
                                          "INSERT INTO wow_db.heroes_maxHP (hero_id,maxHP)\n" +
                                          "VALUES  (1,11110), --1\n" +
                                          "        (2,22220), --2\n" +
                                          "        (3,33330), --3\n" +
                                          "        (4,44440), --4\n" +
                                          "        (5,55550), --5\n" +
                                          "        (6,66660), --6\n" +
                                          "        (7,77770); --7";
}
