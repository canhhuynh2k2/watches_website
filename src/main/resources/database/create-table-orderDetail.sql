CREATE TABLE OrderDetail(
	id 			BIGINT AUTO_INCREMENT PRIMARY KEY,
	orderId 	BIGINT NOT NULL,
	productId 	BIGINT NOT NULL,
	price 		BIGINT NOT NULL, 
	quantity 	INT NOT NULL,
	FOREIGN KEY (productId) REFERENCES Products(id),
	FOREIGN KEY (orderId) REFERENCES Orders(id)
);