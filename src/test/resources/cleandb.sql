SET FOREIGN_KEY_CHECKS  = 0;
TRUNCATE Users;
TRUNCATE Routes;
TRUNCATE Waypoints;
TRUNCATE Invites;
# TRUNCATE UserRole;
INSERT INTO Users VALUES(null, 9, 'testingye', null,'lucas.kostecki@gmail.com','6085167407','Lucas','Kostecki');
INSERT INTO Routes VALUES(null, 1, '2019-05-25', '15:00:00', 'middleton, wi', 'madison, wi', 'Route Title', 'Some description', 0, '53562', now(), 0);
INSERT INTO Waypoints VALUES(null, 1, 'Waypoint name');
INSERT INTO Waypoints VALUES(null, 1, 'Waypoint name 2');
INSERT INTO Invites VALUES(null, 1, 'test@test.test', 1);
# INSERT INTO UserRole VALUES('testingye', null, 'user');
# SET FOREIGN_KEY_CHECKS  = 1;