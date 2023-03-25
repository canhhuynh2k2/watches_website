CREATE TABLE Products(
	id 			INT AUTO_INCREMENT PRIMARY KEY,
	categoryId 	INT, 
	title 		VARCHAR(350), 
	price 		BIGINT, 
	discount 	BIGINT, 
	thumbnail 	VARCHAR(500) NOT NULL,
	description LONGTEXT, 
	createdAt 	DATETIME,
	updatedAt 	DATETIME,
	deleted 	INT,
	FOREIGN KEY (categoryId) REFERENCES Categories(id)
);