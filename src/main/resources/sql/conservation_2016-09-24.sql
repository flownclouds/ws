# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.9)
# Database: conservation
# Generation Time: 2016-09-24 11:12:50 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table county
# ------------------------------------------------------------

DROP TABLE IF EXISTS `county`;

CREATE TABLE `county` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `is_active` int(1) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `county` WRITE;
/*!40000 ALTER TABLE `county` DISABLE KEYS */;

INSERT INTO `county` (`id`, `is_delete`, `is_active`, `name`, `latitude`, `longitude`, `create_time`, `update_time`)
VALUES
	(1,0,1,'东海县','123.0','134.0',1474706352371,1474706352371);

/*!40000 ALTER TABLE `county` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `group`;

CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `is_active` int(1) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `county_id` int(11) NOT NULL,
  `town_id` int(11) NOT NULL,
  `village_id` int(11) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k2co1luo4wqd4n8lrc0iqo1xw` (`county_id`),
  KEY `FK_2x2dtya37gti9erqbme4p5mcb` (`town_id`),
  KEY `FK_fyv8x469enc9r1xioqrjh42oo` (`village_id`),
  CONSTRAINT `FK_2x2dtya37gti9erqbme4p5mcb` FOREIGN KEY (`town_id`) REFERENCES `town` (`id`),
  CONSTRAINT `FK_fyv8x469enc9r1xioqrjh42oo` FOREIGN KEY (`village_id`) REFERENCES `village` (`id`),
  CONSTRAINT `FK_k2co1luo4wqd4n8lrc0iqo1xw` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;

INSERT INTO `group` (`id`, `is_delete`, `is_active`, `name`, `county_id`, `town_id`, `village_id`, `create_time`, `update_time`)
VALUES
	(1,0,1,'殿下组',1,1,1,1474706352371,1474706352371);

/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table notification
# ------------------------------------------------------------

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `category` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `county_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s7cyy7qu30n9q55sa8sc9yorh` (`county_id`),
  KEY `FK_1urdwwsh2ti15ta6f6p5dbdcp` (`user_id`),
  CONSTRAINT `FK_1urdwwsh2ti15ta6f6p5dbdcp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_s7cyy7qu30n9q55sa8sc9yorh` FOREIGN KEY (`county_id`) REFERENCES `exciting`.`county` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `is_active` int(1) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`id`, `is_delete`, `is_active`, `name`, `create_time`, `update_time`)
VALUES
	(1,0,1,'admin',1474706352371,1474706352371),
	(2,0,1,'superGuest',1474706352371,1474706352371),
	(3,0,1,'guest',1474706352371,1474706352371);

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table town
# ------------------------------------------------------------

DROP TABLE IF EXISTS `town`;

CREATE TABLE `town` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `is_active` int(1) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `county_id` int(11) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g1yig1kwhpmbl3m9kkxrcy5w4` (`county_id`),
  CONSTRAINT `FK_g1yig1kwhpmbl3m9kkxrcy5w4` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `town` WRITE;
/*!40000 ALTER TABLE `town` DISABLE KEYS */;

INSERT INTO `town` (`id`, `is_delete`, `is_active`, `name`, `county_id`, `create_time`, `update_time`)
VALUES
	(1,0,1,'唐江镇',1,1474706352371,1474706352371);

/*!40000 ALTER TABLE `town` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `is_active` int(1) NOT NULL DEFAULT '1',
  `is_super_admin` int(1) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `salt` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `county_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gj2fy3dcix7ph7k8684gka40c` (`name`),
  KEY `FK_efsj47nspf3aa5vbtuus01pgr` (`county_id`),
  KEY `FK_qleu8ddawkdltal07p8e6hgva` (`role_id`),
  CONSTRAINT `FK_efsj47nspf3aa5vbtuus01pgr` FOREIGN KEY (`county_id`) REFERENCES `exciting`.`county` (`id`),
  CONSTRAINT `FK_qleu8ddawkdltal07p8e6hgva` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `is_delete`, `email`, `is_active`, `is_super_admin`, `name`, `password`, `phone`, `real_name`, `salt`, `token`, `county_id`, `role_id`, `create_time`, `update_time`)
VALUES
	(1,0,NULL,1,1,'weck','AEw8/HWJ4q39RI8Y3VUCVzUSn7M1mwh1dNrVvs2H+HK8iz3SNy22jcBid2DARij5xbMhCzuUkc+QQ5VzNq53Ua4=',NULL,'weck','ci6Yl0pXZTr7Ikw2sFEyy4MjQgdyZpSGeKi+VZnIVJ0=','3096780c4e3d402ba7573fd6a65c1cd6',1,1,1474706352371,1474706352371);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_towns
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_towns`;

CREATE TABLE `user_towns` (
  `user` int(11) NOT NULL,
  `towns` int(11) NOT NULL,
  KEY `FK_1imlgmi2e69gkrlw3u16igf7j` (`towns`),
  KEY `FK_phvv7at2okykcp95r8s4mf9et` (`user`),
  CONSTRAINT `FK_1imlgmi2e69gkrlw3u16igf7j` FOREIGN KEY (`towns`) REFERENCES `exciting`.`town` (`id`),
  CONSTRAINT `FK_phvv7at2okykcp95r8s4mf9et` FOREIGN KEY (`user`) REFERENCES `exciting`.`user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_towns` WRITE;
/*!40000 ALTER TABLE `user_towns` DISABLE KEYS */;

INSERT INTO `user_towns` (`user`, `towns`)
VALUES
	(1,1);

/*!40000 ALTER TABLE `user_towns` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table village
# ------------------------------------------------------------

DROP TABLE IF EXISTS `village`;

CREATE TABLE `village` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `is_active` int(1) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `county_id` int(11) NOT NULL,
  `town_id` int(11) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_u4ot3j9i9os4tagjmjmfudfh` (`county_id`),
  KEY `FK_7ys24ftdgyvr974qxtqhf1rd7` (`town_id`),
  CONSTRAINT `FK_7ys24ftdgyvr974qxtqhf1rd7` FOREIGN KEY (`town_id`) REFERENCES `town` (`id`),
  CONSTRAINT `FK_u4ot3j9i9os4tagjmjmfudfh` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `village` WRITE;
/*!40000 ALTER TABLE `village` DISABLE KEYS */;

INSERT INTO `village` (`id`, `is_delete`, `is_active`, `name`, `county_id`, `town_id`, `create_time`, `update_time`)
VALUES
	(1,0,1,'江大村',1,1,1474706352371,1474706352371);

/*!40000 ALTER TABLE `village` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
