DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
  USER_ID INT AUTO_INCREMENT  PRIMARY KEY,
  FIRST_NAME VARCHAR(100) NOT NULL,
  LAST_NAME VARCHAR(100) NOT NULL,
  AGE INT DEFAULT NULL
);

INSERT INTO USER(FIRST_NAME, LAST_NAME, AGE) VALUES
('Namjoon', 'Kim', 10),
('Seokjin', 'Kim', 15),
('Yoongi', 'Min', 13),
('Hoseok', 'Jung', 12),
('Jimin', 'Park', 11),
('Taehyung', 'Kim', 11),
('Jungkook', 'Jeon', 10);