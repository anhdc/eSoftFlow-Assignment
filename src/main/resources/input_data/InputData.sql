CREATE DATABASE  IF NOT EXISTS `rest_demo` 
USE `rest_demo`;


DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(145) NOT NULL,
  `email` varchar(145) NOT NULL,
  `address` varchar(500) NOT NULL,
  `insertion_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,  
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


LOCK TABLES `users` WRITE;

INSERT INTO `users` VALUES (1,'anhdc','anhdc9288@gmail.com','Le Van Luong',LOCALTIMESTAMP()),(2,'other_user','other@xxx.com','other address',LOCALTIMESTAMP());

UNLOCK TABLES;
