CREATE TABLE Contact(
	id 			BIGINT AUTO_INCREMENT PRIMARY KEY,
	firstname 	VARCHAR(30), 
	lastname 	VARCHAR(30),
	email 		VARCHAR(150) NOT NULL,
	phoneNumber VARCHAR(20),
	subjectName VARCHAR(200) NOT NULL, 
	content		VARCHAR(500) NOT NULL,
	contactAt 	TIMESTAMP
);