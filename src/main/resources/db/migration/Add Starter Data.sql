SELECT * FROM billsplitter.User;

INSERT INTO billsplitter.User (FirstName, LastName, Email, JoinDate)
VALUES ('Amelia', 'Burger', 'ameliaburger2@gmail.com', CURDATE());

INSERT INTO billsplitter.User (FirstName, LastName, Email, JoinDate)
VALUES ('Jane', 'Doe', 'jane.doe@gmail.com', CURDATE());

INSERT INTO billsplitter.User (FirstName, LastName, Email, JoinDate)
VALUES ('Calvin', 'Harris', 'charris@gmail.com', CURDATE());

INSERT INTO billsplitter.Session (SessionName)
VALUES ('First Session');

SELECT * FROM billsplitter.Session;

INSERT INTO billsplitter.User_Session (UserId, SessionId)
VALUES (1, 1);

INSERT INTO billsplitter.User_Session (UserId, SessionId)
VALUES (2, 1);

INSERT INTO billsplitter.User_Session (UserId, SessionId)
VALUES (3, 1);