CREATE TABLE Galleries(
	id 			INT AUTO_INCREMENT PRIMARY KEY,
	productId 	INT NOT NULL, 
	thumbnail 	VARCHAR(500), 
	FOREIGN KEY (productId) REFERENCES Products(id)
);