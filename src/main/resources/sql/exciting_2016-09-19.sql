# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.13)
# Database: exciting
# Generation Time: 2016-09-18 23:16:36 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table approval
# ------------------------------------------------------------

DROP TABLE IF EXISTS `approval`;

CREATE TABLE `approval` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `file` varchar(255) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `symbol` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `project_detail_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jr9jmlc8rakxwu5rx4ujvymfg` (`project_detail_id`),
  CONSTRAINT `FK_jr9jmlc8rakxwu5rx4ujvymfg` FOREIGN KEY (`project_detail_id`) REFERENCES `project_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table aqueduct
# ------------------------------------------------------------

DROP TABLE IF EXISTS `aqueduct`;

CREATE TABLE `aqueduct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cross_count` varchar(255) DEFAULT NULL,
  `cross_length` varchar(255) DEFAULT NULL,
  `cross_watercourse_location` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `section_size` varchar(255) DEFAULT NULL,
  `structure_and_material` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `aqueduct` WRITE;
/*!40000 ALTER TABLE `aqueduct` DISABLE KEYS */;

INSERT INTO `aqueduct` (`id`, `cross_count`, `cross_length`, `cross_watercourse_location`, `image`, `section_size`, `structure_and_material`)
VALUES
	(1,'','','','','',''),
	(2,'','','','','',''),
	(3,'','','','','',''),
	(4,'','','','','',''),
	(5,'','','','','','');

/*!40000 ALTER TABLE `aqueduct` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bridge
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bridge`;

CREATE TABLE `bridge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cross_count` varchar(255) DEFAULT NULL,
  `cross_length` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `length` varchar(255) DEFAULT NULL,
  `load_standard` varchar(255) DEFAULT NULL,
  `structure_and_material` varchar(255) DEFAULT NULL,
  `watercourse_location` varchar(255) DEFAULT NULL,
  `width` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table canal
# ------------------------------------------------------------

DROP TABLE IF EXISTS `canal`;

CREATE TABLE `canal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `length` varchar(255) DEFAULT NULL,
  `lining_material` varchar(255) DEFAULT NULL,
  `lining_section_size` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `section_size` varchar(255) DEFAULT NULL,
  `seepage_canal_length` varchar(255) DEFAULT NULL,
  `channel_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ifvxylyar7lp5059nnoa9cd9j` (`channel_id`),
  CONSTRAINT `FK_ifvxylyar7lp5059nnoa9cd9j` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table channel
# ------------------------------------------------------------

DROP TABLE IF EXISTS `channel`;

CREATE TABLE `channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `building_match_rate` varchar(255) DEFAULT NULL,
  `good_condition_rate` varchar(255) DEFAULT NULL,
  `head_or_pump_station` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `length` varchar(255) DEFAULT NULL,
  `lining_section_size` varchar(255) DEFAULT NULL,
  `plane_sketch` varchar(255) DEFAULT NULL,
  `section_size` varchar(255) DEFAULT NULL,
  `seepage_canal_length` varchar(255) DEFAULT NULL,
  `sum_length` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table check
# ------------------------------------------------------------

DROP TABLE IF EXISTS `check`;

CREATE TABLE `check` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `file` varchar(255) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `project_detail_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9hl69ln828c7m04l1kwv3da4l` (`project_detail_id`),
  CONSTRAINT `FK_9hl69ln828c7m04l1kwv3da4l` FOREIGN KEY (`project_detail_id`) REFERENCES `project_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table county
# ------------------------------------------------------------

DROP TABLE IF EXISTS `county`;

CREATE TABLE `county` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `is_active` int(1) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `location_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gp9yyqij2mk8w21x8ut6lvxr8` (`location_id`),
  CONSTRAINT `FK_gp9yyqij2mk8w21x8ut6lvxr8` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `county` WRITE;
/*!40000 ALTER TABLE `county` DISABLE KEYS */;

INSERT INTO `county` (`id`, `create_time`, `is_delete`, `update_time`, `is_active`, `name`, `location_id`)
VALUES
	(1,'2016-09-10 00:00:00',0,'2016-09-10 00:00:00',1,'东海县',NULL);

/*!40000 ALTER TABLE `county` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table culvert
# ------------------------------------------------------------

DROP TABLE IF EXISTS `culvert`;

CREATE TABLE `culvert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `culvert_model` varchar(255) DEFAULT NULL,
  `door_material` varchar(255) DEFAULT NULL,
  `hoist_model` varchar(255) DEFAULT NULL,
  `hole_material` varchar(255) DEFAULT NULL,
  `hole_model` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `length` varchar(255) DEFAULT NULL,
  `section_size` varchar(255) DEFAULT NULL,
  `watercourse_location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table dam
# ------------------------------------------------------------

DROP TABLE IF EXISTS `dam`;

CREATE TABLE `dam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `development` varchar(255) DEFAULT NULL,
  `features` varchar(255) DEFAULT NULL,
  `fee_resources` varchar(255) DEFAULT NULL,
  `is_accountability` varchar(255) DEFAULT NULL,
  `is_certificated` varchar(255) DEFAULT NULL,
  `is_registered` varchar(255) DEFAULT NULL,
  `main_functions` varchar(255) DEFAULT NULL,
  `maintain_person_fee` varchar(255) DEFAULT NULL,
  `manage_rage_line` varchar(255) DEFAULT NULL,
  `pond_dam_manage_rage_line` varchar(255) DEFAULT NULL,
  `protect_ground` varchar(255) DEFAULT NULL,
  `specified_mange` varchar(255) DEFAULT NULL,
  `two_lines_building` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `protect_rage_management` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table deep_wells
