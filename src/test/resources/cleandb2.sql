# delete from Users;
drop table Users;
CREATE TABLE IF NOT EXISTS `MotorcycleMeetup`.`Users` (`UserID` INT NOT NULL AUTO_INCREMENT,`Type` INT NULL,`Username` VARCHAR(50) NULL,`Password` VARCHAR(255) NULL,`Email` VARCHAR(100) NULL,`Phone` VARCHAR(15) NULL,`FirstName` VARCHAR(45) NULL,`LastName` VARCHAR(60) NULL,PRIMARY KEY (`UserID`));
INSERT INTO UsersTest VALUES (null, 9, "testing", null, "lucas.kostecki@gmail.com", "6085167407", "Lucas", "Kostecki");