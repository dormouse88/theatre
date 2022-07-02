DROP DATABASE IF EXISTS Banking;
CREATE DATABASE Banking;
USE Banking;

CREATE TABLE Branch (
	ID INT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	city VARCHAR(50) NOT NULL,
    UNIQUE(name)
);

INSERT INTO Branch (ID, name, city) VALUES (1, 'Bradford','Bradford');
INSERT INTO Branch (ID, name, city) VALUES (2, 'Leeds','Leeds');
INSERT INTO Branch (ID, name, city) VALUES (3, 'Sheffield Central','Sheffield');
INSERT INTO Branch (ID, name, city) VALUES (4, 'Sheffield East','Sheffield');

CREATE TABLE Employee (
	ID INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	manager INT,
	FOREIGN KEY (manager) REFERENCES Employee(ID)
);

INSERT INTO Employee (first_name, last_name) VALUES ('Alice', 'Smith');
INSERT INTO Employee (first_name, last_name, manager) VALUES ('Bill', 'Jones', 1);
INSERT INTO Employee (first_name, last_name, manager) VALUES ('Chris', 'Brown', 1);
INSERT INTO Employee (first_name, last_name, manager) VALUES ('Dina', 'Taylor', 1);
INSERT INTO Employee (first_name, last_name, manager) VALUES ('Edward', 'Roberts', 1);

CREATE TABLE Customer (
	ID INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
    street VARCHAR(50),
    city VARCHAR(50),
	banker INT,
	FOREIGN KEY (banker) REFERENCES Employee(ID)
);

INSERT INTO Customer (first_name, last_name, street, city, banker) VALUES ('Greg', 'Wilson', 'Wood Lane', 'Bradford', 2);
INSERT INTO Customer (first_name, last_name, street, city, banker) VALUES ('Harriet', 'Davies', 'Broad Lane', 'Sheffield', 4);
INSERT INTO Customer (first_name, last_name, street, city, banker) VALUES ('Ian', 'Evans', 'Cross Street', 'Leeds', 3);
INSERT INTO Customer (first_name, last_name, street, city, banker) VALUES ('Jane', 'Johnson', 'Pool Way', 'Bradford', 5);
INSERT INTO Customer (first_name, last_name, street, city, banker) VALUES ('Keith', 'Scott', 'Farmer Road', 'Bradford', 2);

CREATE TABLE Account (
	ID INT PRIMARY KEY AUTO_INCREMENT,
	account_type ENUM("current", "savings") NOT NULL,
	balance INT NOT NULL
);

INSERT INTO Account (account_type, balance) VALUES ("current", 0);
INSERT INTO Account (account_type, balance) VALUES ("current", 1000);
INSERT INTO Account (account_type, balance) VALUES ("savings", 10000);
INSERT INTO Account (account_type, balance) VALUES ("current", 1000);
INSERT INTO Account (account_type, balance) VALUES ("savings", 2000);

CREATE TABLE CustomerAccount (
	customerID INT,
    accountID INT,
    PRIMARY KEY (customerID, accountID),
    FOREIGN KEY (customerID) REFERENCES Customer(ID),
    FOREIGN KEY (AccountID) REFERENCES Account(ID)
);

INSERT INTO CustomerAccount (customerID, accountID) VALUES (1,1);
INSERT INTO CustomerAccount (customerID, accountID) VALUES (2,2);
INSERT INTO CustomerAccount (customerID, accountID) VALUES (3,3);
INSERT INTO CustomerAccount (customerID, accountID) VALUES (3,4);
INSERT INTO CustomerAccount (customerID, accountID) VALUES (4,5);
INSERT INTO CustomerAccount (customerID, accountID) VALUES (5,5);

