CREATE TABLE Products(
	id 			BIGINT AUTO_INCREMENT PRIMARY KEY,
	code		VARCHAR(30) NOT NULL UNIQUE,
	categoryId 	BIGINT NOT NULL, 
	title 		VARCHAR(350), 
	price 		BIGINT NOT NULL, 
	discount 	BIGINT NOT NULL, 
	quantity	INT,
	thumbnail 	VARCHAR(500) NOT NULL,
	description LONGTEXT, 
	createdAt 	TIMESTAMP,
	updatedAt 	TIMESTAMP,
	FOREIGN KEY (categoryId) REFERENCES Categories(id)
);