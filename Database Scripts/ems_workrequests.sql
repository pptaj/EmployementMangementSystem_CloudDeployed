-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: ems
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
-- Table structure for table `workrequests`
--

DROP TABLE IF EXISTS `workrequests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workrequests` (
  `workRequestID` int(11) NOT NULL,
  `adminComments` varchar(255) DEFAULT NULL,
  `designation` varchar(255) NOT NULL,
  `emailAddress` varchar(255) NOT NULL,
  `employeeID` int(11) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `managerComments` varchar(255) DEFAULT NULL,
  `requestStatus` varchar(255) DEFAULT NULL,
  `userRole` varchar(255) NOT NULL,
  `MID` int(11) DEFAULT NULL,
  PRIMARY KEY (`workRequestID`),
  UNIQUE KEY `UK_53941aqre28m848e0f1yj21fw` (`employeeID`),
  KEY `FK21dysb5d82n51wxe1cgjp9e8j` (`MID`),
  CONSTRAINT `FK21dysb5d82n51wxe1cgjp9e8j` FOREIGN KEY (`MID`) REFERENCES `manager` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

