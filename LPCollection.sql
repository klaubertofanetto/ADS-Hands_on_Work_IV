-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: LPCollection
-- ------------------------------------------------------
-- Server version	8.0.22-0ubuntu0.20.10.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `disco`
--

DROP TABLE IF EXISTS `disco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disco` (
  `idDisco` int NOT NULL AUTO_INCREMENT,
  `nomeDisco` varchar(50) NOT NULL,
  `banda` varchar(50) NOT NULL,
  `gravadora` varchar(50) DEFAULT NULL,
  `ano` int DEFAULT NULL,
  PRIMARY KEY (`idDisco`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disco`
--

LOCK TABLES `disco` WRITE;
/*!40000 ALTER TABLE `disco` DISABLE KEYS */;
INSERT INTO `disco` VALUES (1,'Master of Puppets','Metallica','Blackened Recordings',1986),(2,'Reload','Metallica','Blackened Recordings',1997),(3,'Images and Words','Dream Theater','',0),(4,'Psychotic Symphony','Sons of Apollo','',2017),(14,'Octavarium','Dream Theater','',0);
/*!40000 ALTER TABLE `disco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musico`
--

DROP TABLE IF EXISTS `musico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `musico` (
  `idMusico` int NOT NULL AUTO_INCREMENT,
  `nomeMusico` varchar(50) NOT NULL,
  `nacionalidade` varchar(50) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  PRIMARY KEY (`idMusico`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musico`
--

LOCK TABLES `musico` WRITE;
/*!40000 ALTER TABLE `musico` DISABLE KEYS */;
INSERT INTO `musico` VALUES (1,'James Hetfield','americano','1963-08-03'),(2,'Cliff Burton','americano','1962-02-10'),(3,'Lars Ulrich','dinamarquês','1963-12-26'),(4,'Kirk Hammet','americano','1962-11-18'),(5,'Mike Portnoy','americano','1967-04-20'),(6,'Billy Sheehan','americano','1953-03-19'),(7,'John Petrucci','americano','1967-07-12'),(8,'James LaBrie','canadense','1963-05-05'),(9,'John Myung','americano','1967-01-24'),(10,'Jeff Scott Soto','americano','1965-11-04'),(11,'Bumblefoot','americano','1968-09-25'),(12,'Derek Sherinian','americano','1966-08-25'),(15,'Bruce Dickinson','inglês','1958-08-07'),(17,'Jordan Rudess','americano','1960-02-03');
/*!40000 ALTER TABLE `musico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musicoPorDisco`
--

DROP TABLE IF EXISTS `musicoPorDisco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `musicoPorDisco` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idMusico` int NOT NULL,
  `idDisco` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idMusico` (`idMusico`),
  KEY `idDisco` (`idDisco`),
  CONSTRAINT `musicoPorDisco_ibfk_1` FOREIGN KEY (`idMusico`) REFERENCES `musico` (`idMusico`),
  CONSTRAINT `musicoPorDisco_ibfk_2` FOREIGN KEY (`idDisco`) REFERENCES `disco` (`idDisco`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musicoPorDisco`
--

LOCK TABLES `musicoPorDisco` WRITE;
/*!40000 ALTER TABLE `musicoPorDisco` DISABLE KEYS */;
INSERT INTO `musicoPorDisco` VALUES (1,4,2),(6,7,14),(7,5,4),(8,5,14),(9,5,3),(10,8,3),(11,7,3),(19,2,1),(20,3,1),(21,4,1),(26,1,1),(30,3,2);
/*!40000 ALTER TABLE `musicoPorDisco` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-24 17:42:18
