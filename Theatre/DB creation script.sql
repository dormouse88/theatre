-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Theatre
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Theatre
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS Theatre;
CREATE SCHEMA IF NOT EXISTS `Theatre` DEFAULT CHARACTER SET utf8 ;
USE `Theatre` ;

-- -----------------------------------------------------
-- Table `Theatre`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Theatre`.`Customer` (
  `CustomerID` INT NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(45) NULL,
  `lname` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `Address` VARCHAR(45) NULL,
  `Username` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NULL,
  PRIMARY KEY (`CustomerID`),
  UNIQUE INDEX `CustomerID_UNIQUE` (`CustomerID` ASC) VISIBLE,
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Theatre`.`Payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Theatre`.`Payment` (
  `PaymentID` INT NOT NULL AUTO_INCREMENT,
  `CardDetails` VARCHAR(16) NULL,
  `PaymentDate` DATE NULL,
  `PaymentAmount` INT NULL,
  PRIMARY KEY (`PaymentID`),
  UNIQUE INDEX `PaymentID_UNIQUE` (`PaymentID` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Theatre`.`ShowType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Theatre`.`ShowType` (
  `ShowTypeID` INT NOT NULL AUTO_INCREMENT,
  `Genre` VARCHAR(45) NULL,
  PRIMARY KEY (`ShowTypeID`),
  UNIQUE INDEX `ShowTypeID_UNIQUE` (`ShowTypeID` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Theatre`.`Showing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Theatre`.`Showing` (
  `ShowingID` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(70) NULL,
  `Duration` INT NULL,
  `Lang` VARCHAR(45) NULL,
  `Info` VARCHAR(300) NULL,
  `ShowTypeID` INT NOT NULL,
  PRIMARY KEY (`ShowingID`),
  INDEX `fk_Show_ShowType1_idx` (`ShowTypeID` ASC) VISIBLE,
  UNIQUE INDEX `ShowID_UNIQUE` (`ShowingID` ASC) VISIBLE,
  CONSTRAINT `fk_Show_ShowType1`
    FOREIGN KEY (`ShowTypeID`)
    REFERENCES `Theatre`.`ShowType` (`ShowTypeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Theatre`.`Performance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Theatre`.`Performance` (
  `PerformanceID` INT NOT NULL AUTO_INCREMENT,
  `pdate` DATE NULL,
  `ptime` VARCHAR(45) NULL,
  `ShowingID` INT NOT NULL,
  PRIMARY KEY (`PerformanceID`),
  INDEX `fk_Performance_Show1_idx` (`ShowingID` ASC) VISIBLE,
  UNIQUE INDEX `PerformanceID_UNIQUE` (`PerformanceID` ASC) VISIBLE,
  CONSTRAINT `fk_Performance_Show1`
    FOREIGN KEY (`ShowingID`)
    REFERENCES `Theatre`.`Showing` (`ShowingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Theatre`.`Seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Theatre`.`Seat` (
  `SeatID` INT NOT NULL AUTO_INCREMENT,
  `SeatZone` VARCHAR(6) NULL,
  `Price` INT NULL,
  `NumberOfSeats` INT NULL,
  `PerformanceID` INT NOT NULL,
  PRIMARY KEY (`SeatID`),
  INDEX `fk_Seat_Performance1_idx` (`PerformanceID` ASC) VISIBLE,
  CONSTRAINT `fk_Seat_Performance1`
    FOREIGN KEY (`PerformanceID`)
    REFERENCES `Theatre`.`Performance` (`PerformanceID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Theatre`.`Booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Theatre`.`Booking` (
  `BookingID` INT NOT NULL AUTO_INCREMENT,
  `NumberOfAdults` INT NULL,
  `NumberOfChildren` INT NULL,
  `TotalCost` INT NULL,
  `CustomerID` INT NOT NULL,
  `PaymentID` INT NULL,
  `SeatID` INT NOT NULL,
  PRIMARY KEY (`BookingID`),
  INDEX `fk_Ticket_Customer1_idx` (`CustomerID` ASC) VISIBLE,
  INDEX `fk_Ticket_Payment1_idx` (`PaymentID` ASC) VISIBLE,
  UNIQUE INDEX `TicketID_UNIQUE` (`BookingID` ASC) VISIBLE,
  INDEX `fk_Booking_Seat1_idx` (`SeatID` ASC) VISIBLE,
  CONSTRAINT `fk_Ticket_Customer1`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `Theatre`.`Customer` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_Payment1`
    FOREIGN KEY (`PaymentID`)
    REFERENCES `Theatre`.`Payment` (`PaymentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Booking_Seat1`
    FOREIGN KEY (`SeatID`)
    REFERENCES `Theatre`.`Seat` (`SeatID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Theatre`.`Performer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Theatre`.`Performer` (
  `PerformerID` INT NOT NULL AUTO_INCREMENT,
  `pname` VARCHAR(70) NULL,
  PRIMARY KEY (`PerformerID`),
  UNIQUE INDEX `PerformerID_UNIQUE` (`PerformerID` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Theatre`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Theatre`.`Employee` (
  `EmployeeID` INT NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(45) NULL,
  `lname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  PRIMARY KEY (`EmployeeID`),
  UNIQUE INDEX `EmployeeID_UNIQUE` (`EmployeeID` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Theatre`.`ShowingPerformer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Theatre`.`ShowingPerformer` (
  `ShowingID` INT NOT NULL,
  `PerformerID` INT NOT NULL,
  INDEX `fk_ShowingPerformer_Showing2_idx` (`ShowingID` ASC) VISIBLE,
  INDEX `fk_ShowingPerformer_Performer1_idx` (`PerformerID` ASC) VISIBLE,
  PRIMARY KEY (`ShowingID`, `PerformerID`),
  CONSTRAINT `fk_ShowingPerformer_Showing2`
    FOREIGN KEY (`ShowingID`)
    REFERENCES `Theatre`.`Showing` (`ShowingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ShowingPerformer_Performer1`
    FOREIGN KEY (`PerformerID`)
    REFERENCES `Theatre`.`Performer` (`PerformerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- test inserts into all tables
INSERT INTO Employee (fname, lname, email, address) VALUES ("John", "Doe", "JDoe@TR.com", "32 Oliver Dr");
INSERT INTO Payment (CardDetails, PaymentDate, PaymentAmount) VALUES ("4548493283237532", curdate(), 4000);
INSERT INTO Customer (fname, lname, email, address, username, password) VALUES ("Jane", "Doe", "JDoes@email.com", "33 Oliver Dr", "JaDo", "password"); 
INSERT INTO ShowType (Genre) VALUES ("Theatre"), ("Musical"), ("Opera"), ("Concert");
INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) VALUES ("Mamma Mia", 195, "English", "Mamma Mia! is a jukebox musical written by British playwright Catherine Johnson, based on the songs of ABBA composed by Benny Andersson and Björn Ulvaeus, members of the band. The title of the musical is taken from the group's 1975 chart-topper 'Mamma Mia'.",2);
INSERT INTO Performer (pname) VALUES ("MammaMiaGroup");
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (1,1); 
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES ("2022-07-13", "Matinee", 1);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES ("Circle", 80, 4000, 1), ("Stalls", 120, 4000, 1); 
INSERT INTO Booking (NumberOfAdults, NumberOfChildren, TotalCost, CustomerID, PaymentID, SeatID) VALUES (1,0,4000,1,1,1);
