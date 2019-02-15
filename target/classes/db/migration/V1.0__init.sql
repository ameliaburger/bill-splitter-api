-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema billsplitter
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `billsplitter` ;

-- -----------------------------------------------------
-- Schema billsplitter
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `billsplitter` DEFAULT CHARACTER SET utf8 ;
USE `billsplitter` ;

-- -----------------------------------------------------
-- Table `billsplitter`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billsplitter`.`User` ;

CREATE TABLE IF NOT EXISTS `billsplitter`.`User` (
  `UserId` INT NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(100) NULL,
  `JoinDate` DATETIME NULL,
  PRIMARY KEY (`UserId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `billsplitter`.`Session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billsplitter`.`Session` ;

CREATE TABLE IF NOT EXISTS `billsplitter`.`Session` (
  `SessionId` INT NOT NULL,
  `SessionName` VARCHAR(45) NOT NULL,
  `AdminId` INT NOT NULL,
  PRIMARY KEY (`SessionId`),
  INDEX `fk_Session_User1_idx` (`AdminId` ASC),
  CONSTRAINT `fk_Session_User1`
    FOREIGN KEY (`AdminId`)
    REFERENCES `billsplitter`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `billsplitter`.`User_Session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billsplitter`.`User_Session` ;

CREATE TABLE IF NOT EXISTS `billsplitter`.`User_Session` (
  `UserId` INT NOT NULL,
  `SessionId` INT NOT NULL,
  PRIMARY KEY (`UserId`, `SessionId`),
  INDEX `fk_User_Session_User_idx` (`UserId` ASC),
  INDEX `fk_User_Session_Session1_idx` (`SessionId` ASC),
  CONSTRAINT `fk_User_Session_User`
    FOREIGN KEY (`UserId`)
    REFERENCES `billsplitter`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Session_Session1`
    FOREIGN KEY (`SessionId`)
    REFERENCES `billsplitter`.`Session` (`SessionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `billsplitter`.`Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billsplitter`.`Transaction` ;

CREATE TABLE IF NOT EXISTS `billsplitter`.`Transaction` (
  `TransactionId` INT NOT NULL,
  `UserPaidId` INT NOT NULL,
  `SessionId` INT NOT NULL,
  `Description` VARCHAR(45) NOT NULL,
  `Time` DATETIME NOT NULL,
  `Amount` DECIMAL(10,2) NOT NULL,
  `Currency` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`TransactionId`),
  INDEX `fk_Transaction_User1_idx` (`UserPaidId` ASC),
  INDEX `fk_Transaction_Session1_idx` (`SessionId` ASC),
  CONSTRAINT `fk_Transaction_User1`
    FOREIGN KEY (`UserPaidId`)
    REFERENCES `billsplitter`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_Session1`
    FOREIGN KEY (`SessionId`)
    REFERENCES `billsplitter`.`Session` (`SessionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `billsplitter`.`User_Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `billsplitter`.`User_Transaction` ;

CREATE TABLE IF NOT EXISTS `billsplitter`.`User_Transaction` (
  `UserId` INT NOT NULL,
  `TransactionId` INT NOT NULL,
  PRIMARY KEY (`UserId`, `TransactionId`),
  INDEX `fk_User_Transaction_Transaction1_idx` (`TransactionId` ASC),
  CONSTRAINT `fk_User_Transaction_User1`
    FOREIGN KEY (`UserId`)
    REFERENCES `billsplitter`.`User` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Transaction_Transaction1`
    FOREIGN KEY (`TransactionId`)
    REFERENCES `billsplitter`.`Transaction` (`TransactionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
