CREATE TABLE OrderDetails(
	id 			INT AUTO_INCREMENT PRIMARY KEY,
	orderId 	INT,
	productId 	INT,
	price 		BIGINT, 
	num 		INT,
	FOREIGN KEY (productId) REFERENCES Products(id),
	FOREIGN KEY (orderId) REFERENCES Orders(id)
);