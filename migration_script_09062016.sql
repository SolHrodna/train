-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: train
-- Source Schemata: trainnew
-- Created: Thu Jun 09 20:20:59 2016
-- Workbench Version: 6.3.4
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema train
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `train` ;
CREATE SCHEMA IF NOT EXISTS `train` ;

-- ----------------------------------------------------------------------------
-- Table train.hibernate_sequence
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `train`.`hibernate_sequence` (
  `next_val` BIGINT(20) NULL DEFAULT NULL COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table train.history
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `train`.`history` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `date` DATE NOT NULL COMMENT '',
  `startroute` TIME NOT NULL COMMENT '',
  `finishroute` TIME NOT NULL COMMENT '',
  `places` INT(10) UNSIGNED NOT NULL COMMENT '',
  `price` DECIMAL(10,0) UNSIGNED NOT NULL COMMENT '',
  `number` VARCHAR(45) NOT NULL COMMENT '',
  `model` VARCHAR(45) NOT NULL COMMENT '',
  `numplaces` INT(11) NOT NULL COMMENT '',
  `startstation` VARCHAR(45) NOT NULL COMMENT '',
  `finishstation` VARCHAR(45) NOT NULL COMMENT '',
  `scheduleId` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table train.ordertrain
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `train`.`ordertrain` (
  `orderId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `route_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `status` VARCHAR(45) NOT NULL COMMENT '',
  `userId` INT(11) NULL DEFAULT NULL COMMENT '',
  `routeId` INT(11) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`orderId`)  COMMENT '',
  INDEX `fk_userId_idx` (`userId` ASC)  COMMENT '',
  INDEX `FKf70bk3gmf4p2gtl6cs7nqhpdn` (`routeId` ASC)  COMMENT '',
  CONSTRAINT `FK31ugkdyu47dtdj0s14998j3o7`
    FOREIGN KEY (`userId`)
    REFERENCES `train`.`usertrain` (`userId`),
  CONSTRAINT `FK_4v81mxe8gc9hvw0ej7kktl8sx`
    FOREIGN KEY (`routeId`)
    REFERENCES `train`.`routers` (`routeId`),
  CONSTRAINT `FK_fqb13uo0kq0o5sbivg1vlp1yt`
    FOREIGN KEY (`userId`)
    REFERENCES `train`.`usertrain` (`userId`),
  CONSTRAINT `FKf70bk3gmf4p2gtl6cs7nqhpdn`
    FOREIGN KEY (`routeId`)
    REFERENCES `train`.`routers` (`routeId`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table train.usertrain
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `train`.`usertrain` (
  `userId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `login` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  `firstname` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `secondname` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `role` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`userId`)  COMMENT '',
  UNIQUE INDEX `login_UNIQUE` (`login` ASC)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table train.routers
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `train`.`routers` (
  `routeId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `trainid` INT(11) NULL DEFAULT NULL COMMENT '',
  `queue` VARCHAR(100) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`routeId`)  COMMENT '',
  INDEX `FKt28t01m8j3eo2km9fum1vqo1q` (`trainid` ASC)  COMMENT '',
  CONSTRAINT `FK_t2c2awag42ypt2aryasjw3uvs`
    FOREIGN KEY (`trainid`)
    REFERENCES `train`.`train` (`trainId`),
  CONSTRAINT `FKt28t01m8j3eo2km9fum1vqo1q`
    FOREIGN KEY (`trainid`)
    REFERENCES `train`.`train` (`trainId`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table train.train
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `train`.`train` (
  `trainId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `number` VARCHAR(45) NOT NULL COMMENT '',
  `model` VARCHAR(45) NOT NULL COMMENT '',
  `numplaces` INT(11) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`trainId`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 60
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table train.schedule
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `train`.`schedule` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `day` VARCHAR(11) NOT NULL COMMENT '',
  `startroute` TIME NOT NULL COMMENT '',
  `finishroute` TIME NOT NULL COMMENT '',
  `status` VARCHAR(45) NOT NULL COMMENT '',
  `price` DECIMAL(10,0) NOT NULL COMMENT '',
  `placesuse` INT(10) UNSIGNED NOT NULL COMMENT '',
  `routeId` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table train.stations
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `train`.`stations` (
  `stationId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `station` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`stationId`)  COMMENT '',
  UNIQUE INDEX `station_UNIQUE` (`station` ASC)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table train.stations_routers
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `train`.`stations_routers` (
  `route_id` INT(11) NOT NULL COMMENT '',
  `station_id` INT(11) NOT NULL COMMENT '',
  `STATIONS_stationId` INT(11) NOT NULL DEFAULT '1' COMMENT '',
  PRIMARY KEY (`route_id`, `station_id`, `STATIONS_stationId`)  COMMENT '',
  INDEX `FK8unktfgn7p502w2wsskkh26cb` (`station_id` ASC)  COMMENT '',
  CONSTRAINT `FK3r09mqbuh5rtvab7eos2w2d50`
    FOREIGN KEY (`route_id`)
    REFERENCES `train`.`routers` (`routeId`),
  CONSTRAINT `FK8unktfgn7p502w2wsskkh26cb`
    FOREIGN KEY (`station_id`)
    REFERENCES `train`.`stations` (`stationId`),
  CONSTRAINT `FK_6ypure41639087o6yf0mrbjf6`
    FOREIGN KEY (`route_id`)
    REFERENCES `train`.`routers` (`routeId`),
  CONSTRAINT `FK_rttx5515euud235hfwo039tmr`
    FOREIGN KEY (`station_id`)
    REFERENCES `train`.`stations` (`stationId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
SET FOREIGN_KEY_CHECKS = 1;
