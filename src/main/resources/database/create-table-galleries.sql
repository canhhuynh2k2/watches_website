CREATE TABLE Galleries(
	id 			BIGINT AUTO_INCREMENT PRIMARY KEY,
	productId 	BIGINT NOT NULL, 
	thumbnail 	VARCHAR(500), 
	FOREIGN KEY (productId) REFERENCES Products(id)
);