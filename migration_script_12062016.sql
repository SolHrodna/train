-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: trainnew
-- Source Schemata: train
-- Created: Sun Jun 12 19:22:41 2016
-- Workbench Version: 6.3.6
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema trainnew
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `trainnew` ;
CREATE SCHEMA IF NOT EXISTS `trainnew` ;

-- ----------------------------------------------------------------------------
-- Table trainnew.hibernate_sequence
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `trainnew`.`hibernate_sequence` (
  `next_val` BIGINT(20) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table trainnew.history
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `trainnew`.`history` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `startroute` TIME NOT NULL,
  `finishroute` TIME NOT NULL,
  `places` INT(10) UNSIGNED NOT NULL,
  `price` DECIMAL(10,0) UNSIGNED NOT NULL,
  `number` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `numplaces` INT(11) NOT NULL,
  `scheduleId` INT(11) NOT NULL,
  `shortname` VARCHAR(90) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 50
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table trainnew.ordertrain
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `trainnew`.`ordertrain` (
  `orderId` INT(11) NOT NULL AUTO_INCREMENT,
  `route_id` INT(11) NULL DEFAULT NULL,
  `status` VARCHAR(45) NOT NULL,
  `userId` INT(11) NULL DEFAULT NULL,
  `routeId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  INDEX `fk_userId_idx` (`userId` ASC),
  INDEX `FKf70bk3gmf4p2gtl6cs7nqhpdn` (`routeId` ASC),
  CONSTRAINT `FK31ugkdyu47dtdj0s14998j3o7`
    FOREIGN KEY (`userId`)
    REFERENCES `trainnew`.`usertrain` (`userId`),
  CONSTRAINT `FK_4v81mxe8gc9hvw0ej7kktl8sx`
    FOREIGN KEY (`routeId`)
    REFERENCES `trainnew`.`routers` (`routeId`),
  CONSTRAINT `FK_fqb13uo0kq0o5sbivg1vlp1yt`
    FOREIGN KEY (`userId`)
    REFERENCES `trainnew`.`usertrain` (`userId`),
  CONSTRAINT `FKf70bk3gmf4p2gtl6cs7nqhpdn`
    FOREIGN KEY (`routeId`)
    REFERENCES `trainnew`.`routers` (`routeId`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table trainnew.routers
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `trainnew`.`routers` (
  `routeId` INT(11) NOT NULL AUTO_INCREMENT,
  `trainid` INT(11) NULL DEFAULT NULL,
  `queue` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`routeId`),
  INDEX `FKt28t01m8j3eo2km9fum1vqo1q` (`trainid` ASC),
  CONSTRAINT `FK_t2c2awag42ypt2aryasjw3uvs`
    FOREIGN KEY (`trainid`)
    REFERENCES `trainnew`.`train` (`trainId`),
  CONSTRAINT `FKt28t01m8j3eo2km9fum1vqo1q`
    FOREIGN KEY (`trainid`)
    REFERENCES `trainnew`.`train` (`trainId`))
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table trainnew.schedule
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `trainnew`.`schedule` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `day` VARCHAR(11) NOT NULL,
  `startroute` TIME NOT NULL,
  `finishroute` TIME NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `price` DECIMAL(10,0) NOT NULL,
  `placesuse` INT(10) UNSIGNED NOT NULL,
  `routeId` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table trainnew.stations
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `trainnew`.`stations` (
  `stationId` INT(11) NOT NULL AUTO_INCREMENT,
  `station` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`stationId`),
  UNIQUE INDEX `station_UNIQUE` (`station` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table trainnew.stations_routers
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `trainnew`.`stations_routers` (
  `route_id` INT(11) NOT NULL,
  `station_id` INT(11) NOT NULL,
  `STATIONS_stationId` INT(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`route_id`, `station_id`, `STATIONS_stationId`),
  INDEX `FK8unktfgn7p502w2wsskkh26cb` (`station_id` ASC),
  CONSTRAINT `FK3r09mqbuh5rtvab7eos2w2d50`
    FOREIGN KEY (`route_id`)
    REFERENCES `trainnew`.`routers` (`routeId`),
  CONSTRAINT `FK8unktfgn7p502w2wsskkh26cb`
    FOREIGN KEY (`station_id`)
    REFERENCES `trainnew`.`stations` (`stationId`),
  CONSTRAINT `FK_6ypure41639087o6yf0mrbjf6`
    FOREIGN KEY (`route_id`)
    REFERENCES `trainnew`.`routers` (`routeId`),
  CONSTRAINT `FK_rttx5515euud235hfwo039tmr`
    FOREIGN KEY (`station_id`)
    REFERENCES `trainnew`.`stations` (`stationId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table trainnew.temp
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `trainnew`.`temp` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `scheduleId` INT(11) NOT NULL,
  `date` DATE NOT NULL,
  `places` INT(11) NOT NULL,
  `userId` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table trainnew.train
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `trainnew`.`train` (
  `trainId` INT(11) NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `numplaces` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`trainId`))
ENGINE = InnoDB
AUTO_INCREMENT = 61
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table trainnew.usertrain
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `trainnew`.`usertrain` (
  `userId` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `secondname` VARCHAR(45) NULL DEFAULT NULL,
  `role` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8;
SET FOREIGN_KEY_CHECKS = 1;
