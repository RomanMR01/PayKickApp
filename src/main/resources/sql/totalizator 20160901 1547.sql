--
-- Скрипт сгенерирован Devart dbForge Studio for MySQL, Версия 7.1.13.0
-- Домашняя страница продукта: http://www.devart.com/ru/dbforge/mysql/studio
-- Дата скрипта: 9/1/2016 3:47:44 PM
-- Версия сервера: 5.7.11-log
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
CREATE TABLE IF NOT EXISTS league (
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
CREATE TABLE IF NOT EXISTS team (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  location VARCHAR(255) NOT NULL,
  emblem_url VARCHAR(255) NOT NULL,
  total_wins INT(11) NOT NULL DEFAULT 0,
  total_loses INT(11) NOT NULL DEFAULT 0,
  total_draws INT(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы user
--
DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user (
  id INT(11) NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(50) NOT NULL,
  age INT(11) NOT NULL,
  gender ENUM('MALE','FEMALE') NOT NULL,
  email VARCHAR(50) NOT NULL,
  login VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  balance DOUBLE NOT NULL DEFAULT 0,
  avatar_url VARCHAR(255) NOT NULL,
  role ENUM('CLIENT','BOOKMAKER','ADMIN','BOSS') NOT NULL DEFAULT 'CLIENT',
  language ENUM('ua_UA','en_EN') NOT NULL DEFAULT 'en_EN',
  is_banned TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы game
--
DROP TABLE IF EXISTS game;
CREATE TABLE IF NOT EXISTS game (
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
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы player
--
DROP TABLE IF EXISTS player;
CREATE TABLE IF NOT EXISTS player (
  id INT(11) NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(50) NOT NULL,
  age INT(11) NOT NULL,
  total_games INT(11) NOT NULL DEFAULT 0,
  team_id INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_player_team_id FOREIGN KEY (team_id)
    REFERENCES team(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы team_league
--
DROP TABLE IF EXISTS team_league;
CREATE TABLE IF NOT EXISTS team_league (
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
CREATE TABLE IF NOT EXISTS total_bet (
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
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы goal
--
DROP TABLE IF EXISTS goal;
CREATE TABLE IF NOT EXISTS goal (
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
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы player_coefficient
--
DROP TABLE IF EXISTS player_coefficient;
CREATE TABLE IF NOT EXISTS player_coefficient (
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
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы result_coefficient
--
DROP TABLE IF EXISTS result_coefficient;
CREATE TABLE IF NOT EXISTS result_coefficient (
  id INT(11) NOT NULL AUTO_INCREMENT,
  game_id INT(11) NOT NULL,
  result ENUM('1','2','X','1X','X2','12') NOT NULL,
  coefficient DOUBLE NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_result_coefficient_game_id FOREIGN KEY (game_id)
    REFERENCES game(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 3
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы score_coefficient
--
DROP TABLE IF EXISTS score_coefficient;
CREATE TABLE IF NOT EXISTS score_coefficient (
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
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы single_bet
--
DROP TABLE IF EXISTS single_bet;
CREATE TABLE IF NOT EXISTS single_bet (
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
AUTO_INCREMENT = 5
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы total_goals_coefficient
--
DROP TABLE IF EXISTS total_goals_coefficient;
CREATE TABLE IF NOT EXISTS total_goals_coefficient (
  id INT(11) NOT NULL AUTO_INCREMENT,
  game_id INT(11) NOT NULL,
  goal_coefficient DOUBLE NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_total_goals_coefficient_game_id FOREIGN KEY (game_id)
    REFERENCES game(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы bet_player
--
DROP TABLE IF EXISTS bet_player;
CREATE TABLE IF NOT EXISTS bet_player (
  single_bet_id INT(11) NOT NULL,
  player_id INT(11) NOT NULL,
  CONSTRAINT FK_bet_player_player_id FOREIGN KEY (player_id)
    REFERENCES player(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_bet_player_single_bet_id FOREIGN KEY (single_bet_id)
    REFERENCES single_bet(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы bet_result
--
DROP TABLE IF EXISTS bet_result;
CREATE TABLE IF NOT EXISTS bet_result (
  single_bet_id INT(11) NOT NULL,
  result ENUM('1','2','X','1X','X2','12') NOT NULL,
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
CREATE TABLE IF NOT EXISTS bet_score (
  single_bet_id INT(11) NOT NULL,
  first_team_score INT(11) NOT NULL,
  second_team_score INT(11) NOT NULL,
  CONSTRAINT FK_bet_score_single_bet_id FOREIGN KEY (single_bet_id)
    REFERENCES single_bet(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci
ROW_FORMAT = DYNAMIC;

--
-- Описание для таблицы bet_total_goals
--
DROP TABLE IF EXISTS bet_total_goals;
CREATE TABLE IF NOT EXISTS bet_total_goals (
  single_bet_id INT(11) NOT NULL,
  goals_count INT(11) NOT NULL,
  CONSTRAINT FK_bet_total_goals_single_bet_id FOREIGN KEY (single_bet_id)
    REFERENCES single_bet(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)
ENGINE = INNODB
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
(1, 'uijo', 'bhj', 'uijo', 5, 5, 5);

-- 
-- Вывод данных для таблицы user
--
INSERT INTO user VALUES
(1, 'hjoikp', 664, 'MALE', 'njkl', 'nkml', 'jk', 5, 'jkop', 'CLIENT', 'en_EN', 0);

-- 
-- Вывод данных для таблицы game
--
INSERT INTO game VALUES
(1, 'bjkl', 'n;kl', '2016-09-12 14:23:50', 1, 1, 5, 4, 'FINISHED', 1, 55);

-- 
-- Вывод данных для таблицы player
--

-- Таблица totalizator.player не содержит данных

-- 
-- Вывод данных для таблицы team_league
--

-- Таблица totalizator.team_league не содержит данных

-- 
-- Вывод данных для таблицы total_bet
--
INSERT INTO total_bet VALUES
(1, 1, 'SINGLE', 300, '2016-08-31 23:54:24', 1000, 'WON');

-- 
-- Вывод данных для таблицы goal
--

-- Таблица totalizator.goal не содержит данных

-- 
-- Вывод данных для таблицы player_coefficient
--

-- Таблица totalizator.player_coefficient не содержит данных

-- 
-- Вывод данных для таблицы result_coefficient
--
INSERT INTO result_coefficient VALUES
(1, 1, '1', 5),
(2, 1, 'X', 5);

-- 
-- Вывод данных для таблицы score_coefficient
--

-- Таблица totalizator.score_coefficient не содержит данных

-- 
-- Вывод данных для таблицы single_bet
--
INSERT INTO single_bet VALUES
(4, 1, 1, 'RESULT', 5, 'WON');

-- 
-- Вывод данных для таблицы total_goals_coefficient
--

-- Таблица totalizator.total_goals_coefficient не содержит данных

-- 
-- Вывод данных для таблицы bet_player
--

-- Таблица totalizator.bet_player не содержит данных

-- 
-- Вывод данных для таблицы bet_result
--
INSERT INTO bet_result VALUES
(4, '1');

-- 
-- Вывод данных для таблицы bet_score
--

-- Таблица totalizator.bet_score не содержит данных

-- 
-- Вывод данных для таблицы bet_total_goals
--

-- Таблица totalizator.bet_total_goals не содержит данных

-- 
-- Восстановить предыдущий режим SQL (SQL mode)
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

-- 
-- Включение внешних ключей
-- 
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;