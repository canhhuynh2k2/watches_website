CREATE TABLE Orders(
	id 			BIGINT AUTO_INCREMENT PRIMARY KEY,
	userId 		BIGINT, 
	fullname 	VARCHAR(50),
	email 		VARCHAR(150) NOT NULL,
	phoneNumber VARCHAR(20) NOT NULL,
	address 	VARCHAR(200),
	note 		VARCHAR(500),
	orderDate 	TIMESTAMP,
	status 		INT,
	FOREIGN KEY (userId) REFERENCES Users(id)
);