# ------------------------------------------------------------

DROP TABLE IF EXISTS `deep_wells`;

CREATE TABLE `deep_wells` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deep_pump` varchar(255) DEFAULT NULL,
  `depth` varchar(255) DEFAULT NULL,
  `diameter` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `irrigate_area` varchar(255) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table drip_irrigation_pipe
# ------------------------------------------------------------

DROP TABLE IF EXISTS `drip_irrigation_pipe`;

CREATE TABLE `drip_irrigation_pipe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `intake_way` varchar(255) DEFAULT NULL,
  `irrigate_area` varchar(255) DEFAULT NULL,
  `sketch` varchar(255) DEFAULT NULL,
  `sum_diameter` varchar(255) DEFAULT NULL,
  `sum_length` varchar(255) DEFAULT NULL,
  `water_resource` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table electric_motor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `electric_motor`;

CREATE TABLE `electric_motor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `factory_date` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `power` varchar(255) DEFAULT NULL,
  `pump_station_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6vaibwox4uoag9quwsu5mb7jn` (`pump_station_id`),
  CONSTRAINT `FK_6vaibwox4uoag9quwsu5mb7jn` FOREIGN KEY (`pump_station_id`) REFERENCES `pump_station` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `electric_motor` WRITE;
/*!40000 ALTER TABLE `electric_motor` DISABLE KEYS */;

INSERT INTO `electric_motor` (`id`, `factory_date`, `model`, `power`, `pump_station_id`)
VALUES
	(1,'','','',1),
	(2,'','','',1),
	(3,'','','',1),
	(4,'','','',2),
	(5,'','','',2),
	(6,'','','',2),
	(7,'','vvb','ccc',3),
	(8,'','ggg','vvvb',3),
	(9,'','cvv','fffcccccc',3);

/*!40000 ALTER TABLE `electric_motor` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table generator
# ------------------------------------------------------------

DROP TABLE IF EXISTS `generator`;

CREATE TABLE `generator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `factory_date` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `power` varchar(255) DEFAULT NULL,
  `hydropower_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cm3crfle1vgom8gnak0pt11o` (`hydropower_id`),
  CONSTRAINT `FK_cm3crfle1vgom8gnak0pt11o` FOREIGN KEY (`hydropower_id`) REFERENCES `hydropower` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table great_wells
# ------------------------------------------------------------

DROP TABLE IF EXISTS `great_wells`;

CREATE TABLE `great_wells` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depth` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `irrigate_area` varchar(255) DEFAULT NULL,
  `model_and_material` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `water_capacity` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `group`;

CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `is_active` int(1) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `county_id` int(11) NOT NULL,
  `town_id` int(11) NOT NULL,
  `village_id` int(11) NOT NULL,
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

INSERT INTO `group` (`id`, `create_time`, `is_delete`, `update_time`, `is_active`, `name`, `county_id`, `town_id`, `village_id`)
VALUES
	(1,'2016-09-08 12:00:00',0,'2016-09-08 12:00:00',1,'无名组',1,1,1),
	(2,'2016-09-08 12:00:00',0,'2016-09-08 12:00:00',1,'什么组',1,2,2);

/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table hydropower
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hydropower`;

