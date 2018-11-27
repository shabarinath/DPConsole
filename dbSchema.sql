-- MySQL dump 10.13  Distrib 5.6.26, for Win64 (x86_64)
--
-- Host: localhost    Database: dpconsole
-- ------------------------------------------------------
-- Server version       5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS dpconsole;

USE dpconsole;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT '0',  
  `authority` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FKBC16F46AB5DA4D87` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(31) NOT NULL,
  `version` bigint(20) DEFAULT '0',
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `account_locked` tinyint(1) NOT NULL DEFAULT '0',
  `account_expired` tinyint(1) NOT NULL DEFAULT '0',
  `credentials_expired` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `catalogue_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogue_categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `version` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `precedence` int(100) NOT NULL,  
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `catalogue_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogue_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `version` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `description` text,
  `sub_category_id` bigint(20) NOT NULL,  
  `food_type` varchar(255) NOT NULL,
  `precedence` int(100) NOT NULL,  
   item_id bigint(20) NOT NULL,
   active tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `catalogue_departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogue_departments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `version` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `category_id` bigint(20) NOT NULL,    
  `precedence` int(100) NOT NULL,    
   active tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `kitchens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kitchens` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `version` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `mailbox_username` varchar(255) NOT NULL,    
  `mailbox_password` varchar(255) NOT NULL, 
  `kitchen_id` bigint(20) NOT NULL,      
   active tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `kitchen_delivery_partners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kitchen_delivery_partners` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `version` bigint(20) NOT NULL DEFAULT '0',
  `delivery_partner` varchar(255) NOT NULL,
  `email_ids` varchar(255) NOT NULL,
  `commission_percentage` DOUBLE(10,4) NOT NULL,  
   active tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `kitchen_discounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kitchen_discounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `version` bigint(20) NOT NULL DEFAULT '0',
  `kitchen_id` bigint(20) NOT NULL,   
  `delivery_partner` varchar(255) NOT NULL,
  `start_time` timestamp, 
  `end_time` timestamp, 
  `discount` DOUBLE(10,4) NOT NULL, 
   active tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `kitchen_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kitchen_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `version` bigint(20) NOT NULL DEFAULT '0',
   item_id bigint(20) NOT NULL,
  `kitchen_id` bigint(20) NOT NULL,   
  `price` DOUBLE(10,4) NOT NULL,   
   active tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `version` bigint(20) NOT NULL DEFAULT '0',
   item_id bigint(20) NOT NULL,
   `kitchen_id` bigint(20) NOT NULL,   
   `delivery_partner` varchar(255) NOT NULL,
   dp_order_id varchar(255) NOT NULL,
   `parsed_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, 
   `ordered_time` timestamp, 
   `status` varchar(255) NOT NULL,
   `total_cost` DOUBLE(10,4) NOT NULL,  
   `notes` DOUBLE(10,4) ,
    manual_review tinyint(1) NOT NULL DEFAULT '0',
	`payment_type` varchar(255) NOT NULL,  
	manual_review_comments text DEFAULT NULL,
    active tinyint(1) NOT NULL DEFAULT '1',  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `version` bigint(20) NOT NULL DEFAULT '0',
   item_id bigint(20) NOT NULL,
  `quantity` bigint(20) NOT NULL,   
  `unit_price` DOUBLE(10,4) NOT NULL,   
   active tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;



--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin',0,'admin','cfed2815f33f81ed7c13f8fc0ce28714',1,0,0,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-20 23:44:16