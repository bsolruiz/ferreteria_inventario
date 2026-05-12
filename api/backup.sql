-- MySQL dump 10.13  Distrib 8.0.45, for Linux (x86_64)
--
-- Host: localhost    Database: ferreteria_db
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Herramientas'),(2,'Plomera'),(3,'Elctrico'),(4,'Construccin'),(5,'Herramientas Manuales'),(6,'Herramientas Electricas'),(7,'Materiales de Construccion'),(8,'Pinturas y Accesorios'),(9,'Electricidad'),(10,'Plomeria'),(11,'Tornilleria y Fijaciones'),(12,'Seguridad Industrial'),(13,'Jardineria'),(14,'Adhesivos y Selladores'),(15,'Iluminacion'),(16,'Cerrajeria'),(17,'Accesorios para Bao'),(18,'Maderas y Tableros'),(19,'Equipos de Medicion');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimiento` (
  `id_movimiento` int NOT NULL AUTO_INCREMENT,
  `fecha_registro` datetime(6) DEFAULT NULL,
  `tipo_movimiento` enum('ENTRADA','SALIDA') DEFAULT NULL,
  `usuario_id` bigint NOT NULL,
  `cantidad` int NOT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `producto_id` int NOT NULL,
  PRIMARY KEY (`id_movimiento`),
  KEY `FK5y2etfql509hed7xho29j555o` (`usuario_id`),
  KEY `FK73whckhwu6526uvwldwoct89t` (`producto_id`),
  CONSTRAINT `FK5y2etfql509hed7xho29j555o` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FK73whckhwu6526uvwldwoct89t` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento`
--

LOCK TABLES `movimiento` WRITE;
/*!40000 ALTER TABLE `movimiento` DISABLE KEYS */;
INSERT INTO `movimiento` VALUES (8,'2026-05-11 14:13:17.650741','ENTRADA',1,100,'asdsa',19),(9,'2026-05-11 18:34:30.666472','ENTRADA',1,4,'asd',18);
/*!40000 ALTER TABLE `movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `codigo_barras` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `nombre_producto` varchar(255) DEFAULT NULL,
  `precio` decimal(38,2) DEFAULT NULL,
  `categoria_id` int NOT NULL,
  `cantidad` int DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  UNIQUE KEY `UK_nrobij4fg0dpdfcro7t2ystd1` (`codigo_barras`),
  KEY `FKodqr7965ok9rwquj1utiamt0m` (`categoria_id`),
  CONSTRAINT `FKodqr7965ok9rwquj1utiamt0m` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (18,'213213213a','2esqed','2026-05-11 18:34:30.672252','2026-05-11 12:28:15.093607','asdsad',123213.00,1,4),(19,'1232132132112321321312321','32wqsad','2026-05-11 14:13:17.655342','2026-05-11 14:12:58.360346','asdsadsad',12323.00,1,100),(20,'21sadsad','21321','2026-05-11 18:34:10.621570','2026-05-11 18:34:10.621570','asd',21321.00,1,0),(21,'770100100001','Martillo de acero con mango ergonmico','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Martillo',25000.00,1,50),(22,'770100100002','Destornillador plano 6 pulgadas','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Destornillador Plano',8000.00,1,30),(23,'770100100003','Taladro elctrico 500W','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Taladro Electrico',180000.00,2,20),(24,'770100100004','Sierra circular 7 1/4 pulgadas','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Sierra Circular',220000.00,2,15),(25,'770100100005','Bolsa de cemento 50kg','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Cemento',32000.00,3,100),(26,'770100100006','Pintura blanca tipo 1 galon','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Pintura Blanca',45000.00,4,80),(27,'770100100007','Cable elctrico calibre 12','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Cable Electrico',3000.00,5,60),(28,'770100100008','Tubo PVC 1/2 pulgada','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Tubo PVC',12000.00,6,40),(29,'770100100009','Caja de tornillos 1 pulgada','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Tornillos',5000.00,7,200),(30,'770100100010','Casco de seguridad industrial','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Casco Seguridad',35000.00,8,25),(31,'770100100011','Pala para jardinera','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Pala',20000.00,9,35),(32,'770100100012','Silicona multiusos','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Silicona',10000.00,10,70),(33,'770100100013','Bombillo LED 12W','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Bombillo LED',7000.00,11,45),(34,'770100100014','Cerradura para puerta principal','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Cerradura',85000.00,12,10),(35,'770100100015','Lavamanos cermico','2026-05-12 15:27:15.000000','2026-05-12 15:27:15.000000','Lavamanos',95000.00,13,55);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Admin'),(2,'Encargado');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `contrasena` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `estado` bigint DEFAULT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `rol_id` int NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_2mlfr087gb1ce55f2j87o74t` (`correo`),
  KEY `FKshkwj12wg6vkm6iuwhvcfpct8` (`rol_id`),
  CONSTRAINT `FKshkwj12wg6vkm6iuwhvcfpct8` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'$2a$10$0SfBXVdH5qe0vnwN5r9sSOTzSvcCI53l1Unx02jcLVoU6hXimWAX2','admin@ferreteria.com',1,NULL,'2026-04-13 22:51:22.623122','Administrador',1),(2,'$2a$10$35sKQYrZUmba26DVwGIc1.fxEcoapqR2FSIidWvihPvHYH9tjlRS2','bodega@ferreteria.com',1,'2026-04-24 15:30:57.503950','2026-04-13 22:53:12.883946','Encargado Bodega',2),(3,'$2a$10$SjvvzFduHNCrfuNdEClu7OIDubPlgeXUldVgZtOxlUGax4GYfeqmy','admin@ferreteria2.com',1,NULL,'2026-04-24 15:31:30.992765','aa',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-12 15:46:05
