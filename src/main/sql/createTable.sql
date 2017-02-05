CREATE  TABLE IF NOT EXISTS `contactbook`.`user` (
  `userId` INT NOT NULL AUTO_INCREMENT ,
  `lastName` VARCHAR(45) NULL ,
  `firstName` VARCHAR(45) NULL ,
  `age` INT NULL,
  `gender` VARCHAR(1) NULL,
  `phonenumber` VARCHAR(20) NULL,
  PRIMARY KEY (`userId`) )