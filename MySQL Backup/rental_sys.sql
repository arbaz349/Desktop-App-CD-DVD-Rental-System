-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: rental_sys
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.18.04.1

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `cust_id` varchar(30) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `mobno` varchar(15) NOT NULL,
  `dob` date NOT NULL,
  `locked` varchar(3) DEFAULT 'No',
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('arbaz349','Arbaz','Shaikh','Viman Nagar','1234567890','1999-03-04','No'),('v','v','v','v','8007401402','1999-03-04','No'),('vector349','Vector','Shaikh','Shivaji Nagar','8007401402','1999-03-04','No');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dvd`
--

DROP TABLE IF EXISTS `dvd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dvd` (
  `dvd_id` varchar(30) NOT NULL,
  `name` varchar(20) NOT NULL,
  `genre1` varchar(20) NOT NULL,
  `genre2` varchar(20) DEFAULT NULL,
  `actor1` varchar(20) NOT NULL,
  `actor2` varchar(20) NOT NULL,
  `actor3` varchar(20) DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `certificate` varchar(3) NOT NULL,
  `totalstock` int(11) NOT NULL DEFAULT '0',
  `addinfo` varchar(50) DEFAULT NULL,
  `price` double NOT NULL DEFAULT '0',
  `noofreview` int(11) NOT NULL,
  `totalrating` double NOT NULL,
  `rented` varchar(3) NOT NULL DEFAULT 'No',
  PRIMARY KEY (`dvd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dvd`
--

LOCK TABLES `dvd` WRITE;
/*!40000 ALTER TABLE `dvd` DISABLE KEYS */;
INSERT INTO `dvd` VALUES ('dvd01','Pulp Fiction','Crime','Documentry','Tim Roth','Amanda Plummer','Samuel L Jackson','English','A',50,'Won 1 Oscar',85,0,0,'No'),('dvd02','Titanic','Romance','Documentry','Leonardo DiCaprio','Kate Winslet','Billy Zane','English','U/A',75,'Won 11 Oscar',50,1,5,'yes'),('dvd03','The Dark Knight','Action','Crime','Christian Bale','Heath Ledger','Morgan Freeman','English','U/A',100,'Won 2 Oscar',85,1,5,'No'),('dvd04','Avatar','Action','Adventure','Sam Worthington','Zoe Saldana','Stephen Lang','English','U/A',45,'Won 3 Oscar',60,0,0,'No'),('dvd05','Inception','Action','Science-Fiction','Leonardo DiCaprio','Joseph Gordon-Levitt','Ellen Page','English','U/A',110,'Won 4 Oscar',85,0,0,'Yes'),('dvd06','3 Idiots','Adventure','None','Aamir Khan','R. Madhavan','Sharman Joshi','Indian','U/A',120,'Release Date 25/12/2009',88,0,0,'Yes'),('dvd07','Bajrangi Bhaijaan','Action','Adventure','Salman Khan','Harshaali Malhotra ','Nawazuddin Siddiqui','Indian','U/A',105,'Release date 17/7/2015',75,0,0,'No'),('dvd08','Padmaavat','Action','Documentry','Deepika Padukone','Shahid Kapoor','Ranveer Singh','Indian','U/A',75,'Release Date 20/1/2018',65,0,0,'yes');
/*!40000 ALTER TABLE `dvd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hisrental`
--

DROP TABLE IF EXISTS `hisrental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hisrental` (
  `cust_id` varchar(30) NOT NULL,
  `dvd_id` varchar(30) NOT NULL,
  `renteddate` date NOT NULL,
  `returndate` date NOT NULL,
  `amountpaid` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`cust_id`,`dvd_id`,`renteddate`,`returndate`,`amountpaid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hisrental`
--

LOCK TABLES `hisrental` WRITE;
/*!40000 ALTER TABLE `hisrental` DISABLE KEYS */;
INSERT INTO `hisrental` VALUES ('vector349','dvd02','2018-09-10','2018-10-10',2125),('vector349','dvd03','2018-10-10','2018-10-10',255);
/*!40000 ALTER TABLE `hisrental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `que` varchar(50) NOT NULL,
  `ans` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('arbaz349','vector','arbaz@gmail.com','What is Pet Name ?','vector'),('vector349','arbaz','vector@gmail.com','What is Pet Name ?','arbaz');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rental` (
  `cust_id` varchar(30) NOT NULL,
  `dvd_id` varchar(30) NOT NULL,
  `renteddate` date NOT NULL,
  `duedate` date NOT NULL,
  `paid` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`cust_id`,`dvd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental`
--

LOCK TABLES `rental` WRITE;
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
INSERT INTO `rental` VALUES ('arbaz349','dvd06','2018-10-10','2018-10-13',264),('arbaz349','dvd08','2018-09-10','2018-09-15',325),('vector349','dvd02','2018-09-10','2018-09-15',250),('vector349','dvd05','2018-10-10','2018-10-13',255);
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-10  0:21:39
