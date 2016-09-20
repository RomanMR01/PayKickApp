--
-- Скрипт сгенерирован Devart dbForge Studio for MySQL, Версия 7.1.13.0
-- Домашняя страница продукта: http://www.devart.com/ru/dbforge/mysql/studio
-- Дата скрипта: 20.09.2016 0:50:05
-- Версия сервера: 5.7.12-log
-- Версия клиента: 4.1
--


-- 
-- Отключение внешних ключей
-- 
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

-- 
-- Установить режим SQL (SQL mode)
-- 
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 
-- Установка кодировки, с использованием которой клиент будет посылать запросы на сервер
--
SET NAMES 'utf8';

-- 
-- Установка базы данных по умолчанию
--
USE totalizator;

--
-- Описание для таблицы league
--
DROP TABLE IF EXISTS league;
CREATE TABLE league (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы team
--
DROP TABLE IF EXISTS team;
CREATE TABLE team (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  location VARCHAR(255) NOT NULL,
  emblem_url VARCHAR(255) DEFAULT NULL,
  total_wins INT(11) NOT NULL DEFAULT 0,
  total_loses INT(11) NOT NULL DEFAULT 0,
  total_draws INT(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 9
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы user
--
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id INT(11) NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(50) NOT NULL,
  age INT(11) NOT NULL,
  gender ENUM('MALE','FEMALE') NOT NULL,
  email VARCHAR(50) NOT NULL,
  login VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  balance DOUBLE NOT NULL DEFAULT 0,
  avatar_url VARCHAR(255) DEFAULT NULL,
  role ENUM('CLIENT','BOOKMAKER','ADMIN','BOSS') NOT NULL DEFAULT 'CLIENT',
  language ENUM('ua_UA','en_EN') NOT NULL DEFAULT 'en_EN',
  is_banned TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE INDEX email (email),
  UNIQUE INDEX login (login)
)
ENGINE = INNODB
AUTO_INCREMENT = 123
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы game
--
DROP TABLE IF EXISTS game;
CREATE TABLE game (
  id INT(11) NOT NULL AUTO_INCREMENT,
  title VARCHAR(50) NOT NULL,
  location VARCHAR(255) NOT NULL,
  date DATETIME NOT NULL,
  first_team_id INT(11) NOT NULL,
  second_team_id INT(11) NOT NULL,
  first_goals INT(11) DEFAULT NULL,
  second_goals INT(11) DEFAULT NULL,
  status ENUM('NEW','ACTIVE','FINISHED','CANCELED') NOT NULL DEFAULT 'NEW',
  bookmaker_id INT(11) DEFAULT NULL,
  profit DOUBLE DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_game_first_team_id FOREIGN KEY (first_team_id)
    REFERENCES team(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_game_second_team_id FOREIGN KEY (second_team_id)
    REFERENCES team(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_game_user_id FOREIGN KEY (bookmaker_id)
    REFERENCES user(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 24
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы player
--
DROP TABLE IF EXISTS player;
CREATE TABLE player (
  id INT(11) NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(50) NOT NULL,
  age INT(11) NOT NULL,
  total_games INT(11) NOT NULL DEFAULT 0,
  team_id INT(11) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_player_team_id FOREIGN KEY (team_id)
    REFERENCES team(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 104
AVG_ROW_LENGTH = 167
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы team_league
--
DROP TABLE IF EXISTS team_league;
CREATE TABLE team_league (
  team_id INT(11) NOT NULL,
  league_id INT(11) NOT NULL,
  CONSTRAINT FK_team_league_league_id FOREIGN KEY (league_id)
    REFERENCES league(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_team_league_team_id FOREIGN KEY (team_id)
    REFERENCES team(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы total_bet
--
DROP TABLE IF EXISTS total_bet;
CREATE TABLE total_bet (
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_id INT(11) NOT NULL,
  type ENUM('SINGLE','MULTIPLE') NOT NULL,
  amount INT(11) NOT NULL,
  date DATETIME NOT NULL,
  award DOUBLE NOT NULL,
  status ENUM('ACTIVE','WON','LOST','CANCELED') NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (id),
  CONSTRAINT FK_total_bet_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 30
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы goal
--
DROP TABLE IF EXISTS goal;
CREATE TABLE goal (
  id INT(11) NOT NULL AUTO_INCREMENT,
  player_id INT(11) NOT NULL,
  team_id INT(11) NOT NULL,
  game_id INT(11) NOT NULL,
  minute INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_goal_game_id FOREIGN KEY (game_id)
    REFERENCES game(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_goal_player_id FOREIGN KEY (player_id)
    REFERENCES player(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_goal_team_id FOREIGN KEY (team_id)
    REFERENCES team(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 76
AVG_ROW_LENGTH = 218
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы player_coefficient
--
DROP TABLE IF EXISTS player_coefficient;
CREATE TABLE player_coefficient (
  id INT(11) NOT NULL AUTO_INCREMENT,
  game_id INT(11) NOT NULL,
  player_id INT(11) NOT NULL,
  coefficient DOUBLE NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_player_coefficient_game_id FOREIGN KEY (game_id)
    REFERENCES game(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_player_coefficient_player_id FOREIGN KEY (player_id)
    REFERENCES player(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 573
AVG_ROW_LENGTH = 85
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы result_coefficient
--
DROP TABLE IF EXISTS result_coefficient;
CREATE TABLE result_coefficient (
  id INT(11) NOT NULL AUTO_INCREMENT,
  game_id INT(11) NOT NULL,
  result ENUM('C1','C2','CX','C1X','CX2','C12') NOT NULL,
  coefficient DOUBLE NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_result_coefficient_game_id FOREIGN KEY (game_id)
    REFERENCES game(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 127
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы score_coefficient
--
DROP TABLE IF EXISTS score_coefficient;
CREATE TABLE score_coefficient (
  id INT(11) NOT NULL AUTO_INCREMENT,
  game_id INT(11) NOT NULL,
  start_coefficient DOUBLE NOT NULL,
  first_team_coefficient DOUBLE NOT NULL,
  second_team_coefficient DOUBLE NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_score_coefficient_game_id FOREIGN KEY (game_id)
    REFERENCES game(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 22
AVG_ROW_LENGTH = 780
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы single_bet
--
DROP TABLE IF EXISTS single_bet;
CREATE TABLE single_bet (
  id INT(11) NOT NULL AUTO_INCREMENT,
  total_bet_id INT(11) NOT NULL,
  game_id INT(11) NOT NULL,
  category ENUM('GOALS','RESULT','SCORE','PLAYER') NOT NULL,
  coefficient DOUBLE NOT NULL,
  status ENUM('ACTIVE','WON','LOST','CANCELED') NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (id),
  CONSTRAINT FK_single_bet_game_id FOREIGN KEY (game_id)
    REFERENCES game(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_single_bet_total_bet_id FOREIGN KEY (total_bet_id)
    REFERENCES total_bet(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 44
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы total_goals_coefficient
--
DROP TABLE IF EXISTS total_goals_coefficient;
CREATE TABLE total_goals_coefficient (
  id INT(11) NOT NULL AUTO_INCREMENT,
  game_id INT(11) NOT NULL,
  goal_coefficient DOUBLE NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_total_goals_coefficient_game_id FOREIGN KEY (game_id)
    REFERENCES game(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 22
AVG_ROW_LENGTH = 780
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы bet_player
--
DROP TABLE IF EXISTS bet_player;
CREATE TABLE bet_player (
  single_bet_id INT(11) NOT NULL,
  player_id INT(11) NOT NULL,
  CONSTRAINT FK_bet_player_player_id FOREIGN KEY (player_id)
    REFERENCES player(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_bet_player_single_bet_id FOREIGN KEY (single_bet_id)
    REFERENCES single_bet(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AVG_ROW_LENGTH = 1024
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы bet_result
--
DROP TABLE IF EXISTS bet_result;
CREATE TABLE bet_result (
  single_bet_id INT(11) NOT NULL,
  result ENUM('C1','C2','CX','C1X','CX2','C12') NOT NULL,
  CONSTRAINT FK_bet_result_single_bet_id FOREIGN KEY (single_bet_id)
    REFERENCES single_bet(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы bet_score
--
DROP TABLE IF EXISTS bet_score;
CREATE TABLE bet_score (
  single_bet_id INT(11) NOT NULL,
  first_team_score INT(11) NOT NULL,
  second_team_score INT(11) NOT NULL,
  CONSTRAINT FK_bet_score_single_bet_id FOREIGN KEY (single_bet_id)
    REFERENCES single_bet(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы bet_total_goals
--
DROP TABLE IF EXISTS bet_total_goals;
CREATE TABLE bet_total_goals (
  single_bet_id INT(11) NOT NULL,
  goals_count INT(11) NOT NULL,
  CONSTRAINT FK_bet_total_goals_single_bet_id FOREIGN KEY (single_bet_id)
    REFERENCES single_bet(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

-- 
-- Вывод данных для таблицы league
--

-- Таблица totalizator.league не содержит данных

-- 
-- Вывод данных для таблицы team
--
INSERT INTO team VALUES
(1, 'Manchester United', 'Stradford', NULL, 2, 4, 3),
(2, 'Barcelona', 'Barcelona', NULL, 0, 2, 4),
(3, 'Real Madrid', 'Madrid', NULL, 5, 0, 3),
(4, 'Liverpool', 'Liverpool', NULL, 1, 1, 0),
(5, 'PSG', 'Paris', NULL, 1, 0, 1),
(6, 'Karpaty Lviv', 'Lviv', NULL, 1, 0, 3),
(7, 'Manchester City', 'Manchester', NULL, 0, 3, 2),
(8, 'Dream Team', 'Lviv', NULL, 0, 0, 0);

-- 
-- Вывод данных для таблицы user
--
INSERT INTO user VALUES
(115, 'Arsen Nikinak', 23, 'MALE', 'markiss.nikita@mail.ru', 'arsenush', 'bbb2e5d95976c98ee4a525cd2cb3154fd14c0791ba7edf9776d66eb8ee1b64b1', 0, NULL, 'ADMIN', 'en_EN', 0),
(116, 'Ivan Ivanysche', 22, 'MALE', 'markiss.nikita@gmail.com', 'ivan', 'cd0b9452fc376fc4c35a60087b366f70d883fc901524daf1f122fbd319384f6a', 0, NULL, 'BOOKMAKER', 'en_EN', 0),
(117, 'Igor Igorysche', 25, 'MALE', 'arsen.nykytenko@gmail.com', 'igor', '6ab84705e5d695efd532f462bc41ffc7a05f3a097b877dd2b95d168cd9f3b93d', 0.5, NULL, 'CLIENT', 'en_EN', 0),
(118, 'Big Vova', 18, 'MALE', 'vova.big@mail.ru', 'vova', 'd6e89e66652561cd3a4b377c35d9c9dcfaba88895da9668442984d59c308e91d', 20150, NULL, 'CLIENT', 'en_EN', 0),
(119, 'Banan Banan', 23, 'MALE', 'banan@mail.ua', 'banan', '668c222e4a5933f8616b6dc985d5ee015fa13abe1f1d052a4eeaae4170077011', 7405, NULL, 'CLIENT', 'en_EN', 0),
(120, 'Baton Baton', 23, 'MALE', 'baton@gmail.com', 'baton', '418c01e890606d2da936e53e1c58b165991c3c447a47b9428cb4d46829509233', 7835, NULL, 'CLIENT', 'en_EN', 0),
(121, 'Eric Eric', 23, 'MALE', 'eric@mail.ru', 'eric12', '2c87f901f18a0c9fb4ddb0100c540103e67ca2994121bc6d2db077129028dccc', 6869.5, NULL, 'CLIENT', 'en_EN', 0),
(122, 'Valera Valera', 32, 'MALE', 'valera@mail.ru', 'valera1', '74ad9e489ae2c16b5a1661258a6fddee645083625485f7b0d38608a9faba805a', 10732, NULL, 'CLIENT', 'en_EN', 0);

-- 
-- Вывод данных для таблицы game
--
INSERT INTO game VALUES
(1, '1/8 Euro Cup', 'London', '2016-09-10 20:45:00', 1, 7, 1, 0, 'FINISHED', 116, -245),
(2, '1/8 Euro Cup', 'Paris', '2016-09-10 15:30:00', 3, 4, 4, 3, 'FINISHED', 116, 1000),
(3, '1/8 Euro Cup', 'Berlin', '2016-09-10 16:30:00', 6, 2, NULL, NULL, 'CANCELED', 116, NULL),
(4, '1/8 Euro Cup', 'Berlin', '2016-09-11 20:45:00', 6, 2, 5, 4, 'FINISHED', 116, 4245),
(5, '1/4 Euro Cup', 'London', '2016-09-13 20:45:00', 1, 2, 5, 3, 'FINISHED', 116, 0),
(6, '1/4 Euro Cup', 'London', '2016-09-12 15:45:00', 4, 7, 5, 3, 'FINISHED', 116, 18965),
(7, '1/4 Euro Cup', 'Paris', '2016-09-14 15:45:00', 7, 5, 3, 4, 'FINISHED', 116, 1035),
(8, '1/4 Euro Cup', 'Lviv', '2016-09-15 20:00:00', 6, 3, 3, 3, 'FINISHED', 116, 10000),
(9, '1/2 Euro Cup', 'Kyiv', '2016-09-17 21:00:00', 2, 7, 3, 3, 'FINISHED', 116, -106.5),
(10, '1/2 Euro Cup', 'Madrid', '2016-09-18 19:00:00', 3, 1, 4, 3, 'CANCELED', 116, NULL),
(11, 'Final Euro Cup', 'Lviv', '2016-09-20 21:15:00', 3, 2, 0, 0, 'FINISHED', 116, 30081),
(12, '1/8 World Cup', 'London', '2016-09-21 21:45:00', 3, 1, 1, 0, 'FINISHED', 116, 500),
(13, '1/8 World Cup', 'London', '2016-09-22 21:45:00', 6, 2, 0, 0, 'FINISHED', 116, -1000),
(14, 'Friendly Match', 'Berlin', '2016-09-26 21:45:00', 1, 7, 0, 0, 'FINISHED', 116, -2775),
(15, 'Friendly Match', 'Barcelona', '2016-09-23 19:45:00', 2, 3, 0, 0, 'FINISHED', 116, 5837.5),
(16, 'Friendly Match', 'Berlin', '2016-09-24 15:00:00', 6, 1, 1, 1, 'FINISHED', 116, 350),
(17, '1/8 Euro Cup', 'Lviv', '2016-09-25 22:00:00', 6, 1, NULL, NULL, 'ACTIVE', 116, NULL),
(18, 'Premiere League', 'London', '2016-09-26 15:00:00', 1, 7, NULL, NULL, 'ACTIVE', 116, NULL),
(19, 'Friendly Match', 'Paris', '2016-09-25 16:45:00', 5, 1, 0, 0, 'FINISHED', 116, 4096),
(20, 'Friendly Match', 'London', '2016-09-26 15:45:00', 1, 4, NULL, NULL, 'ACTIVE', 116, NULL),
(21, '1/4 World Cup', 'London', '2016-09-28 20:15:00', 1, 2, NULL, NULL, 'ACTIVE', 116, NULL),
(22, 'Friendly Match', 'Lviv', '2016-09-29 20:45:00', 7, 6, NULL, NULL, 'NEW', 116, NULL),
(23, 'Friendly Match', ':iverpool', '2016-09-30 20:00:00', 4, 6, NULL, NULL, 'ACTIVE', 116, NULL);

-- 
-- Вывод данных для таблицы player
--
INSERT INTO player VALUES
(1, 'Paul Pogba', 23, 9, 1),
(2, 'Zlatan Ibrahimovich', 34, 9, 1),
(3, 'Henrich Mkhitaryan', 27, 9, 1),
(4, 'Wayne Rooney', 30, 9, 1),
(5, 'Bastian Schweinsteiger', 32, 9, 1),
(6, 'Eric Bayee', 22, 9, 1),
(7, 'Marcus Rashford', 18, 9, 1),
(8, 'Antony Marsyal', 20, 9, 1),
(9, 'Juan Mata', 28, 9, 1),
(10, 'Memphis Delay', 22, 9, 1),
(11, 'David De Gea', 25, 9, 1),
(12, 'Maruan Fellayni', 28, 9, 1),
(13, 'Lionel Messi', 29, 6, 2),
(14, 'Neymar Da Silva', 24, 6, 2),
(15, 'Louis Suarez', 29, 6, 2),
(16, 'Pako Alcacer', 23, 6, 2),
(17, 'Samuel Umtiti', 22, 6, 2),
(18, 'Arda Turan', 29, 6, 2),
(19, 'Andre Gomesh', 23, 6, 2),
(20, 'Jasper Sillessen', 27, 6, 2),
(21, 'Gerard Pique', 29, 6, 2),
(22, 'Luka Deen', 23, 6, 2),
(23, 'Andres Injesta', 32, 6, 2),
(24, 'Denis Suarez', 22, 6, 2),
(25, 'Mark Ter Schtegen', 24, 6, 2),
(26, 'Ivan Rakitic', 28, 6, 2),
(27, 'Christiano Ronaldo', 31, 8, 3),
(28, 'Garret Bale', 27, 8, 3),
(29, 'Hames Rodrigues', 25, 8, 3),
(30, 'Karim Benzema', 28, 8, 3),
(31, 'Alvaro Morata', 23, 8, 3),
(32, 'Marco Axenio', 20, 8, 3),
(33, 'Sergio Ramos', 30, 8, 3),
(34, 'Francisco Suarez', 24, 8, 3),
(35, 'Toni Cross', 26, 8, 3),
(36, 'Keppler Pepe', 33, 8, 3),
(37, 'Keilor Navas', 29, 8, 3),
(38, 'Luka Modric', 31, 8, 3),
(39, 'Marcelo Vieyra', 28, 8, 3),
(40, 'Mariano Dias', 23, 8, 3),
(41, 'Sadio Mane', 24, 2, 4),
(42, 'Philippe Coutigno', 24, 2, 4),
(43, 'Daniel Starrige', 27, 2, 4),
(44, 'Georginho Waynaldum', 25, 2, 4),
(45, 'Loris Carius', 23, 2, 4),
(46, 'Mamadu Sako', 26, 2, 4),
(47, 'Ragnar Klavan', 30, 2, 4),
(48, 'Goel Matip', 25, 2, 4),
(49, 'Roberto Firminho', 24, 2, 4),
(50, 'Marco Grujich', 20, 2, 4),
(51, 'Adam Lallana', 28, 2, 4),
(52, 'Alberto Perez', 24, 2, 4),
(53, 'Emre Djan', 22, 2, 4),
(54, 'Divok Orege', 21, 2, 4),
(55, 'Khatem Ben Arfa', 29, 2, 5),
(56, 'Hese Rodriguez', 23, 2, 5),
(57, 'Edinson Cavanee', 29, 2, 5),
(58, 'Grzegorz Krychowiak', 26, 2, 5),
(59, 'Blez Matuidi', 29, 2, 5),
(60, 'Angel Di Maria', 28, 2, 5),
(61, 'Marco Verratty', 23, 2, 5),
(62, 'Lucas Silva', 24, 2, 5),
(63, 'Thomas Meunier', 25, 2, 5),
(64, 'Marcos Correa', 22, 2, 5),
(65, 'Teago Silva', 31, 2, 5),
(66, 'Xavier Pastore', 27, 2, 5),
(67, 'Serge Orieur', 23, 2, 5),
(68, 'Layvin Kurzawa', 24, 2, 5),
(69, 'Igor Khudobyak', 31, 4, 6),
(70, 'Gustavo Leschuk', 23, 4, 6),
(71, 'Ambrosiy Chachua', 22, 4, 6),
(72, 'Paul Ksionz', 29, 4, 6),
(73, 'Roman Pidkivka', 21, 4, 6),
(74, 'Volodymyr Kostevich', 23, 4, 6),
(75, 'Gegam Kadymyan', 23, 4, 6),
(76, 'Arthur Karnoza', 26, 4, 6),
(77, 'Arthur Novotryasov', 24, 4, 6),
(78, 'Roman Mysak', 25, 4, 6),
(79, 'Vasyl Kravets', 19, 4, 6),
(80, 'Denys Miroshnichenko', 21, 4, 6),
(81, 'Gabriel Ochekuvu', 21, 4, 6),
(82, 'Sergiy Rudyka', 28, 4, 6),
(83, 'Vadym Strashkevych', 22, 4, 6),
(84, 'Sergio Aguero', 28, 5, 7),
(85, 'Lerroy Sane', 20, 5, 7),
(86, 'Claudio Bravo', 33, 5, 7),
(87, 'John Stones', 22, 5, 7),
(88, 'Manuel Nolito', 29, 5, 7),
(89, 'Ilkay Guundogan', 25, 5, 7),
(90, 'Yaya Toure', 33, 5, 7),
(91, 'Kevin De Brugne', 25, 5, 7),
(92, 'Rakhim Sterling', 21, 5, 7),
(93, 'Kelechi Ikheanacho', 19, 5, 7),
(94, 'David Silva', 30, 5, 7),
(95, 'Vensan Companee', 30, 5, 7),
(96, 'Louis Fernandinho', 31, 5, 7),
(97, 'Vilfredo Cabaliero', 34, 5, 7),
(98, 'Gosha Kran', 20, 0, 8),
(99, 'Valera Kaban', 19, 0, 8),
(100, 'Vova Garage', 19, 0, 8),
(101, 'William Norrigton', 27, 0, 8),
(102, 'Kasha Malasha', 27, 0, 8),
(103, 'Liva Noga', 20, 0, 8);

-- 
-- Вывод данных для таблицы team_league
--

-- Таблица totalizator.team_league не содержит данных

-- 
-- Вывод данных для таблицы total_bet
--
INSERT INTO total_bet VALUES
(1, 117, 'MULTIPLE', 100, '2016-09-19 23:06:46', 345, 'WON'),
(2, 117, 'MULTIPLE', 1000, '2016-09-19 23:24:36', 14370, 'LOST'),
(3, 117, 'SINGLE', 1350, '2016-09-19 23:35:14', 1620, 'LOST'),
(4, 117, 'MULTIPLE', 2895, '2016-09-19 23:35:54', 26634, 'CANCELED'),
(5, 117, 'MULTIPLE', 18965, '2016-09-19 23:40:16', 91032, 'LOST'),
(6, 117, 'MULTIPLE', 1035, '2016-09-19 23:43:34', 8694, 'LOST'),
(7, 117, 'MULTIPLE', 10000, '2016-09-19 23:45:49', 3840000, 'LOST'),
(8, 117, 'SINGLE', 355, '2016-09-19 23:48:29', 461.5, 'WON'),
(9, 117, 'MULTIPLE', 29645, '2016-09-19 23:54:51', 400207.5, 'LOST'),
(10, 117, 'SINGLE', 10, '2016-09-19 23:55:49', 30, 'CANCELED'),
(11, 117, 'SINGLE', 5, '2016-09-19 23:56:32', 25, 'CANCELED'),
(12, 117, 'MULTIPLE', 5, '2016-09-19 23:56:39', 125, 'CANCELED'),
(13, 117, 'SINGLE', 5, '2016-09-19 23:56:44', 25, 'CANCELED'),
(14, 117, 'SINGLE', 436, '2016-09-20 00:00:30', 654, 'LOST'),
(15, 118, 'MULTIPLE', 500, '2016-09-20 00:08:59', 1950, 'LOST'),
(16, 118, 'SINGLE', 500, '2016-09-20 00:11:18', 1500, 'WON'),
(17, 119, 'SINGLE', 1475, '2016-09-20 00:20:53', 2212.5, 'WON'),
(18, 119, 'SINGLE', 385, '2016-09-20 00:21:07', 577.5, 'WON'),
(19, 119, 'SINGLE', 3525, '2016-09-20 00:21:22', 4230, 'LOST'),
(20, 120, 'SINGLE', 1350, '2016-09-20 00:22:25', 2025, 'WON'),
(21, 120, 'SINGLE', 3512, '2016-09-20 00:22:37', 4565.6, 'LOST'),
(22, 120, 'SINGLE', 1344, '2016-09-20 00:22:48', 2016, 'WON'),
(23, 121, 'SINGLE', 1375, '2016-09-20 00:24:13', 2062.5, 'WON'),
(24, 121, 'SINGLE', 2500, '2016-09-20 00:24:22', 3250, 'LOST'),
(25, 121, 'SINGLE', 1318, '2016-09-20 00:24:34', 1581.6, 'LOST'),
(26, 122, 'SINGLE', 1350, '2016-09-20 00:25:45', 2025, 'WON'),
(27, 122, 'SINGLE', 18, '2016-09-20 00:25:53', 23.4, 'LOST'),
(28, 122, 'SINGLE', 150, '2016-09-20 00:26:08', 225, 'WON'),
(29, 118, 'SINGLE', 350, '2016-09-20 00:37:56', 525, 'LOST');

-- 
-- Вывод данных для таблицы goal
--
INSERT INTO goal VALUES
(1, 3, 1, 1, 0),
(2, 27, 3, 2, 0),
(3, 28, 3, 2, 0),
(4, 29, 3, 2, 0),
(5, 33, 3, 2, 0),
(6, 42, 4, 2, 0),
(7, 49, 4, 2, 0),
(8, 42, 4, 2, 0),
(9, 69, 6, 4, 0),
(10, 69, 6, 4, 0),
(11, 71, 6, 4, 0),
(12, 74, 6, 4, 0),
(13, 72, 6, 4, 0),
(14, 14, 2, 4, 0),
(15, 13, 2, 4, 0),
(16, 13, 2, 4, 0),
(17, 16, 2, 4, 0),
(18, 1, 1, 5, 0),
(19, 1, 1, 5, 0),
(20, 4, 1, 5, 0),
(21, 4, 1, 5, 0),
(22, 5, 1, 5, 0),
(23, 21, 2, 5, 0),
(24, 14, 2, 5, 0),
(25, 14, 2, 5, 0),
(26, 52, 4, 6, 0),
(27, 41, 4, 6, 0),
(28, 45, 4, 6, 0),
(29, 50, 4, 6, 0),
(30, 50, 4, 6, 0),
(31, 84, 7, 6, 0),
(32, 88, 7, 6, 0),
(33, 84, 7, 6, 0),
(34, 91, 7, 7, 0),
(35, 91, 7, 7, 0),
(36, 93, 7, 7, 0),
(37, 58, 5, 7, 0),
(38, 58, 5, 7, 0),
(39, 58, 5, 7, 0),
(40, 66, 5, 7, 0),
(41, 69, 6, 8, 0),
(42, 70, 6, 8, 0),
(43, 72, 6, 8, 0),
(44, 27, 3, 8, 0),
(45, 27, 3, 8, 0),
(46, 28, 3, 8, 0),
(47, 14, 2, 9, 0),
(48, 21, 2, 9, 0),
(49, 17, 2, 9, 0),
(50, 87, 7, 9, 0),
(51, 84, 7, 9, 0),
(52, 84, 7, 9, 0),
(53, 27, 3, 10, 0),
(54, 27, 3, 10, 0),
(55, 28, 3, 10, 0),
(56, 30, 3, 10, 0),
(57, 2, 1, 10, 0),
(58, 2, 1, 10, 0),
(59, 27, 3, 10, 0),
(60, 27, 3, 10, 0),
(61, 28, 3, 10, 0),
(62, 30, 3, 10, 0),
(63, 2, 1, 10, 0),
(64, 2, 1, 10, 0),
(65, 1, 1, 10, 0),
(66, 27, 3, 10, 0),
(67, 27, 3, 10, 0),
(68, 28, 3, 10, 0),
(69, 30, 3, 10, 0),
(70, 2, 1, 10, 0),
(71, 2, 1, 10, 0),
(72, 1, 1, 10, 0),
(73, 27, 3, 12, 0),
(74, 69, 6, 16, 0),
(75, 4, 1, 16, 0);

-- 
-- Вывод данных для таблицы player_coefficient
--
INSERT INTO player_coefficient VALUES
(1, 1, 1, 5),
(2, 1, 2, 3),
(3, 1, 3, 4),
(4, 1, 4, 5),
(5, 1, 5, 5),
(6, 1, 6, 5),
(7, 1, 7, 5),
(8, 1, 8, 5),
(9, 1, 9, 5),
(10, 1, 10, 5),
(11, 1, 11, 5),
(12, 1, 12, 5),
(13, 1, 84, 3),
(14, 1, 85, 5),
(15, 1, 86, 5),
(16, 1, 87, 5),
(17, 1, 88, 5),
(18, 1, 89, 5),
(19, 1, 90, 5),
(20, 1, 91, 3),
(21, 1, 92, 5),
(22, 1, 93, 5),
(23, 1, 94, 5),
(24, 1, 95, 5),
(25, 1, 96, 5),
(26, 1, 97, 5),
(27, 2, 27, 2),
(28, 2, 28, 2),
(29, 2, 29, 5),
(30, 2, 30, 2),
(31, 2, 31, 5),
(32, 2, 32, 5),
(33, 2, 33, 5),
(34, 2, 34, 5),
(35, 2, 35, 5),
(36, 2, 36, 5),
(37, 2, 37, 5),
(38, 2, 38, 5),
(39, 2, 39, 5),
(40, 2, 40, 5),
(41, 2, 41, 5),
(42, 2, 42, 5),
(43, 2, 43, 3),
(44, 2, 44, 5),
(45, 2, 45, 3),
(46, 2, 46, 5),
(47, 2, 47, 5),
(48, 2, 48, 5),
(49, 2, 49, 5),
(50, 2, 50, 5),
(51, 2, 51, 5),
(52, 2, 52, 5),
(53, 2, 53, 5),
(54, 2, 54, 5),
(55, 4, 69, 5),
(56, 4, 70, 5),
(57, 4, 71, 5),
(58, 4, 72, 5),
(59, 4, 73, 5),
(60, 4, 74, 5),
(61, 4, 75, 5),
(62, 4, 76, 5),
(63, 4, 77, 5),
(64, 4, 78, 5),
(65, 4, 79, 5),
(66, 4, 80, 5),
(67, 4, 81, 5),
(68, 4, 82, 5),
(69, 4, 83, 5),
(70, 4, 13, 2),
(71, 4, 14, 2),
(72, 4, 15, 2),
(73, 4, 16, 5),
(74, 4, 17, 5),
(75, 4, 18, 5),
(76, 4, 19, 5),
(77, 4, 20, 5),
(78, 4, 21, 5),
(79, 4, 22, 5),
(80, 4, 23, 5),
(81, 4, 24, 5),
(82, 4, 25, 5),
(83, 4, 26, 5),
(84, 5, 1, 2),
(85, 5, 2, 2),
(86, 5, 3, 2),
(87, 5, 4, 2),
(88, 5, 5, 3),
(89, 5, 6, 5),
(90, 5, 7, 5),
(91, 5, 8, 5),
(92, 5, 9, 5),
(93, 5, 10, 5),
(94, 5, 11, 5),
(95, 5, 12, 5),
(96, 5, 13, 2),
(97, 5, 14, 2),
(98, 5, 15, 2),
(99, 5, 16, 5),
(100, 5, 17, 5),
(101, 5, 18, 5),
(102, 5, 19, 5),
(103, 5, 20, 5),
(104, 5, 21, 5),
(105, 5, 22, 5),
(106, 5, 23, 5),
(107, 5, 24, 5),
(108, 5, 25, 5),
(109, 5, 26, 5),
(110, 6, 41, 3),
(111, 6, 42, 3),
(112, 6, 43, 2),
(113, 6, 44, 5),
(114, 6, 45, 5),
(115, 6, 46, 5),
(116, 6, 47, 5),
(117, 6, 48, 5),
(118, 6, 49, 5),
(119, 6, 50, 5),
(120, 6, 51, 5),
(121, 6, 52, 5),
(122, 6, 53, 5),
(123, 6, 54, 5),
(124, 6, 84, 3),
(125, 6, 85, 3),
(126, 6, 86, 2),
(127, 6, 87, 4),
(128, 6, 88, 5),
(129, 6, 89, 5),
(130, 6, 90, 5),
(131, 6, 91, 2),
(132, 6, 92, 5),
(133, 6, 93, 5),
(134, 6, 94, 5),
(135, 6, 95, 5),
(136, 6, 96, 5),
(137, 6, 97, 5),
(138, 7, 84, 3),
(139, 7, 85, 3),
(140, 7, 86, 4),
(141, 7, 87, 5),
(142, 7, 88, 5),
(143, 7, 89, 5),
(144, 7, 90, 5),
(145, 7, 91, 5),
(146, 7, 92, 5),
(147, 7, 93, 5),
(148, 7, 94, 5),
(149, 7, 95, 5),
(150, 7, 96, 5),
(151, 7, 97, 5),
(152, 7, 55, 3),
(153, 7, 56, 4),
(154, 7, 57, 2),
(155, 7, 58, 5),
(156, 7, 59, 5),
(157, 7, 60, 3),
(158, 7, 61, 5),
(159, 7, 62, 5),
(160, 7, 63, 5),
(161, 7, 64, 5),
(162, 7, 65, 5),
(163, 7, 66, 5),
(164, 7, 67, 5),
(165, 7, 68, 5),
(166, 8, 69, 5),
(167, 8, 70, 5),
(168, 8, 71, 1.5),
(169, 8, 72, 5),
(170, 8, 73, 5),
(171, 8, 74, 5),
(172, 8, 75, 5),
(173, 8, 76, 5),
(174, 8, 77, 5),
(175, 8, 78, 5),
(176, 8, 79, 5),
(177, 8, 80, 5),
(178, 8, 81, 5),
(179, 8, 82, 5),
(180, 8, 83, 5),
(181, 8, 27, 3),
(182, 8, 28, 2),
(183, 8, 29, 4),
(184, 8, 30, 5),
(185, 8, 31, 5),
(186, 8, 32, 5),
(187, 8, 33, 5),
(188, 8, 34, 5),
(189, 8, 35, 5),
(190, 8, 36, 5),
(191, 8, 37, 5),
(192, 8, 38, 5),
(193, 8, 39, 5),
(194, 8, 40, 5),
(195, 9, 13, 3),
(196, 9, 14, 3),
(197, 9, 15, 3),
(198, 9, 16, 5),
(199, 9, 17, 5),
(200, 9, 18, 5),
(201, 9, 19, 5),
(202, 9, 20, 5),
(203, 9, 21, 5),
(204, 9, 22, 5),
(205, 9, 23, 5),
(206, 9, 24, 5),
(207, 9, 25, 5),
(208, 9, 26, 5),
(209, 9, 84, 3),
(210, 9, 85, 3),
(211, 9, 86, 3),
(212, 9, 87, 5),
(213, 9, 88, 5),
(214, 9, 89, 5),
(215, 9, 90, 5),
(216, 9, 91, 5),
(217, 9, 92, 5),
(218, 9, 93, 5),
(219, 9, 94, 5),
(220, 9, 95, 5),
(221, 9, 96, 5),
(222, 9, 97, 5),
(223, 10, 27, 3),
(224, 10, 28, 2),
(225, 10, 29, 2),
(226, 10, 30, 4),
(227, 10, 31, 3),
(228, 10, 32, 5),
(229, 10, 33, 5),
(230, 10, 34, 5),
(231, 10, 35, 5),
(232, 10, 36, 5),
(233, 10, 37, 5),
(234, 10, 38, 5),
(235, 10, 39, 5),
(236, 10, 40, 5),
(237, 10, 1, 3),
(238, 10, 2, 3),
(239, 10, 3, 2),
(240, 10, 4, 2),
(241, 10, 5, 2),
(242, 10, 6, 5),
(243, 10, 7, 5),
(244, 10, 8, 5),
(245, 10, 9, 5),
(246, 10, 10, 5),
(247, 10, 11, 5),
(248, 10, 12, 5),
(249, 11, 27, 3),
(250, 11, 28, 3),
(251, 11, 29, 3),
(252, 11, 30, 2),
(253, 11, 31, 5),
(254, 11, 32, 5),
(255, 11, 33, 5),
(256, 11, 34, 5),
(257, 11, 35, 5),
(258, 11, 36, 5),
(259, 11, 37, 5),
(260, 11, 38, 5),
(261, 11, 39, 5),
(262, 11, 40, 5),
(263, 11, 13, 2),
(264, 11, 14, 3),
(265, 11, 15, 3),
(266, 11, 16, 4),
(267, 11, 17, 5),
(268, 11, 18, 5),
(269, 11, 19, 5),
(270, 11, 20, 5),
(271, 11, 21, 5),
(272, 11, 22, 5),
(273, 11, 23, 5),
(274, 11, 24, 5),
(275, 11, 25, 5),
(276, 11, 26, 5),
(277, 12, 27, 3),
(278, 12, 28, 3),
(279, 12, 29, 3),
(280, 12, 30, 5),
(281, 12, 31, 5),
(282, 12, 32, 5),
(283, 12, 33, 5),
(284, 12, 34, 5),
(285, 12, 35, 5),
(286, 12, 36, 5),
(287, 12, 37, 5),
(288, 12, 38, 5),
(289, 12, 39, 5),
(290, 12, 40, 5),
(291, 12, 1, 5),
(292, 12, 2, 2),
(293, 12, 3, 3),
(294, 12, 4, 3),
(295, 12, 5, 3),
(296, 12, 6, 5),
(297, 12, 7, 5),
(298, 12, 8, 5),
(299, 12, 9, 5),
(300, 12, 10, 5),
(301, 12, 11, 5),
(302, 12, 12, 5),
(303, 13, 69, 3),
(304, 13, 70, 5),
(305, 13, 71, 4),
(306, 13, 72, 5),
(307, 13, 73, 7),
(308, 13, 74, 5),
(309, 13, 75, 5),
(310, 13, 76, 5),
(311, 13, 77, 5),
(312, 13, 78, 5),
(313, 13, 79, 5),
(314, 13, 80, 5),
(315, 13, 81, 5),
(316, 13, 82, 5),
(317, 13, 83, 5),
(318, 13, 13, 2),
(319, 13, 14, 3),
(320, 13, 15, 3),
(321, 13, 16, 3),
(322, 13, 17, 5),
(323, 13, 18, 5),
(324, 13, 19, 5),
(325, 13, 20, 5),
(326, 13, 21, 5),
(327, 13, 22, 5),
(328, 13, 23, 5),
(329, 13, 24, 5),
(330, 13, 25, 5),
(331, 13, 26, 5),
(332, 14, 1, 3),
(333, 14, 2, 3),
(334, 14, 3, 3),
(335, 14, 4, 5),
(336, 14, 5, 5),
(337, 14, 6, 5),
(338, 14, 7, 5),
(339, 14, 8, 5),
(340, 14, 9, 5),
(341, 14, 10, 5),
(342, 14, 11, 5),
(343, 14, 12, 5),
(344, 14, 84, 3),
(345, 14, 85, 3),
(346, 14, 86, 3),
(347, 14, 87, 2),
(348, 14, 88, 5),
(349, 14, 89, 5),
(350, 14, 90, 5),
(351, 14, 91, 5),
(352, 14, 92, 5),
(353, 14, 93, 5),
(354, 14, 94, 5),
(355, 14, 95, 5),
(356, 14, 96, 5),
(357, 14, 97, 5),
(358, 19, 55, 3),
(359, 19, 56, 3),
(360, 19, 57, 2),
(361, 19, 58, 5),
(362, 19, 59, 5),
(363, 19, 60, 5),
(364, 19, 61, 5),
(365, 19, 62, 5),
(366, 19, 63, 5),
(367, 19, 64, 5),
(368, 19, 65, 5),
(369, 19, 66, 5),
(370, 19, 67, 5),
(371, 19, 68, 5),
(372, 19, 1, 5),
(373, 19, 2, 2),
(374, 19, 3, 2),
(375, 19, 4, 2),
(376, 19, 5, 3),
(377, 19, 6, 5),
(378, 19, 7, 5),
(379, 19, 8, 5),
(380, 19, 9, 5),
(381, 19, 10, 5),
(382, 19, 11, 5),
(383, 19, 12, 5),
(384, 15, 13, 2),
(385, 15, 14, 2),
(386, 15, 15, 2),
(387, 15, 16, 3),
(388, 15, 17, 3),
(389, 15, 18, 5),
(390, 15, 19, 5),
(391, 15, 20, 5),
(392, 15, 21, 5),
(393, 15, 22, 5),
(394, 15, 23, 5),
(395, 15, 24, 5),
(396, 15, 25, 5),
(397, 15, 26, 5),
(398, 15, 27, 2),
(399, 15, 28, 2),
(400, 15, 29, 3),
(401, 15, 30, 2),
(402, 15, 31, 4),
(403, 15, 32, 5),
(404, 15, 33, 5),
(405, 15, 34, 5),
(406, 15, 35, 5),
(407, 15, 36, 5),
(408, 15, 37, 5),
(409, 15, 38, 5),
(410, 15, 39, 5),
(411, 15, 40, 5),
(412, 16, 69, 4),
(413, 16, 70, 4),
(414, 16, 71, 4),
(415, 16, 72, 5),
(416, 16, 73, 5),
(417, 16, 74, 5),
(418, 16, 75, 5),
(419, 16, 76, 5),
(420, 16, 77, 5),
(421, 16, 78, 5),
(422, 16, 79, 5),
(423, 16, 80, 5),
(424, 16, 81, 5),
(425, 16, 82, 5),
(426, 16, 83, 5),
(427, 16, 1, 5),
(428, 16, 2, 2),
(429, 16, 3, 3),
(430, 16, 4, 2),
(431, 16, 5, 3),
(432, 16, 6, 5),
(433, 16, 7, 5),
(434, 16, 8, 5),
(435, 16, 9, 5),
(436, 16, 10, 5),
(437, 16, 11, 5),
(438, 16, 12, 5),
(439, 17, 69, 5),
(440, 17, 70, 5),
(441, 17, 71, 5),
(442, 17, 72, 5),
(443, 17, 73, 5),
(444, 17, 74, 5),
(445, 17, 75, 5),
(446, 17, 76, 5),
(447, 17, 77, 5),
(448, 17, 78, 5),
(449, 17, 79, 5),
(450, 17, 80, 5),
(451, 17, 81, 5),
(452, 17, 82, 5),
(453, 17, 83, 5),
(454, 17, 1, 5),
(455, 17, 2, 5),
(456, 17, 3, 5),
(457, 17, 4, 5),
(458, 17, 5, 5),
(459, 17, 6, 5),
(460, 17, 7, 5),
(461, 17, 8, 5),
(462, 17, 9, 5),
(463, 17, 10, 5),
(464, 17, 11, 5),
(465, 17, 12, 5),
(466, 18, 1, 3),
(467, 18, 2, 2),
(468, 18, 3, 3),
(469, 18, 4, 3),
(470, 18, 5, 3),
(471, 18, 6, 5),
(472, 18, 7, 5),
(473, 18, 8, 4),
(474, 18, 9, 5),
(475, 18, 10, 5),
(476, 18, 11, 5),
(477, 18, 12, 5),
(478, 18, 84, 2),
(479, 18, 85, 2),
(480, 18, 86, 3),
(481, 18, 87, 3),
(482, 18, 88, 4),
(483, 18, 89, 4),
(484, 18, 90, 5),
(485, 18, 91, 2),
(486, 18, 92, 5),
(487, 18, 93, 5),
(488, 18, 94, 5),
(489, 18, 95, 5),
(490, 18, 96, 5),
(491, 18, 97, 5),
(492, 23, 41, 3),
(493, 23, 42, 3),
(494, 23, 43, 4),
(495, 23, 44, 3),
(496, 23, 45, 2),
(497, 23, 46, 5),
(498, 23, 47, 5),
(499, 23, 48, 5),
(500, 23, 49, 5),
(501, 23, 50, 5),
(502, 23, 51, 5),
(503, 23, 52, 5),
(504, 23, 53, 5),
(505, 23, 54, 5),
(506, 23, 69, 4),
(507, 23, 70, 4),
(508, 23, 71, 4),
(509, 23, 72, 5),
(510, 23, 73, 4),
(511, 23, 74, 5),
(512, 23, 75, 5),
(513, 23, 76, 5),
(514, 23, 77, 5),
(515, 23, 78, 5),
(516, 23, 79, 5),
(517, 23, 80, 5),
(518, 23, 81, 5),
(519, 23, 82, 5),
(520, 23, 83, 5),
(521, 20, 1, 3),
(522, 20, 2, 2),
(523, 20, 3, 3),
(524, 20, 4, 2),
(525, 20, 5, 2),
(526, 20, 6, 5),
(527, 20, 7, 5),
(528, 20, 8, 5),
(529, 20, 9, 5),
(530, 20, 10, 5),
(531, 20, 11, 5),
(532, 20, 12, 5),
(533, 20, 41, 3),
(534, 20, 42, 3),
(535, 20, 43, 3),
(536, 20, 44, 4),
(537, 20, 45, 2),
(538, 20, 46, 2),
(539, 20, 47, 5),
(540, 20, 48, 5),
(541, 20, 49, 5),
(542, 20, 50, 5),
(543, 20, 51, 5),
(544, 20, 52, 5),
(545, 20, 53, 5),
(546, 20, 54, 5),
(547, 21, 1, 3),
(548, 21, 2, 2),
(549, 21, 3, 3),
(550, 21, 4, 2),
(551, 21, 5, 2),
(552, 21, 6, 5),
(553, 21, 7, 5),
(554, 21, 8, 5),
(555, 21, 9, 5),
(556, 21, 10, 5),
(557, 21, 11, 5),
(558, 21, 12, 5),
(559, 21, 13, 2),
(560, 21, 14, 2),
(561, 21, 15, 2),
(562, 21, 16, 3),
(563, 21, 17, 2),
(564, 21, 18, 5),
(565, 21, 19, 5),
(566, 21, 20, 5),
(567, 21, 21, 5),
(568, 21, 22, 5),
(569, 21, 23, 5),
(570, 21, 24, 5),
(571, 21, 25, 5),
(572, 21, 26, 5);

-- 
-- Вывод данных для таблицы result_coefficient
--
INSERT INTO result_coefficient VALUES
(1, 1, 'C1', 1.8),
(2, 1, 'C2', 1.2),
(3, 1, 'CX', 1.5),
(4, 1, 'C1X', 1.15),
(5, 1, 'CX2', 1.1),
(6, 1, 'C12', 1.1),
(7, 2, 'C1', 1.2),
(8, 2, 'C2', 1.8),
(9, 2, 'CX', 1.45),
(10, 2, 'C1X', 1.33),
(11, 2, 'CX2', 1.2),
(12, 2, 'C12', 1.1),
(13, 4, 'C1', 3.5),
(14, 4, 'C2', 1.2),
(15, 4, 'CX', 2.8),
(16, 4, 'C1X', 2),
(17, 4, 'CX2', 1.15),
(18, 4, 'C12', 1.15),
(19, 5, 'C1', 1.5),
(20, 5, 'C2', 1.5),
(21, 5, 'CX', 1.7),
(22, 5, 'C1X', 1.4),
(23, 5, 'CX2', 1.4),
(24, 5, 'C12', 1.4),
(25, 6, 'C1', 1.4),
(26, 6, 'C2', 1.6),
(27, 6, 'CX', 1.5),
(28, 6, 'C1X', 1.3),
(29, 6, 'CX2', 1.25),
(30, 6, 'C12', 1.25),
(31, 7, 'C1', 1.5),
(32, 7, 'C2', 1.5),
(33, 7, 'CX', 1.5),
(34, 7, 'C1X', 1.4),
(35, 7, 'CX2', 1.4),
(36, 7, 'C12', 1.4),
(37, 8, 'C1', 3.5),
(38, 8, 'C2', 1.2),
(39, 8, 'CX', 2),
(40, 8, 'C1X', 3),
(41, 8, 'CX2', 1.6),
(42, 8, 'C12', 2),
(43, 9, 'C1', 1.5),
(44, 9, 'C2', 1.5),
(45, 9, 'CX', 1.5),
(46, 9, 'C1X', 1.3),
(47, 9, 'CX2', 1.3),
(48, 9, 'C12', 1.3),
(49, 10, 'C1', 1.4),
(50, 10, 'C2', 1.6),
(51, 10, 'CX', 1.5),
(52, 10, 'C1X', 1.3),
(53, 10, 'CX2', 1.4),
(54, 10, 'C12', 1.3),
(55, 11, 'C1', 1.5),
(56, 11, 'C2', 1.5),
(57, 11, 'CX', 1.5),
(58, 11, 'C1X', 1.5),
(59, 11, 'CX2', 1.5),
(60, 11, 'C12', 1.5),
(61, 12, 'C1', 1.5),
(62, 12, 'C2', 1.5),
(63, 12, 'CX', 1.5),
(64, 12, 'C1X', 1.3),
(65, 12, 'CX2', 1.3),
(66, 12, 'C12', 1.3),
(67, 13, 'C1', 4),
(68, 13, 'C2', 1.2),
(69, 13, 'CX', 3),
(70, 13, 'C1X', 2.5),
(71, 13, 'CX2', 1.8),
(72, 13, 'C12', 1.5),
(73, 14, 'C1', 1.5),
(74, 14, 'C2', 1.5),
(75, 14, 'CX', 1.5),
(76, 14, 'C1X', 1.3),
(77, 14, 'CX2', 1.3),
(78, 14, 'C12', 1.3),
(79, 19, 'C1', 1.4),
(80, 19, 'C2', 1.6),
(81, 19, 'CX', 1.5),
(82, 19, 'C1X', 1.3),
(83, 19, 'CX2', 1.5),
(84, 19, 'C12', 1.2),
(85, 15, 'C1', 1.5),
(86, 15, 'C2', 1.5),
(87, 15, 'CX', 1.5),
(88, 15, 'C1X', 1.3),
(89, 15, 'CX2', 1.3),
(90, 15, 'C12', 1.3),
(91, 16, 'C1', 3),
(92, 16, 'C2', 1.3),
(93, 16, 'CX', 2),
(94, 16, 'C1X', 1.9),
(95, 16, 'CX2', 1.6),
(96, 16, 'C12', 1.5),
(97, 17, 'C1', 3),
(98, 17, 'C2', 1.3),
(99, 17, 'CX', 2),
(100, 17, 'C1X', 1.8),
(101, 17, 'CX2', 1.5),
(102, 17, 'C12', 1.2),
(103, 18, 'C1', 1.5),
(104, 18, 'C2', 1.5),
(105, 18, 'CX', 1.5),
(106, 18, 'C1X', 1.3),
(107, 18, 'CX2', 1.3),
(108, 18, 'C12', 1.3),
(109, 23, 'C1', 1.3),
(110, 23, 'C2', 2),
(111, 23, 'CX', 1.7),
(112, 23, 'C1X', 1.6),
(113, 23, 'CX2', 1.8),
(114, 23, 'C12', 1.5),
(115, 20, 'C1', 1.5),
(116, 20, 'C2', 1.5),
(117, 20, 'CX', 1.5),
(118, 20, 'C1X', 1.3),
(119, 20, 'CX2', 1.3),
(120, 20, 'C12', 1.3),
(121, 21, 'C1', 1.7),
(122, 21, 'C2', 1.3),
(123, 21, 'CX', 1.5),
(124, 21, 'C1X', 1.4),
(125, 21, 'CX2', 1.3),
(126, 21, 'C12', 1.2);

-- 
-- Вывод данных для таблицы score_coefficient
--
INSERT INTO score_coefficient VALUES
(1, 1, 1.3, 1.5, 1.4),
(2, 2, 1.2, 1.1, 1.8),
(3, 4, 2, 5, 1.2),
(4, 5, 1.2, 2.5, 2.5),
(5, 6, 1.5, 2, 1.8),
(6, 7, 1.5, 2.4, 2.4),
(7, 8, 2, 4, 2),
(8, 9, 2, 2.4, 2.4),
(9, 10, 2, 2.3, 2.5),
(10, 11, 2, 2, 2),
(11, 12, 2, 2.5, 2.5),
(12, 13, 2, 5, 1.5),
(13, 14, 1.5, 1.5, 1.5),
(14, 19, 2, 1.8, 2),
(15, 15, 1.5, 1.6, 1.6),
(16, 16, 2, 5, 2),
(17, 17, 2, 5, 2),
(18, 18, 2, 2, 2),
(19, 23, 2, 2, 4),
(20, 20, 2, 2, 2),
(21, 21, 2, 3, 2);

-- 
-- Вывод данных для таблицы single_bet
--
INSERT INTO single_bet VALUES
(1, 1, 1, 'RESULT', 1.15, 'WON'),
(2, 1, 1, 'PLAYER', 3, 'WON'),
(3, 2, 2, 'SCORE', 1.2, 'LOST'),
(4, 2, 2, 'PLAYER', 5, 'WON'),
(5, 3, 4, 'RESULT', 1.2, 'LOST'),
(6, 4, 4, 'RESULT', 1.15, 'LOST'),
(7, 4, 5, 'PLAYER', 2, 'WON'),
(8, 4, 5, 'PLAYER', 2, 'LOST'),
(9, 4, 5, 'PLAYER', 2, 'LOST'),
(10, 5, 6, 'RESULT', 1.6, 'LOST'),
(11, 5, 6, 'PLAYER', 3, 'LOST'),
(12, 6, 7, 'RESULT', 1.4, 'LOST'),
(13, 6, 7, 'PLAYER', 3, 'LOST'),
(14, 6, 7, 'PLAYER', 2, 'WON'),
(15, 7, 8, 'GOALS', 2, 'LOST'),
(16, 7, 8, 'PLAYER', 3, 'WON'),
(17, 7, 8, 'PLAYER', 2, 'LOST'),
(18, 8, 9, 'RESULT', 1.3, 'WON'),
(19, 9, 11, 'RESULT', 1.5, 'WON'),
(20, 9, 11, 'PLAYER', 3, 'LOST'),
(21, 9, 11, 'PLAYER', 3, 'LOST'),
(22, 10, 10, 'PLAYER', 3, 'CANCELED'),
(23, 11, 10, 'PLAYER', 5, 'CANCELED'),
(24, 12, 10, 'PLAYER', 5, 'CANCELED'),
(25, 12, 10, 'PLAYER', 5, 'CANCELED'),
(26, 13, 10, 'PLAYER', 5, 'CANCELED'),
(27, 14, 11, 'RESULT', 1.5, 'LOST'),
(28, 15, 12, 'RESULT', 1.3, 'WON'),
(29, 15, 12, 'PLAYER', 3, 'LOST'),
(30, 16, 13, 'RESULT', 3, 'WON'),
(31, 17, 14, 'RESULT', 1.5, 'WON'),
(32, 18, 15, 'RESULT', 1.5, 'WON'),
(33, 19, 19, 'RESULT', 1.2, 'LOST'),
(34, 20, 14, 'RESULT', 1.5, 'WON'),
(35, 21, 15, 'RESULT', 1.3, 'LOST'),
(36, 22, 19, 'RESULT', 1.5, 'WON'),
(37, 23, 14, 'RESULT', 1.5, 'WON'),
(38, 24, 15, 'RESULT', 1.3, 'LOST'),
(39, 25, 19, 'RESULT', 1.2, 'LOST'),
(40, 26, 14, 'RESULT', 1.5, 'WON'),
(41, 27, 15, 'RESULT', 1.3, 'LOST'),
(42, 28, 19, 'RESULT', 1.5, 'WON'),
(43, 29, 16, 'RESULT', 1.5, 'LOST');

-- 
-- Вывод данных для таблицы total_goals_coefficient
--
INSERT INTO total_goals_coefficient VALUES
(1, 1, 1.3),
(2, 2, 1.2),
(3, 4, 2),
(4, 5, 1.2),
(5, 6, 1.5),
(6, 7, 1.5),
(7, 8, 2),
(8, 9, 2),
(9, 10, 2),
(10, 11, 2),
(11, 12, 2),
(12, 13, 2),
(13, 14, 1.5),
(14, 19, 2),
(15, 15, 1.5),
(16, 16, 2),
(17, 17, 2),
(18, 18, 2),
(19, 23, 2),
(20, 20, 2),
(21, 21, 2);

-- 
-- Вывод данных для таблицы bet_player
--
INSERT INTO bet_player VALUES
(2, 3),
(4, 42),
(7, 14),
(8, 15),
(9, 16),
(11, 85),
(13, 56),
(14, 58),
(16, 28),
(17, 29),
(20, 28),
(21, 29),
(22, 28),
(24, 40),
(26, 40),
(29, 28);

-- 
-- Вывод данных для таблицы bet_result
--
INSERT INTO bet_result VALUES
(1, 'C1X'),
(5, 'C2'),
(6, 'CX2'),
(10, 'C2'),
(12, 'C1X'),
(18, 'CX2'),
(19, 'CX'),
(27, 'C12'),
(28, 'C1X'),
(30, 'CX'),
(31, 'CX'),
(32, 'CX'),
(33, 'C12'),
(34, 'CX'),
(35, 'C12'),
(36, 'CX'),
(37, 'CX'),
(38, 'C12'),
(39, 'C12'),
(40, 'CX'),
(41, 'C12'),
(42, 'CX'),
(43, 'C12');

-- 
-- Вывод данных для таблицы bet_score
--
INSERT INTO bet_score VALUES
(3, 3, 1);

-- 
-- Вывод данных для таблицы bet_total_goals
--
INSERT INTO bet_total_goals VALUES
(15, 5);

-- 
-- Восстановить предыдущий режим SQL (SQL mode)
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

-- 
-- Включение внешних ключей
-- 
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;