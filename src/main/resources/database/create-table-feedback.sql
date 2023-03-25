CREATE TABLE FeedBack(
	id 			INT AUTO_INCREMENT PRIMARY KEY,
	firstname 	VARCHAR(30), 
	lastname 	VARCHAR(30),
	email 		VARCHAR(150) NOT NULL,
	phoneNumber VARCHAR(20),
	subjectName VARCHAR(200), 
	note 		VARCHAR(500)
);