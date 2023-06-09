CREATE TABLE Products(
	id 						BIGINT AUTO_INCREMENT PRIMARY KEY,
	code					VARCHAR(30) NOT NULL UNIQUE,
	categoryId 				BIGINT NOT NULL, 
	title 					VARCHAR(350) NOT NULL, 
	price 					BIGINT NOT NULL, 
	discount 				BIGINT NOT NULL, 
	quantity				INT NOT NULL,
	thumbnail 				VARCHAR(500) NOT NULL,
	description 			LONGTEXT, 
	createdAt 				TIMESTAMP,
	updatedAt 				TIMESTAMP,
	status  				INT NOT NULL,
	origin 					VARCHAR(50) NOT NULL,
	collection 				VARCHAR(100),
	gender					INT NOT NULL,
	size					VARCHAR(50) NOT NULL,
	style					VARCHAR(100),
	machineType 			VARCHAR(100) NOT NULL,
	dial 					VARCHAR(50),
	glassMaterial 			VARCHAR(100) NOT NULL,
	caseMaterial 			VARCHAR(100),
	strapMaterial 			VARCHAR(100),
	shape 					VARCHAR(50),
	thickness 				VARCHAR(50),
	waterResistance 		VARCHAR(50) NOT NULL,
	benzel 					VARCHAR(50),
	energyStorage 			VARCHAR(50),
	weight 					VARCHAR(20),
	feature 				VARCHAR(100),
	domesticWarranty		VARCHAR(50) NOT NULL,
	internationalWarranty 	VARCHAR(50),
	FOREIGN KEY (categoryId) REFERENCES Categories(id)
);