CREATE TABLE `hydropower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `affiliation` varchar(255) DEFAULT NULL,
  `annual_fee` varchar(255) DEFAULT NULL,
  `average_capacity` varchar(255) DEFAULT NULL,
  `drainage_area` varchar(255) DEFAULT NULL,
  `drainage_fee` varchar(255) DEFAULT NULL,
  `external_image` varchar(255) DEFAULT NULL,
  `internal_image` varchar(255) DEFAULT NULL,
  `irrigate_area` varchar(255) DEFAULT NULL,
  `irrigate_fee` varchar(255) DEFAULT NULL,
  `machine_area` varchar(255) DEFAULT NULL,
  `paddy_field_area` varchar(255) DEFAULT NULL,
  `problem` varchar(255) DEFAULT NULL,
  `river_location` varchar(255) DEFAULT NULL,
  `sum_electric_capacity` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table investment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `investment`;

CREATE TABLE `investment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `category` int(11) DEFAULT NULL,
  `city_invest` varchar(255) DEFAULT NULL,
  `country_invest` varchar(255) DEFAULT NULL,
  `county_invest` varchar(255) DEFAULT NULL,
  `other_invest` varchar(255) DEFAULT NULL,
  `province_invest` varchar(255) DEFAULT NULL,
  `project_detail_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gdfcspg4lfgw4nbf8pgjyos0u` (`project_detail_id`),
  CONSTRAINT `FK_gdfcspg4lfgw4nbf8pgjyos0u` FOREIGN KEY (`project_detail_id`) REFERENCES `project_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table location
# ------------------------------------------------------------

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `latitude` varchar(20) NOT NULL DEFAULT '',
  `longitude` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;

INSERT INTO `location` (`id`, `create_time`, `is_delete`, `update_time`, `latitude`, `longitude`)
VALUES
	(1,'2016-09-08 11:47:18',0,'2016-09-08 11:47:18','0.00000000','0.00000000'),
	(2,'2016-09-08 11:47:18',0,'2016-09-08 11:47:18','0.00000000','0.00000000'),
	(3,'2016-09-08 11:47:18',0,'2016-09-08 11:47:18','0.00000000','0.00000000'),
	(4,'2016-09-08 11:47:18',0,'2016-09-08 11:47:18','0.00000000','0.00000000'),
	(5,'2016-09-08 11:47:18',0,'2016-09-08 11:47:18','0.00000000','0.00000000'),
	(6,'2016-09-17 20:18:32',0,'2016-09-17 20:18:32','0.00000000','0.00000000'),
	(7,'2016-09-17 20:18:52',0,'2016-09-17 20:18:52','0.00000000','0.00000000'),
	(8,'2016-09-17 20:20:43',0,'2016-09-17 20:20:43','0.00000000','0.00000000'),
	(9,'2016-09-17 20:22:37',0,'2016-09-17 20:22:37','0.00000000','0.00000000'),
	(10,'2016-09-17 20:25:15',0,'2016-09-17 20:25:15','0.00000000','0.00000000'),
	(11,'2016-09-17 20:26:06',0,'2016-09-17 20:26:06','0.00000000','0.00000000'),
	(12,'2016-09-17 20:26:47',0,'2016-09-17 20:26:47','0.00000000','0.00000000'),
	(13,'2016-09-17 21:29:16',0,'2016-09-17 21:29:16','123.02016700','123.02183300'),
	(14,'2016-09-17 21:39:25',0,'2016-09-17 21:39:25','123.020167','123.021833');

/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table notification
# ------------------------------------------------------------

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `category` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `county_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s7cyy7qu30n9q55sa8sc9yorh` (`county_id`),
  KEY `FK_1urdwwsh2ti15ta6f6p5dbdcp` (`user_id`),
  CONSTRAINT `FK_1urdwwsh2ti15ta6f6p5dbdcp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_s7cyy7qu30n9q55sa8sc9yorh` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;

INSERT INTO `notification` (`id`, `create_time`, `is_delete`, `update_time`, `category`, `content`, `title`, `county_id`, `user_id`)
VALUES
	(1,'1970-01-18 09:07:14',0,'1970-01-18 09:07:14',1,'在做梦一样','开学了',NULL,1),
	(2,'1970-01-18 09:07:14',0,'1970-01-18 09:07:14',2,'在做梦一样','开学了',1,1),
	(3,'2016-09-18 09:46:48',0,'2016-09-18 09:46:48',2,'在做梦一样','困的一逼',1,1);

/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pipe
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pipe`;

