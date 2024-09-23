DROP SCHEMA IF EXISTS wow_db CASCADE;
CREATE SCHEMA wow_db;
DROP TABLE IF EXISTS wow_db.heroes CASCADE;
DROP TABLE IF EXISTS wow_db.roles CASCADE;
DROP TABLE IF EXISTS wow_db.fractions CASCADE;
DROP TABLE IF EXISTS wow_db.hero_fractions CASCADE;
DROP TABLE IF EXISTS wow_db.maxHP CASCADE;

CREATE TABLE IF NOT EXISTS wow_db.roles
(role_name_id  bigserial PRIMARY KEY,
role_name VARCHAR(255) NOT NULL UNIQUE);

INSERT INTO wow_db.roles (role_name)
VALUES  ('Воин'), --1
        ('Маг'), --2
        ('Лучник'); --3

CREATE TABLE IF NOT EXISTS wow_db.heroes
( hero_id bigserial PRIMARY KEY,
hero_name VARCHAR(255) NOT NULL UNIQUE,
hero_lastname VARCHAR(255) NOT NULL UNIQUE,
role_name_id BIGINT REFERENCES wow_db.roles(role_name_id));


CREATE TABLE IF NOT EXISTS wow_db.fractions
(fraction_id bigserial PRIMARY KEY,
fraction_name VARCHAR(255) NOT NULL);

CREATE TABLE IF NOT EXISTS wow_db.heroes_fractions
( heroes_fraction_id bigserial PRIMARY KEY,
hero_id BIGINT REFERENCES wow_db.heroes(hero_id),
fraction_id BIGINT REFERENCES wow_db.fractions(fraction_id));

CREATE TABLE IF NOT EXISTS wow_db.heroes_maxHP
( heroes_maxHP_id bigserial PRIMARY KEY,
hero_id BIGINT REFERENCES wow_db.heroes(hero_id),
maxHP BIGINT NOT NULL UNIQUE);

INSERT INTO wow_db.heroes (hero_name,hero_lastname, role_name_id)
VALUES  ('Тралл', 'Огриммаров',  1), --1
        ('Сильвана', 'Ветрокрылова',  3), --2
        ('Иллидан', 'Яростьбурьев',  1), --3
        ('Артас', 'Менетилов',  1), --4
        ('Гарош', 'Адскокриков',  1), --5
        ('Джайна', 'Праудмур',  2), --6
        ('Кадгар', 'Верхомагов', 2); --7
INSERT INTO wow_db.fractions (fraction_name)
VALUES  ('Альянс'), --1
        ('Орда'); --2

INSERT INTO wow_db.heroes_fractions (hero_id,fraction_id)
VALUES  (1,2), --1
        (2,1), --2
        (3,1), --3
        (4,1), --4
        (5,2), --5
        (6,1), --6
        (7,1); --7

INSERT INTO wow_db.heroes_maxHP (hero_id,maxHP)
VALUES  (1,11110), --1
        (2,22220), --2
        (3,33330), --3
        (4,44440), --4
        (5,55550), --5
        (6,66660), --6
        (7,77770); --7