-- CREATE DATABASE IF NOT EXISTS `thymeleaf-spring-boot-app` 
-- USE `thymeleaf-spring-boot-app`;

CREATE TABLE IF NOT EXISTS USER (
	  ID         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
	  USERNAME   VARCHAR(255) NOT NULL,
	  PASSWORD   VARCHAR(255) NOT NULL,
	  EMAIL      VARCHAR(255),
	  FIRST_NAME VARCHAR(255),
	  LAST_NAME  VARCHAR(255),
	  ROLE		 VARCHAR(255),	
	  UNIQUE INDEX `USERNAME_UNIQUE` (`username`)
	);
	