CREATE TABLE `pipe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `diameter` varchar(255) DEFAULT NULL,
  `length` varchar(255) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `drip_irrigation_pipe_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g1jdspskc5nmtaaist1a3uitt` (`drip_irrigation_pipe_id`),
  CONSTRAINT `FK_g1jdspskc5nmtaaist1a3uitt` FOREIGN KEY (`drip_irrigation_pipe_id`) REFERENCES `drip_irrigation_pipe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table pond
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pond`;

CREATE TABLE `pond` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `last_dredging_time` varchar(255) DEFAULT NULL,
  `main_function` varchar(255) DEFAULT NULL,
  `water_area` varchar(255) DEFAULT NULL,
  `water_capacity` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table project_detail
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_detail`;

CREATE TABLE `project_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `construct_time` varchar(255) DEFAULT NULL,
  `construct_unit` varchar(255) DEFAULT NULL,
  `others` varchar(255) DEFAULT NULL,
  `project_name` varchar(255) NOT NULL,
  `sum_estimate_investment` varchar(255) DEFAULT NULL,
  `sum_fact_investment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table project_mark
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_mark`;

CREATE TABLE `project_mark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aqueduct_id` int(11) DEFAULT NULL,
  `bridge_id` int(11) DEFAULT NULL,
  `channel_id` int(11) DEFAULT NULL,
  `culvert_id` int(11) DEFAULT NULL,
  `dam_id` int(11) DEFAULT NULL,
  `deep_wells_id` int(11) DEFAULT NULL,
  `drip_irrigation_pipe_id` int(11) DEFAULT NULL,
  `great_wells_id` int(11) DEFAULT NULL,
  `hydropower_id` int(11) DEFAULT NULL,
  `pond_id` int(11) DEFAULT NULL,
  `pump_station_id` int(11) DEFAULT NULL,
  `sluice_id` int(11) DEFAULT NULL,
  `water_works_id` int(11) DEFAULT NULL,
  `watercourse_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lva1w3ev1bdlbah1ncmvhvbuj` (`aqueduct_id`),
  KEY `FK_iu1gotlpcxgx5je80njem24h7` (`bridge_id`),
  KEY `FK_mbotktim0c7lobg2u603hw9fw` (`channel_id`),
  KEY `FK_bicrc669e2bhoxddi3oqe6jj` (`culvert_id`),
  KEY `FK_pm6s3yfo228m7lcm798pk6sgj` (`dam_id`),
  KEY `FK_8e6xpqvgn5s5p7dmims27ika` (`deep_wells_id`),
  KEY `FK_jn8nmevf9fuh1f9but3xlmtsa` (`drip_irrigation_pipe_id`),
  KEY `FK_rgv88ghu7px2on9nw4aynhckl` (`great_wells_id`),
  KEY `FK_dmblqyeo4woeq4ktooe5dt6p9` (`hydropower_id`),
  KEY `FK_gu8aftnqm5reiaw5osrpua7il` (`pond_id`),
  KEY `FK_1sgxkm4hhvcyko79opqh9j101` (`pump_station_id`),
  KEY `FK_8gywstnggtyex3ijxar0neq6b` (`sluice_id`),
  KEY `FK_lersfcx97le51sk4yd65s812n` (`water_works_id`),
  KEY `FK_ho5kvgg4ar335vda5jkmw68rp` (`watercourse_id`),
  CONSTRAINT `FK_1sgxkm4hhvcyko79opqh9j101` FOREIGN KEY (`pump_station_id`) REFERENCES `pump_station` (`id`),
  CONSTRAINT `FK_8e6xpqvgn5s5p7dmims27ika` FOREIGN KEY (`deep_wells_id`) REFERENCES `deep_wells` (`id`),
  CONSTRAINT `FK_8gywstnggtyex3ijxar0neq6b` FOREIGN KEY (`sluice_id`) REFERENCES `sluice` (`id`),
  CONSTRAINT `FK_bicrc669e2bhoxddi3oqe6jj` FOREIGN KEY (`culvert_id`) REFERENCES `culvert` (`id`),
  CONSTRAINT `FK_dmblqyeo4woeq4ktooe5dt6p9` FOREIGN KEY (`hydropower_id`) REFERENCES `hydropower` (`id`),
  CONSTRAINT `FK_gu8aftnqm5reiaw5osrpua7il` FOREIGN KEY (`pond_id`) REFERENCES `pond` (`id`),
  CONSTRAINT `FK_ho5kvgg4ar335vda5jkmw68rp` FOREIGN KEY (`watercourse_id`) REFERENCES `watercourse` (`id`),
  CONSTRAINT `FK_iu1gotlpcxgx5je80njem24h7` FOREIGN KEY (`bridge_id`) REFERENCES `bridge` (`id`),
  CONSTRAINT `FK_jn8nmevf9fuh1f9but3xlmtsa` FOREIGN KEY (`drip_irrigation_pipe_id`) REFERENCES `drip_irrigation_pipe` (`id`),
  CONSTRAINT `FK_lersfcx97le51sk4yd65s812n` FOREIGN KEY (`water_works_id`) REFERENCES `waterworks` (`id`),
  CONSTRAINT `FK_lva1w3ev1bdlbah1ncmvhvbuj` FOREIGN KEY (`aqueduct_id`) REFERENCES `aqueduct` (`id`),
  CONSTRAINT `FK_mbotktim0c7lobg2u603hw9fw` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`),
  CONSTRAINT `FK_pm6s3yfo228m7lcm798pk6sgj` FOREIGN KEY (`dam_id`) REFERENCES `dam` (`id`),
  CONSTRAINT `FK_rgv88ghu7px2on9nw4aynhckl` FOREIGN KEY (`great_wells_id`) REFERENCES `great_wells` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `project_mark` WRITE;
