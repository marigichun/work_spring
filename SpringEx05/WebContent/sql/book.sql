CREATE TABLE book(
	isbn		INT	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	title		VARCHAR(30) NOT NULL,
	author		VARCHAR(10) NOT NULL,
	publisher	VARCHAR(30) NOT NULL,
	price		INT NOT NULL,
	description	VARCHAR(255) NULL 
);

INSERT INTO book (title, author, publisher, price, description)
	VALUES ('책제목-1', '작가-1', '출판사-1', 30000, '설명-1');
	
INSERT INTO book (title, author, publisher, price, description)
	VALUES ('책제목-2', '작가-2', '출판사-2', 30000, '설명-2');
	
INSERT INTO book (title, author, publisher, price, description)
	VALUES ('책제목-3', '작가-3', '출판사-3', 30000, '설명-3');

