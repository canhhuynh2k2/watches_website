CREATE TABLE Users(
	id 			INT AUTO_INCREMENT PRIMARY KEY,
	fullname 	VARCHAR(50) NOT NULL,
	email 		VARCHAR(150) UNIQUE,
	phoneNumber VARCHAR(20) NOT NULL,
	address 	VARCHAR(200),
	password 	VARCHAR(32) NOT NULL,
	roleId 		INT, 
	createdAt 	DATETIME,
	updatedAt 	DATETIME,
	deleted 	INT,
	FOREIGN KEY (roleId) REFERENCES Role(id)
	
);