/*!40000 ALTER TABLE `project_mark` DISABLE KEYS */;

INSERT INTO `project_mark` (`id`, `aqueduct_id`, `bridge_id`, `channel_id`, `culvert_id`, `dam_id`, `deep_wells_id`, `drip_irrigation_pipe_id`, `great_wells_id`, `hydropower_id`, `pond_id`, `pump_station_id`, `sluice_id`, `water_works_id`, `watercourse_id`)
VALUES
	(1,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(2,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(3,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(4,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL),
	(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),
	(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,NULL);

/*!40000 ALTER TABLE `project_mark` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pump
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pump`;

CREATE TABLE `pump` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` varchar(255) DEFAULT NULL,
  `factory_date` varchar(255) DEFAULT NULL,
  `lift_or_flow` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `pump_station_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7vhfqbslwcix30i0fe1q0jr7x` (`pump_station_id`),
  CONSTRAINT `FK_7vhfqbslwcix30i0fe1q0jr7x` FOREIGN KEY (`pump_station_id`) REFERENCES `pump_station` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `pump` WRITE;
/*!40000 ALTER TABLE `pump` DISABLE KEYS */;

INSERT INTO `pump` (`id`, `count`, `factory_date`, `lift_or_flow`, `model`, `pump_station_id`)
VALUES
	(1,'','','','',1),
	(2,'','','','',1),
	(3,'','','','',1),
	(4,'','','','',2),
	(5,'','','','',2),
	(6,'','','','',2),
	(7,'fgg','','fff','vvbb',3),
	(8,'gg','','fgg','ghhb',3),
	(9,'bbb','','vvvb','bbb',3);

/*!40000 ALTER TABLE `pump` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pump_station
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pump_station`;

CREATE TABLE `pump_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `annual_income` varchar(255) DEFAULT NULL,
  `drainage_area` varchar(255) DEFAULT NULL,
  `drainage_fee` varchar(255) DEFAULT NULL,
  `external_image` varchar(255) DEFAULT NULL,
  `internal_image` varchar(255) DEFAULT NULL,
  `irrigate_area` varchar(255) DEFAULT NULL,
  `irrigate_fee` varchar(255) DEFAULT NULL,
  `machine_area` varchar(255) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  `paddy_field_area` varchar(255) DEFAULT NULL,
  `pool_height` varchar(255) DEFAULT NULL,
  `problem` varchar(255) DEFAULT NULL,
  `river_elevation` varchar(255) DEFAULT NULL,
  `river_location` varchar(255) DEFAULT NULL,
  `total_installed_capacity` varchar(255) DEFAULT NULL,
  `annual_fee` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `pump_station` WRITE;
/*!40000 ALTER TABLE `pump_station` DISABLE KEYS */;

INSERT INTO `pump_station` (`id`, `annual_income`, `drainage_area`, `drainage_fee`, `external_image`, `internal_image`, `irrigate_area`, `irrigate_fee`, `machine_area`, `nature`, `paddy_field_area`, `pool_height`, `problem`, `river_elevation`, `river_location`, `total_installed_capacity`, `annual_fee`)
VALUES
	(1,NULL,'gghb','vbn','/file/download/1474115186573_tempImage.jpg','/file/download/1474115185044_tempImage.jpg','vvb','vbbb','hhj','灌排结合站','vbb','ghj','cbbb','ghh','www','ghh','cvb'),
	(2,NULL,'gghb','vbn','/file/download/1474115223299_tempImage.jpg','/file/download/1474115223151_tempImage.jpg','vvb','vbbb','hhj','灌排结合站','vbb','ghj','cbbb','ghh','www','ghh','cvb'),
	(3,NULL,'','','','/file/download/1474119578452_tempImage.jpg','','','','不限','','','','','www','','');

/*!40000 ALTER TABLE `pump_station` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `is_active` int(1) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`id`, `create_time`, `is_delete`, `update_time`, `is_active`, `name`)
VALUES
	(1,'2016-09-08 12:00:00',0,'2016-09-08 12:00:00',1,'admin'),
	(2,'2016-09-08 12:00:00',0,'2016-09-08 12:00:00',1,'superGuest'),
	(3,'2016-09-08 12:00:00',0,'2016-09-08 12:00:00',1,'guest');

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sluice
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sluice`;

CREATE TABLE `sluice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `building_situation` varchar(255) DEFAULT NULL,
  `door` varchar(255) DEFAULT NULL,
  `door_height` decimal(19,2) DEFAULT NULL,
  `door_situation` varchar(255) DEFAULT NULL,
  `door_width` decimal(19,2) DEFAULT NULL,
  `hoist_model` varchar(255) DEFAULT NULL,
  `hoist_situation` varchar(255) DEFAULT NULL,
  `hoist_tonnage` decimal(19,2) DEFAULT NULL,
  `hole_count` int(11) DEFAULT NULL,
  `hole_height` decimal(19,2) DEFAULT NULL,
  `hole_width` decimal(19,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `watercourse_location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table town
# ------------------------------------------------------------

DROP TABLE IF EXISTS `town`;

CREATE TABLE `town` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `is_active` int(1) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `county_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g1yig1kwhpmbl3m9kkxrcy5w4` (`county_id`),
  CONSTRAINT `FK_g1yig1kwhpmbl3m9kkxrcy5w4` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `town` WRITE;
/*!40000 ALTER TABLE `town` DISABLE KEYS */;

INSERT INTO `town` (`id`, `create_time`, `is_delete`, `update_time`, `is_active`, `name`, `county_id`)
VALUES
	(1,'2016-09-10 00:00:00',0,'2016-09-10 00:00:00',1,'五毛镇',1),
	(2,'2016-09-10 00:00:00',0,'2016-09-10 00:00:00',1,'腊鸡镇',1),
	(3,'2016-09-10 00:00:00',0,'2016-09-10 00:00:00',1,'渣渣镇',1);

/*!40000 ALTER TABLE `town` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table transformer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `transformer`;

CREATE TABLE `transformer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `bydropower_id` int(11) DEFAULT NULL,
  `pump_station_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_eo7456a55j72v40b2xuyiyqwf` (`bydropower_id`),
  KEY `FK_ex5isl473fsqcguixf1bhfj5x` (`pump_station_id`),
  CONSTRAINT `FK_eo7456a55j72v40b2xuyiyqwf` FOREIGN KEY (`bydropower_id`) REFERENCES `hydropower` (`id`),
  CONSTRAINT `FK_ex5isl473fsqcguixf1bhfj5x` FOREIGN KEY (`pump_station_id`) REFERENCES `pump_station` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `transformer` WRITE;
/*!40000 ALTER TABLE `transformer` DISABLE KEYS */;

INSERT INTO `transformer` (`id`, `capacity`, `model`, `bydropower_id`, `pump_station_id`)
VALUES
	(1,'','',NULL,1),
	(2,'','',NULL,1),
	(3,'','',NULL,1),
	(4,'','',NULL,2),
	(5,'','',NULL,2),
	(6,'','',NULL,2),
	(7,'','',NULL,3),
	(8,'','',NULL,3),
	(9,'','',NULL,3);

/*!40000 ALTER TABLE `transformer` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table turbine
# ------------------------------------------------------------

DROP TABLE IF EXISTS `turbine`;

CREATE TABLE `turbine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` varchar(255) DEFAULT NULL,
  `factory_date` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `turns_or_flow` varchar(255) DEFAULT NULL,
  `hydropower_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_meu6k670ustq8lm2w588pi4g5` (`hydropower_id`),
  CONSTRAINT `FK_meu6k670ustq8lm2w588pi4g5` FOREIGN KEY (`hydropower_id`) REFERENCES `hydropower` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gj2fy3dcix7ph7k8684gka40c` (`name`),
  KEY `FK_efsj47nspf3aa5vbtuus01pgr` (`county_id`),
  KEY `FK_qleu8ddawkdltal07p8e6hgva` (`role_id`),
  CONSTRAINT `FK_efsj47nspf3aa5vbtuus01pgr` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`),
  CONSTRAINT `FK_qleu8ddawkdltal07p8e6hgva` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `create_time`, `is_delete`, `update_time`, `email`, `is_active`, `is_super_admin`, `name`, `password`, `phone`, `real_name`, `salt`, `token`, `county_id`, `role_id`)
VALUES
	(1,'2016-09-08 12:00:00',0,'2016-09-18 21:52:45',NULL,1,1,'weck','AOzYfJVv6gCIl4YFpKdt0Fh8MxKGKbdJ6qNFWsYtC1qmsL5LG4nBOfTwa4QQbV8Rg/63dgA849/tLqwEbsGMiOk=',NULL,'weck','HvkAy+OM8xv0qVjW+ZvAl91zJIIn/prIh/qgPVpzlGQ=','f4376359065243bf9baf1cb26b5c5246',1,1);

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
  CONSTRAINT `FK_1imlgmi2e69gkrlw3u16igf7j` FOREIGN KEY (`towns`) REFERENCES `town` (`id`),
  CONSTRAINT `FK_phvv7at2okykcp95r8s4mf9et` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_towns` WRITE;
/*!40000 ALTER TABLE `user_towns` DISABLE KEYS */;

INSERT INTO `user_towns` (`user`, `towns`)
VALUES
	(1,1),
	(1,2);

/*!40000 ALTER TABLE `user_towns` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table village
# ------------------------------------------------------------

DROP TABLE IF EXISTS `village`;

CREATE TABLE `village` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `is_active` int(1) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `county_id` int(11) NOT NULL,
  `town_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_u4ot3j9i9os4tagjmjmfudfh` (`county_id`),
  KEY `FK_7ys24ftdgyvr974qxtqhf1rd7` (`town_id`),
  CONSTRAINT `FK_7ys24ftdgyvr974qxtqhf1rd7` FOREIGN KEY (`town_id`) REFERENCES `town` (`id`),
  CONSTRAINT `FK_u4ot3j9i9os4tagjmjmfudfh` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `village` WRITE;
/*!40000 ALTER TABLE `village` DISABLE KEYS */;

INSERT INTO `village` (`id`, `create_time`, `is_delete`, `update_time`, `is_active`, `name`, `county_id`, `town_id`)
VALUES
	(1,'2016-09-08 12:00:00',0,'2016-09-08 12:00:00',1,'闹鬼村',1,1),
	(2,'2016-09-08 12:00:00',0,'2016-09-08 12:00:00',1,'天村',1,3);

/*!40000 ALTER TABLE `village` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table water_conservation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `water_conservation`;

CREATE TABLE `water_conservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `construct_unit` varchar(255) DEFAULT NULL,
  `manage_model` varchar(255) DEFAULT NULL,
  `manager` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `property_owner` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `situation` varchar(255) DEFAULT NULL,
  `sum_estimate_investment` varchar(255) DEFAULT NULL,
  `sum_fact_investment` varchar(255) DEFAULT NULL,
  `county_id` int(11) NOT NULL,
  `group_id` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `project_mark_id` int(11) NOT NULL,
  `town_id` int(11) NOT NULL,
  `village_id` int(11) NOT NULL,
  `construct_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8ija4wnnyojw5pedcclo6qj01` (`project_mark_id`),
  KEY `FK_8asascecvic8ro4iog12i5tuv` (`county_id`),
  KEY `FK_f4skyo096pwlq2cwows0sv3ox` (`group_id`),
  KEY `FK_ghhn2efvoclpm76uxinjf3iio` (`location_id`),
  KEY `FK_2lfk86onnxtr01wypi82x1rx2` (`town_id`),
  KEY `FK_loiaql631ustvbyak2svah5ji` (`village_id`),
  CONSTRAINT `FK_2lfk86onnxtr01wypi82x1rx2` FOREIGN KEY (`town_id`) REFERENCES `town` (`id`),
  CONSTRAINT `FK_8asascecvic8ro4iog12i5tuv` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`),
  CONSTRAINT `FK_8ija4wnnyojw5pedcclo6qj01` FOREIGN KEY (`project_mark_id`) REFERENCES `project_mark` (`id`),
  CONSTRAINT `FK_f4skyo096pwlq2cwows0sv3ox` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`),
  CONSTRAINT `FK_ghhn2efvoclpm76uxinjf3iio` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `FK_loiaql631ustvbyak2svah5ji` FOREIGN KEY (`village_id`) REFERENCES `village` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `water_conservation` WRITE;
/*!40000 ALTER TABLE `water_conservation` DISABLE KEYS */;

INSERT INTO `water_conservation` (`id`, `create_time`, `is_delete`, `update_time`, `category`, `code`, `construct_unit`, `manage_model`, `manager`, `name`, `property_owner`, `remark`, `situation`, `sum_estimate_investment`, `sum_fact_investment`, `county_id`, `group_id`, `location_id`, `project_mark_id`, `town_id`, `village_id`, `construct_time`)
VALUES
	(1,'2016-09-08 12:08:23',0,'2016-09-08 12:08:23','渡槽','abcd','','自管','','什么鬼渡槽','','','破损',NULL,NULL,1,2,2,1,3,2,NULL),
	(2,'2016-09-08 14:25:05',0,'2016-09-08 14:25:05','渡槽','abcd','','自管','','什么鬼渡槽','','','破损',NULL,NULL,1,1,3,2,1,1,NULL),
	(3,'2016-09-08 14:26:06',0,'2016-09-08 14:26:06','渡槽','abcd','','自管','','什么鬼渡槽','','','完好',NULL,NULL,1,2,4,3,3,2,NULL),
	(4,'2016-09-08 21:28:47',0,'2016-09-08 21:28:47','渡槽','abcd','','自管','','什么鬼渡槽','','','完好',NULL,NULL,1,1,5,4,1,1,NULL),
	(5,'2016-09-17 20:26:22',0,'2016-09-17 20:26:22','泵站','ssssssvbb','vbb','委托管理','ghh','ghh','vbb','www','cbbb',NULL,NULL,1,1,11,5,1,1,NULL),
	(6,'2016-09-17 20:27:03',0,'2016-09-17 20:27:03','泵站','ssssssvbb','vbb','委托管理','ghh','ghh','vbb','www','cbbb',NULL,NULL,1,1,12,6,1,1,NULL),
	(7,'2016-09-17 21:39:38',0,'2016-09-17 21:39:38','泵站','ssssss','','不限','','','','www','完好',NULL,NULL,1,1,14,7,1,1,NULL);

/*!40000 ALTER TABLE `water_conservation` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table watercourse
# ------------------------------------------------------------

DROP TABLE IF EXISTS `watercourse`;

CREATE TABLE `watercourse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_image` varchar(255) DEFAULT NULL,
  `estuary_height` varchar(255) DEFAULT NULL,
  `estuary_width` varchar(255) DEFAULT NULL,
  `flow_villages` varchar(255) DEFAULT NULL,
  `hedi_height` varchar(255) DEFAULT NULL,
  `hedi_width` varchar(255) DEFAULT NULL,
  `last_dredging_time` varchar(255) DEFAULT NULL,
  `left_width` varchar(255) DEFAULT NULL,
  `length` varchar(255) DEFAULT NULL,
  `middle_image` varchar(255) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  `right_width` varchar(255) DEFAULT NULL,
  `section_image` varchar(255) DEFAULT NULL,
  `start_image` varchar(255) DEFAULT NULL,
  `endpoint_location_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cuco7mn88nagylmy9kfkiqi14` (`endpoint_location_id`),
  CONSTRAINT `FK_cuco7mn88nagylmy9kfkiqi14` FOREIGN KEY (`endpoint_location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table waterworks
# ------------------------------------------------------------

DROP TABLE IF EXISTS `waterworks`;

CREATE TABLE `waterworks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day_provide_amount` varchar(255) DEFAULT NULL,
  `have_cleaner` varchar(255) DEFAULT NULL,
  `have_protect_area` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_regular_check` varchar(255) DEFAULT NULL,
  `provide_amount` varchar(255) DEFAULT NULL,
  `provide_population` varchar(255) DEFAULT NULL,
  `provide_village_count` varchar(255) DEFAULT NULL,
  `water_model` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
