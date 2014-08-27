delimiter $$

CREATE TABLE `examplebooks` (
  `bookID` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `publisher` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `categoryname` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`bookID`),
  UNIQUE KEY `bookname_UNIQUE` (`bookname`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci$$

/*
-- Query: select * from examplebooks
LIMIT 0, 1000

-- Date: 2012-06-05 15:19
*/
INSERT INTO `examplebooks` (`bookID`,`bookname`,`publisher`,`categoryname`) VALUES (1,'deneme4','deneme','deneme');
INSERT INTO `examplebooks` (`bookID`,`bookname`,`publisher`,`categoryname`) VALUES (2,'deneme3','deneme','deneme');
INSERT INTO `examplebooks` (`bookID`,`bookname`,`publisher`,`categoryname`) VALUES (3,'deneme2','deneme','deneme');
INSERT INTO `examplebooks` (`bookID`,`bookname`,`publisher`,`categoryname`) VALUES (4,'deneme1','deneme','deneme');