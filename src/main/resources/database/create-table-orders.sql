CREATE TABLE Orders(
	id 			INT AUTO_INCREMENT PRIMARY KEY,
	userId 		INT, 
	fullname 	VARCHAR(50),
	email 		VARCHAR(150) NOT NULL,
	phoneNumber VARCHAR(20) NOT NULL,
	address 	VARCHAR(200),
	note 		VARCHAR(500),
	orderDate 	DATETIME,
	status 		INT,
	FOREIGN KEY (userId) REFERENCES Users(id)
);