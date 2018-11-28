-- MySQL dump 10.13  Distrib 5.6.26, for Win64 (x86_64)
--
-- Host: localhost    Database: dpconsole
-- ------------------------------------------------------
-- Server version	5.6.26-log

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

--
-- Table structure for table `catalogue_categories`
--

DROP TABLE IF EXISTS `catalogue_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogue_categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `precedence` int(10) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogue_categories`
--

LOCK TABLES `catalogue_categories` WRITE;
/*!40000 ALTER TABLE `catalogue_categories` DISABLE KEYS */;
INSERT INTO `catalogue_categories` VALUES (1,0,'Category_a12c5',1,1),(2,0,'Category_d7b84',2,1),(3,0,'Category_c7448',3,1),(4,0,'Category_90664',4,1),(5,0,'Category_976c0',5,1),(6,0,'Category_0e48e',6,1),(7,0,'Category_c5bdb',7,1),(8,0,'Category_37218',8,1),(9,0,'Category_d9d76',9,1);
/*!40000 ALTER TABLE `catalogue_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogue_item_aliases`
--

DROP TABLE IF EXISTS `catalogue_item_aliases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogue_item_aliases` (
  `item_id` bigint(20) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `list_index` int(10) NOT NULL DEFAULT '0',
  UNIQUE KEY `name_uk` (`item_id`,`alias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogue_item_aliases`
--

LOCK TABLES `catalogue_item_aliases` WRITE;
/*!40000 ALTER TABLE `catalogue_item_aliases` DISABLE KEYS */;
/*!40000 ALTER TABLE `catalogue_item_aliases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogue_items`
--

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
  `precedence` int(10) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uk` (`sub_category_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogue_items`
--

LOCK TABLES `catalogue_items` WRITE;
/*!40000 ALTER TABLE `catalogue_items` DISABLE KEYS */;
INSERT INTO `catalogue_items` VALUES (1,0,'Item_12596','DESC_d122af27-19de-48ba-893f-e1af3fedd6ab',1,'VEG',1,1),(2,0,'Item_a90cc','DESC_5aba2dee-3436-4574-b964-d9a755e7e26f',1,'VEG',2,1),(3,0,'Item_8f839','DESC_2f60462e-6fa3-4b98-8c6c-7305f79d8979',1,'VEG',3,1),(4,0,'Item_1ad49','DESC_7c0839e4-5f65-4226-b1b0-b8fa7caa02df',1,'VEG',4,1),(5,0,'Item_eb135','DESC_13ff89bd-8066-455e-bee5-c1da0639f067',1,'VEG',5,1),(6,0,'Item_089b0','DESC_da3b2bd3-b025-4c1b-813e-4e3c085e49d1',1,'VEG',6,1),(7,0,'Item_57c0a','DESC_d396ddd1-f388-41ef-9737-7f57dc0a7354',1,'VEG',7,1),(8,0,'Item_caaab','DESC_65c8e3b3-aa33-404c-a539-5380c9880b2e',1,'VEG',8,1),(9,0,'Pulka',NULL,1,'VEG',1,1),(10,0,'Schezwan Chicken Noodles',NULL,2,'VEG',1,1),(11,0,'Egg Bhurji',NULL,2,'EGG',1,1),(12,0,'Schezwan Egg Noodles',NULL,2,'EGG',1,1),(13,0,'Boneless Mutton Masala',NULL,2,'NON_VEG',1,1),(14,0,'Garlic Chicken with Noodles Combo',NULL,2,'NON_VEG',1,1),(15,0,'Chilli Chicken with Noodles Combo',NULL,2,'NON_VEG',1,1),(16,0,'Chef Special Chicken with Fried Rice',NULL,2,'NON_VEG',1,1),(17,0,'Mixed Veg Curry',NULL,2,'VEG',1,1),(18,0,'Garlic Chicken',NULL,2,'NON_VEG',1,1),(19,0,'Chef Special Chicken with Fried Rice Combo',NULL,2,'NON_VEG',1,1);
/*!40000 ALTER TABLE `catalogue_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogue_sub_categories`
--

DROP TABLE IF EXISTS `catalogue_sub_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogue_sub_categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `precedence` int(10) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uk` (`category_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogue_sub_categories`
--

LOCK TABLES `catalogue_sub_categories` WRITE;
/*!40000 ALTER TABLE `catalogue_sub_categories` DISABLE KEYS */;
INSERT INTO `catalogue_sub_categories` VALUES (1,0,'SubCategory_de937',1,1,1),(2,0,'SubCategory_5196d',1,2,1),(3,0,'SubCategory_7e576',1,3,1);
/*!40000 ALTER TABLE `catalogue_sub_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kitchen_delivery_partners`
--

DROP TABLE IF EXISTS `kitchen_delivery_partners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kitchen_delivery_partners` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL DEFAULT '0',
  `kitchen_id` bigint(20) NOT NULL,
  `delivery_partner` varchar(255) NOT NULL,
  `email_ids` varchar(255) NOT NULL,
  `commission_percentage` double(10,4) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uk` (`kitchen_id`,`delivery_partner`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kitchen_delivery_partners`
--

LOCK TABLES `kitchen_delivery_partners` WRITE;
/*!40000 ALTER TABLE `kitchen_delivery_partners` DISABLE KEYS */;
INSERT INTO `kitchen_delivery_partners` VALUES (5,0,1,'ZOMATO','volamshabarinath@gmail.com,noreply@zomato.com',21.5000,1);
/*!40000 ALTER TABLE `kitchen_delivery_partners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kitchen_discounts`
--

DROP TABLE IF EXISTS `kitchen_discounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kitchen_discounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL DEFAULT '0',
  `kitchen_id` bigint(20) NOT NULL,
  `delivery_partner` varchar(255) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `discount` double(10,4) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kitchen_discounts`
--

LOCK TABLES `kitchen_discounts` WRITE;
/*!40000 ALTER TABLE `kitchen_discounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `kitchen_discounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kitchen_items`
--

DROP TABLE IF EXISTS `kitchen_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kitchen_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL DEFAULT '0',
  `kitchen_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `manufacturing_price` double(10,4) NOT NULL,
  `market_price` double(10,4) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uk` (`kitchen_id`,`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kitchen_items`
--

LOCK TABLES `kitchen_items` WRITE;
/*!40000 ALTER TABLE `kitchen_items` DISABLE KEYS */;
INSERT INTO `kitchen_items` VALUES (1,0,1,1,6.0000,10.0000,1),(2,0,1,2,80.0000,100.0000,1),(3,0,1,10,80.0000,100.0000,1),(4,0,1,9,6.0000,10.0000,1),(5,0,1,13,80.0000,180.0000,1),(6,0,1,11,40.0000,70.0000,1),(7,0,1,12,70.0000,100.0000,1),(9,0,1,15,70.0000,150.0000,1),(10,0,1,16,70.0000,120.0000,1),(11,0,1,17,50.0000,120.0000,1),(12,0,1,18,150.0000,240.0000,1),(13,0,1,19,150.0000,220.0000,1);
/*!40000 ALTER TABLE `kitchen_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kitchens`
--

DROP TABLE IF EXISTS `kitchens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kitchens` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `mailbox_username` varchar(255) NOT NULL,
  `mailbox_password` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kitchens`
--

LOCK TABLES `kitchens` WRITE;
/*!40000 ALTER TABLE `kitchens` DISABLE KEYS */;
INSERT INTO `kitchens` VALUES (1,0,'Biryani Vs Pulav','Kitchensofchina@gmail.com','koc654321',1);
/*!40000 ALTER TABLE `kitchens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL DEFAULT '0',
  `order_id` bigint(20) NOT NULL,
  `kitchen_item_id` bigint(20) NOT NULL,
  `quantity` int(10) NOT NULL,
  `manufacturing_price` double(10,4) NOT NULL,
  `market_price` double(10,4) NOT NULL,
  `dp_received_price` double(10,4) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uk` (`order_id`,`kitchen_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,0,2,13,1,150.0000,220.0000,220.0000,1),(2,0,3,9,1,70.0000,150.0000,220.0000,1),(3,0,13,3,1,80.0000,100.0000,200.0000,1),(4,0,14,6,1,40.0000,70.0000,150.0000,1),(5,0,15,7,1,70.0000,100.0000,160.0000,1),(6,0,16,5,1,80.0000,180.0000,270.0000,1),(7,0,17,9,1,70.0000,150.0000,220.0000,1),(8,0,18,9,1,70.0000,150.0000,220.0000,1),(9,0,18,13,1,150.0000,220.0000,220.0000,1),(10,0,19,11,1,50.0000,120.0000,190.0000,1),(11,0,20,9,1,70.0000,150.0000,220.0000,1),(12,0,25,9,1,70.0000,150.0000,220.0000,1),(13,0,36,12,1,150.0000,240.0000,240.0000,1),(14,0,36,9,1,70.0000,150.0000,220.0000,1),(15,0,36,13,1,150.0000,220.0000,220.0000,1);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL DEFAULT '0',
  `kitchen_id` bigint(20) NOT NULL,
  `delivery_partner` varchar(255) NOT NULL,
  `dp_order_id` varchar(255) NOT NULL,
  `parsed_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ordered_time` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `total_cost` double(10,4) NOT NULL,
  `notes` text,
  `manual_review` tinyint(1) NOT NULL DEFAULT '0',
  `manual_review_comments` text,
  `payment_type` varchar(255) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dp_order_id_uk` (`dp_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,0,1,'ZOMATO','1593465149','2018-11-28 16:22:47','2018-11-16 00:00:00','DELIVERED',160.0000,NULL,1,'Item Name: Mixed Veg Noodles kitchenObj: null may be empty!!','Prepaid',1),(2,0,1,'ZOMATO','1670735554','2018-11-28 16:22:48','2018-11-16 00:00:00','DELIVERED',220.0000,NULL,0,'','Prepaid',1),(3,0,1,'ZOMATO','1142881152','2018-11-28 16:22:49','2018-11-16 00:00:00','DELIVERED',220.0000,NULL,0,'','Prepaid',1),(4,0,1,'ZOMATO','1332921601','2018-11-28 16:22:51','2018-11-17 00:00:00','DELIVERED',340.0000,NULL,1,'Item Name: Schezwan Veg Fried Rice kitchenObj: null may be empty!!','COD',1),(5,0,1,'ZOMATO','1141156211','2018-11-28 16:22:52','2018-11-17 00:00:00','DELIVERED',240.0000,NULL,1,'Item Name: Dragon Chicken kitchenObj: null may be empty!!','Prepaid',1),(6,0,1,'ZOMATO','1611793025','2018-11-28 16:22:53','2018-11-17 00:00:00','DELIVERED',220.0000,NULL,1,'Item Name: Baby Corn Manchurian with Noodle Combo kitchenObj: null may be empty!!','Prepaid',1),(7,0,1,'ZOMATO','1189211932','2018-11-28 16:22:54','2018-11-18 00:00:00','DELIVERED',400.0000,NULL,1,'Item Name: Chilli Egg with Fried Rice Combo kitchenObj: null may be empty!!','Prepaid',1),(8,0,1,'ZOMATO','1531258433','2018-11-28 16:22:55','2018-11-18 00:00:00','DELIVERED',220.0000,NULL,1,'Item Name: Chilli Paneer with Noodles Combo kitchenObj: null may be empty!!','Prepaid',1),(9,0,1,'ZOMATO','1094212784','2018-11-28 16:22:56','2018-11-18 00:00:00','DELIVERED',220.0000,NULL,1,'Item Name: Garlic Chicken with Noodles Combo kitchenObj: null may be empty!!','Prepaid',1),(10,0,1,'ZOMATO','1682270698','2018-11-28 16:22:57','2018-11-19 00:00:00','DELIVERED',220.0000,NULL,1,'Item Name: Chef Special Chicken with Noodles Combo kitchenObj: null may be empty!!','Prepaid',1),(11,0,1,'ZOMATO','1444182947','2018-11-28 16:22:58','2018-11-19 00:00:00','DELIVERED',96.0000,NULL,1,'Item Name: Chicken Hot and Sour Soup kitchenObj: null may be empty!!','Prepaid',1),(12,0,1,'ZOMATO','1522209874','2018-11-28 16:22:59','2018-11-19 00:00:00','DELIVERED',160.0000,NULL,1,'Item Name: Veg Manchurian with Noodles Combo kitchenObj: null may be empty!!','Prepaid',1),(13,0,1,'ZOMATO','1000419127','2018-11-28 16:23:00','2018-11-20 00:00:00','DELIVERED',160.0000,NULL,0,'','Prepaid',1),(14,0,1,'ZOMATO','1236428433','2018-11-28 16:23:01','2018-11-20 00:00:00','DELIVERED',200.0000,NULL,1,'Item Name: Butter Chapati kitchenObj: null may be empty!!','Prepaid',1),(15,0,1,'ZOMATO','1224034134','2018-11-28 16:23:02','2018-11-20 00:00:00','DELIVERED',128.0000,NULL,0,'','COD',1),(16,0,1,'ZOMATO','1324885256','2018-11-28 16:23:03','2018-11-20 00:00:00','DELIVERED',472.0000,NULL,1,'Item Name: Schezwan Veg Noodles kitchenObj: null may be empty!!','COD',1),(17,0,1,'ZOMATO','1306751331','2018-11-28 16:23:04','2018-11-21 00:00:00','DELIVERED',352.0000,NULL,1,'Item Name: Garlic Chicken with Noodles Combo kitchenObj: null may be empty!!','Prepaid',1),(18,0,1,'ZOMATO','1492793170','2018-11-28 16:23:05','2018-11-21 00:00:00','DELIVERED',352.0000,NULL,0,'','Prepaid',1),(19,0,1,'ZOMATO','1162508460','2018-11-28 16:23:05','2018-11-21 00:00:00','DELIVERED',209.6000,NULL,1,'Item Name: Chapati kitchenObj: null may be empty!!','Prepaid',1),(20,0,1,'ZOMATO','1091488956','2018-11-28 16:23:06','2018-11-21 00:00:00','DELIVERED',176.0000,NULL,0,'','Prepaid',1),(21,0,1,'ZOMATO','1206292440','2018-11-28 16:23:07','2018-11-22 00:00:00','DELIVERED',176.0000,NULL,1,'Item Name: Chilli Paneer with Fried Rice Combo kitchenObj: null may be empty!!','Prepaid',1),(22,0,1,'ZOMATO','1536890831','2018-11-28 16:23:08','2018-11-23 00:00:00','DELIVERED',120.0000,NULL,1,'Item Name: Veg Noodles kitchenObj: null may be empty!!','Prepaid',1),(23,0,1,'ZOMATO','1337567589','2018-11-28 16:23:09','2018-11-23 00:00:00','DELIVERED',160.0000,NULL,1,'Item Name: Schezwan Mushroom Noodles kitchenObj: null may be empty!!','Prepaid',1),(24,0,1,'ZOMATO','1465693208','2018-11-28 16:23:11','2018-11-23 00:00:00','DELIVERED',176.0000,NULL,1,'Item Name: Baby Corn Manchurian with Noodle Combo kitchenObj: null may be empty!!','Prepaid',1),(25,0,1,'ZOMATO','1550450858','2018-11-28 16:23:13','2018-11-23 00:00:00','DELIVERED',176.0000,NULL,0,'','Prepaid',1),(26,0,1,'ZOMATO','1696040776','2018-11-28 16:23:14','2018-11-24 00:00:00','DELIVERED',120.0000,NULL,1,'Item Name: Veg Fried Rice kitchenObj: null may be empty!!','Prepaid',1),(27,0,1,'ZOMATO','1336702831','2018-11-28 16:23:15','2018-11-24 00:00:00','DELIVERED',376.0000,NULL,1,'Item Name: Chicken Manchurian kitchenObj: null may be empty!!','Prepaid',1),(28,0,1,'ZOMATO','1671308822','2018-11-28 16:23:16','2018-11-24 00:00:00','DELIVERED',176.0000,NULL,1,'Item Name: Chef Special Chicken with Noodles Combo kitchenObj: null may be empty!!','Prepaid',1),(29,0,1,'ZOMATO','1598601682','2018-11-28 16:23:17','2018-11-24 00:00:00','DELIVERED',534.4000,NULL,1,'Item Name: Phuka kitchenObj: null may be empty!!','Prepaid',1),(30,0,1,'ZOMATO','1253805350','2018-11-28 16:23:18','2018-11-24 00:00:00','DELIVERED',152.0000,NULL,1,'Item Name: Chicken Noodles kitchenObj: null may be empty!!','Prepaid',1),(31,0,1,'ZOMATO','1098355250','2018-11-28 16:23:19','2018-11-24 00:00:00','DELIVERED',176.0000,NULL,1,'Item Name: Chicken Manchurian with Noodles Combo kitchenObj: null may be empty!!','Prepaid',1),(32,0,1,'ZOMATO','1623157493','2018-11-28 16:23:21','2018-11-25 00:00:00','DELIVERED',304.0000,NULL,1,'Item Name: Chilli Paneer kitchenObj: null may be empty!!','Prepaid',1),(33,0,1,'ZOMATO','1221368994','2018-11-28 16:23:21','2018-11-26 00:00:00','DELIVERED',160.0000,NULL,1,'Item Name: Chilli Aloo with Noodles Combo kitchenObj: null may be empty!!','Prepaid',1),(34,0,1,'ZOMATO','1621844102','2018-11-28 16:23:23','2018-11-27 00:00:00','DELIVERED',160.0000,NULL,1,'Item Name: Chilli Egg with Noodles Combo kitchenObj: null may be empty!!','Prepaid',1),(35,0,1,'ZOMATO','1479518921','2018-11-28 16:23:24','2018-11-27 00:00:00','DELIVERED',160.0000,NULL,1,'Item Name: Veg Manchurian with Noodles Combo kitchenObj: null may be empty!!','COD',1),(36,0,1,'ZOMATO','1592814926','2018-11-28 16:23:25','2018-11-28 00:00:00','DELIVERED',544.0000,NULL,0,'','Prepaid',1),(37,0,1,'ZOMATO','1610654127','2018-11-28 16:23:26','2018-11-28 00:00:00','DELIVERED',176.0000,NULL,1,'Item Name: Chef Special Chicken with Noodles Combo kitchenObj: null may be empty!!','Prepaid',1),(38,0,1,'ZOMATO','1225180871','2018-11-28 16:23:27','2018-11-28 00:00:00','DELIVERED',160.0000,NULL,1,'Item Name: Egg 65 with Fried Rice Combo kitchenObj: null may be empty!!','Prepaid',1),(39,0,1,'ZOMATO','1521958961','2018-11-28 16:23:28','2018-11-29 00:00:00','DELIVERED',120.0000,NULL,1,'Item Name: Egg Noodles kitchenObj: null may be empty!!','Prepaid',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
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
  `class_code` varchar(30) NOT NULL,
  `version` bigint(20) DEFAULT '0',
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `account_locked` tinyint(1) NOT NULL DEFAULT '0',
  `account_expired` tinyint(1) NOT NULL DEFAULT '0',
  `credentials_expired` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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

-- Dump completed on 2018-11-29  3:23:37
