# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.13)
# Database: exciting
# Generation Time: 2016-09-04 12:48:10 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table access
# ------------------------------------------------------------

DROP TABLE IF EXISTS `access`;

CREATE TABLE `access` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `county_id` int(11) NOT NULL,
  `town_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qp1mrvsw50wb4pqrn3i25mcj6` (`county_id`),
  UNIQUE KEY `UK_k3g658i4pjcheiup2er0kbxk9` (`town_id`),
  UNIQUE KEY `UK_psdvmdgqlkp7w1rinu8xbeqcw` (`user_id`),
  CONSTRAINT `FK_k3g658i4pjcheiup2er0kbxk9` FOREIGN KEY (`town_id`) REFERENCES `town` (`id`),
  CONSTRAINT `FK_psdvmdgqlkp7w1rinu8xbeqcw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_qp1mrvsw50wb4pqrn3i25mcj6` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table approval
# ------------------------------------------------------------

DROP TABLE IF EXISTS `approval`;

CREATE TABLE `approval` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `approval_symbol` varchar(255) NOT NULL,
  `approval_time` datetime NOT NULL,
  `file` varchar(255) DEFAULT NULL,
  `grade` int(11) NOT NULL,
  `grade_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table aqueduct
# ------------------------------------------------------------

DROP TABLE IF EXISTS `aqueduct`;

CREATE TABLE `aqueduct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cross_count` int(11) DEFAULT NULL,
  `cross_length` decimal(19,2) DEFAULT NULL,
  `cross_watercourse_location` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `section_size` decimal(19,2) DEFAULT NULL,
  `structure_and_material` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table bridge
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bridge`;

CREATE TABLE `bridge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `beam_material` varchar(255) DEFAULT NULL,
  `beam_structure` varchar(255) DEFAULT NULL,
  `cross_count` int(11) DEFAULT NULL,
  `cross_length` decimal(19,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `length` decimal(19,2) DEFAULT NULL,
  `load_standard` decimal(19,2) DEFAULT NULL,
  `situation` varchar(255) DEFAULT NULL,
  `watercourse_location` varchar(255) DEFAULT NULL,
  `width` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table canal
# ------------------------------------------------------------

DROP TABLE IF EXISTS `canal`;

CREATE TABLE `canal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `length` decimal(19,2) DEFAULT NULL,
  `lining_material` varchar(255) DEFAULT NULL,
  `lining_section_size` decimal(19,2) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `project_code` varchar(255) NOT NULL,
  `project_id` int(11) NOT NULL,
  `section_size` decimal(19,2) DEFAULT NULL,
  `seepage_canal_length` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table channel
# ------------------------------------------------------------

DROP TABLE IF EXISTS `channel`;

CREATE TABLE `channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `building_match_rate` decimal(19,2) DEFAULT NULL,
  `good_condition_rate` decimal(19,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `pump_station` varchar(255) DEFAULT NULL,
  `sum_length` decimal(19,2) DEFAULT NULL,
  `sum_lining_section_size` decimal(19,2) DEFAULT NULL,
  `sum_section_size` decimal(19,2) DEFAULT NULL,
  `sum_seepage_canal_length` decimal(19,2) DEFAULT NULL,
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
  `check_time` datetime NOT NULL,
  `file` varchar(255) DEFAULT NULL,
  `grade` int(11) NOT NULL,
  `grade_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table construct
# ------------------------------------------------------------

DROP TABLE IF EXISTS `construct`;

CREATE TABLE `construct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `construct_time` datetime DEFAULT NULL,
  `construct_unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table county
# ------------------------------------------------------------

DROP TABLE IF EXISTS `county`;

CREATE TABLE `county` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `is_active` int(11) NOT NULL,
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
	(1,'2016-08-15 18:37:11',0,'2016-08-15 18:37:11',1,'nankang',NULL);

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
  `length` decimal(19,2) DEFAULT NULL,
  `section_height` decimal(19,2) DEFAULT NULL,
  `section_width` decimal(19,2) DEFAULT NULL,
  `watercourse_location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table dam
# ------------------------------------------------------------

DROP TABLE IF EXISTS `dam`;

CREATE TABLE `dam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountability_is_build` int(11) DEFAULT NULL,
  `capacity` decimal(19,2) DEFAULT NULL,
  `crest_elevation` decimal(19,2) DEFAULT NULL,
  `dam_explanation` varchar(255) DEFAULT NULL,
  `dam_rage_qualified` int(11) DEFAULT NULL,
  `develop_explanation` int(11) DEFAULT NULL,
  `factory_building_area` decimal(19,2) DEFAULT NULL,
  `features` varchar(255) DEFAULT NULL,
  `have_buildings` int(11) DEFAULT NULL,
  `have_certificate` int(11) DEFAULT NULL,
  `have_development` int(11) DEFAULT NULL,
  `have_funding` int(11) DEFAULT NULL,
  `have_maintain_person` int(11) DEFAULT NULL,
  `height` decimal(19,2) DEFAULT NULL,
  `houses_area` decimal(19,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_registered` int(11) DEFAULT NULL,
  `length` decimal(19,2) DEFAULT NULL,
  `maintain_situation` varchar(255) DEFAULT NULL,
  `manage_qualified` int(11) DEFAULT NULL,
  `manage_situation` varchar(255) DEFAULT NULL,
  `other_area` decimal(19,2) DEFAULT NULL,
  `pond_dam_explanation` varchar(255) DEFAULT NULL,
  `pond_dam_rage_qulified` int(11) DEFAULT NULL,
  `retaining_dam` int(11) DEFAULT NULL,
  `retaining_length` decimal(19,2) DEFAULT NULL,
  `retaining_width` decimal(19,2) DEFAULT NULL,
  `seal_manage` int(11) DEFAULT NULL,
  `village_road_area` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table deep_wells
# ------------------------------------------------------------

DROP TABLE IF EXISTS `deep_wells`;

CREATE TABLE `deep_wells` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deep_pump` varchar(255) DEFAULT NULL,
  `depth` decimal(19,2) DEFAULT NULL,
  `diameter` decimal(19,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `irrigate_area` decimal(19,2) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table drip_irrigation_pipe
# ------------------------------------------------------------

DROP TABLE IF EXISTS `drip_irrigation_pipe`;

CREATE TABLE `drip_irrigation_pipe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iamge` varchar(255) DEFAULT NULL,
  `intake_way` varchar(255) DEFAULT NULL,
  `irrigate_area` decimal(19,2) DEFAULT NULL,
  `sketch` varchar(255) DEFAULT NULL,
  `sum_diameter` decimal(19,2) DEFAULT NULL,
  `sum_length` decimal(19,2) DEFAULT NULL,
  `water_resource` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table electric_motor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `electric_motor`;

CREATE TABLE `electric_motor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `factory_time` datetime DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `power` decimal(19,2) DEFAULT NULL,
  `project_code` varchar(255) NOT NULL,
  `project_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table generator
# ------------------------------------------------------------

DROP TABLE IF EXISTS `generator`;

CREATE TABLE `generator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `factory_time` datetime DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `power` decimal(19,2) DEFAULT NULL,
  `project_code` varchar(255) NOT NULL,
  `project_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table great_wells
# ------------------------------------------------------------

DROP TABLE IF EXISTS `great_wells`;

CREATE TABLE `great_wells` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depth` decimal(19,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `irrigate_area` decimal(19,2) DEFAULT NULL,
  `material` decimal(19,2) DEFAULT NULL,
  `size` decimal(19,2) DEFAULT NULL,
  `water_capacity` decimal(19,2) DEFAULT NULL,
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
  `is_active` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `location_id` int(11) DEFAULT NULL,
  `village_id` int(11) NOT NULL,
  `county_id` int(11) NOT NULL,
  `town_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fyv8x469enc9r1xioqrjh42oo` (`village_id`),
  KEY `FK_hq9697mb1c858ldr18m9m56mh` (`location_id`),
  KEY `FK_k2co1luo4wqd4n8lrc0iqo1xw` (`county_id`),
  KEY `FK_2x2dtya37gti9erqbme4p5mcb` (`town_id`),
  CONSTRAINT `FK_2x2dtya37gti9erqbme4p5mcb` FOREIGN KEY (`town_id`) REFERENCES `town` (`id`),
  CONSTRAINT `FK_fyv8x469enc9r1xioqrjh42oo` FOREIGN KEY (`village_id`) REFERENCES `village` (`id`),
  CONSTRAINT `FK_hq9697mb1c858ldr18m9m56mh` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `FK_k2co1luo4wqd4n8lrc0iqo1xw` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table hydropower
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hydropower`;

CREATE TABLE `hydropower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `affiliation` varchar(255) DEFAULT NULL,
  `annual_income` varchar(255) DEFAULT NULL,
  `average_capacity` decimal(19,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `drainage_area` decimal(19,2) DEFAULT NULL,
  `drainage_fee` varchar(255) DEFAULT NULL,
  `external_image` varchar(255) DEFAULT NULL,
  `internal_image` varchar(255) DEFAULT NULL,
  `irrigate_area` decimal(19,2) DEFAULT NULL,
  `irrigate_fee` varchar(255) DEFAULT NULL,
  `machine_area` decimal(19,2) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  `paddy_field_area` decimal(19,2) DEFAULT NULL,
  `pool_height` decimal(19,2) DEFAULT NULL,
  `river_elevation` decimal(19,2) DEFAULT NULL,
  `running_situation` varchar(255) DEFAULT NULL,
  `sum_electric_capacity` decimal(19,2) DEFAULT NULL,
  `total_installed_capacity` decimal(19,2) DEFAULT NULL,
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
  `category_name` varchar(255) DEFAULT NULL,
  `city_invest` varchar(255) DEFAULT NULL,
  `country_invest` varchar(255) DEFAULT NULL,
  `county_invest` varchar(255) DEFAULT NULL,
  `other_invest` varchar(255) DEFAULT NULL,
  `province_invest` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table location
# ------------------------------------------------------------

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `latitude` decimal(13,8) NOT NULL,
  `longitude` decimal(13,8) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table manager
# ------------------------------------------------------------

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `contacts` varchar(255) DEFAULT NULL,
  `manager` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



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
	(1,'2016-08-15 18:37:11',0,'2016-09-03 01:01:15',2,'哦是吗','夜深啦',1,1),
	(2,'2016-08-15 18:37:11',0,'2016-08-15 18:37:11',1,'网站公告','啊呀',NULL,1),
	(3,'1970-01-18 09:07:14',0,'2016-09-03 01:01:15',1,'红红火火恍恍惚惚哈哈哈','收工啦',NULL,1),
	(4,'1970-01-18 09:07:14',0,'1970-01-18 09:07:14',1,'在做梦一样','开学了',NULL,1),
	(5,'1970-01-18 09:07:14',1,'2016-09-03 01:01:15',1,'在做梦一样','开学了',NULL,1);

/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pipe
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pipe`;

CREATE TABLE `pipe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `diameter` decimal(19,2) DEFAULT NULL,
  `length` decimal(19,2) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `project_code` varchar(255) NOT NULL,
  `project_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table pond
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pond`;

CREATE TABLE `pond` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `features` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_dredging_time` datetime DEFAULT NULL,
  `water_area` decimal(19,2) DEFAULT NULL,
  `water_capacity` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table project_category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_category`;

CREATE TABLE `project_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `project_category` WRITE;
/*!40000 ALTER TABLE `project_category` DISABLE KEYS */;

INSERT INTO `project_category` (`id`, `create_time`, `is_delete`, `update_time`, `name`)
VALUES
	(1,'2016-08-15 18:37:12',0,'2016-08-15 18:37:11','泵站'),
	(2,'2016-08-15 18:37:11',0,'2016-08-15 18:37:11','渡槽'),
	(3,'2016-08-15 18:37:11',0,'2016-08-15 18:37:11','河道');

/*!40000 ALTER TABLE `project_category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project_detail_mark
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_detail_mark`;

CREATE TABLE `project_detail_mark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `approval_id` int(11) DEFAULT NULL,
  `check_id` int(11) DEFAULT NULL,
  `investment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o87xn0ssanrvlptulaxkekpwm` (`approval_id`),
  KEY `FK_j53g6kjw78a2o1huwn8nenx94` (`check_id`),
  KEY `FK_3sbt9g2q0b76v1vmimrettt66` (`investment_id`),
  CONSTRAINT `FK_3sbt9g2q0b76v1vmimrettt66` FOREIGN KEY (`investment_id`) REFERENCES `investment` (`id`),
  CONSTRAINT `FK_j53g6kjw78a2o1huwn8nenx94` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`),
  CONSTRAINT `FK_o87xn0ssanrvlptulaxkekpwm` FOREIGN KEY (`approval_id`) REFERENCES `approval` (`id`)
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



# Dump of table property_owner
# ------------------------------------------------------------

DROP TABLE IF EXISTS `property_owner`;

CREATE TABLE `property_owner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `contacts` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table pump
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pump`;

CREATE TABLE `pump` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `factory_time` datetime DEFAULT NULL,
  `flow` decimal(19,2) DEFAULT NULL,
  `lift` decimal(19,2) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `project_code` varchar(255) NOT NULL,
  `project_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table pump_station
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pump_station`;

CREATE TABLE `pump_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `annual_income` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `drainage_area` decimal(19,2) DEFAULT NULL,
  `drainage_fee` varchar(255) DEFAULT NULL,
  `external_image` varchar(255) DEFAULT NULL,
  `internal_image` varchar(255) DEFAULT NULL,
  `irrigate_area` decimal(19,2) DEFAULT NULL,
  `irrigate_fee` varchar(255) DEFAULT NULL,
  `machine_area` decimal(19,2) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  `paddy_field_area` decimal(19,2) DEFAULT NULL,
  `pool_height` decimal(19,2) DEFAULT NULL,
  `river_elevation` decimal(19,2) DEFAULT NULL,
  `running_situation` varchar(255) DEFAULT NULL,
  `total_installed_capacity` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



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
	(1,'2016-08-15 18:37:11',0,'2016-08-15 18:37:11',1,'admin'),
	(2,'2016-08-15 18:37:11',0,'2016-08-15 18:37:11',1,'guest'),
	(3,'2016-08-15 18:37:11',0,'2016-08-15 18:37:11',1,'superAdmin');

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
  `is_active` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `county_id` int(11) NOT NULL,
  `location_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g1yig1kwhpmbl3m9kkxrcy5w4` (`county_id`),
  KEY `FK_d8vhmittimfgmu849939tuc5q` (`location_id`),
  CONSTRAINT `FK_d8vhmittimfgmu849939tuc5q` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `FK_g1yig1kwhpmbl3m9kkxrcy5w4` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table transformer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `transformer`;

CREATE TABLE `transformer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity` decimal(19,2) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `project_code` varchar(255) NOT NULL,
  `project_id` int(11) NOT NULL,
  `annual_pay` decimal(19,2) DEFAULT NULL,
  `years` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table turbine
# ------------------------------------------------------------

DROP TABLE IF EXISTS `turbine`;

CREATE TABLE `turbine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` varchar(255) DEFAULT NULL,
  `factory_time` datetime DEFAULT NULL,
  `flow` decimal(19,2) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `project_code` varchar(255) NOT NULL,
  `project_id` int(11) NOT NULL,
  `turns` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
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
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `county_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gj2fy3dcix7ph7k8684gka40c` (`name`),
  KEY `FK_efsj47nspf3aa5vbtuus01pgr` (`county_id`),
  CONSTRAINT `FK_efsj47nspf3aa5vbtuus01pgr` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `create_time`, `is_delete`, `update_time`, `email`, `is_active`, `name`, `password`, `phone`, `real_name`, `salt`, `token`, `county_id`)
VALUES
	(1,'2016-08-15 18:37:11',0,'2016-09-04 14:03:25',NULL,1,'weck','AJakUTAHq+04GQOEqBCR/Zz8sFAQyX1u1iL4GRBE5qd4YFEc+A7dB9hUuWpZHm/l3vjicEVb4s5zJd9EjmPDRxM=',NULL,'weck','ADftHJvI/VIGgUIlTER7SqAvDvCazQopk2oELqgj0Ao=','d1421ca624fe49eaaa7d155aa6c56361',1);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user` int(11) NOT NULL,
  `roles` int(11) NOT NULL,
  KEY `FK_7mjv136svoj4cwc81kdvg60uu` (`roles`),
  KEY `FK_oanch5nixy8aa46y2jexkvo5w` (`user`),
  CONSTRAINT `FK_7mjv136svoj4cwc81kdvg60uu` FOREIGN KEY (`roles`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_oanch5nixy8aa46y2jexkvo5w` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;

INSERT INTO `user_roles` (`user`, `roles`)
VALUES
	(1,1),
	(1,2),
	(1,3);

/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table village
# ------------------------------------------------------------

DROP TABLE IF EXISTS `village`;

CREATE TABLE `village` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `is_active` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `location_id` int(11) DEFAULT NULL,
  `town_id` int(11) NOT NULL,
  `county_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7ys24ftdgyvr974qxtqhf1rd7` (`town_id`),
  KEY `FK_slhsmscg2jlna2dqo69wp1kej` (`location_id`),
  KEY `FK_u4ot3j9i9os4tagjmjmfudfh` (`county_id`),
  CONSTRAINT `FK_7ys24ftdgyvr974qxtqhf1rd7` FOREIGN KEY (`town_id`) REFERENCES `town` (`id`),
  CONSTRAINT `FK_slhsmscg2jlna2dqo69wp1kej` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `FK_u4ot3j9i9os4tagjmjmfudfh` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table water_conservation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `water_conservation`;

CREATE TABLE `water_conservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `construct_id` int(11) DEFAULT NULL,
  `county_id` int(11) NOT NULL,
  `group_id` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL,
  `project_category_id` int(11) NOT NULL,
  `project_detail_mark_id` int(11) DEFAULT NULL,
  `project_mark_id` int(11) NOT NULL,
  `property_owner_id` int(11) DEFAULT NULL,
  `town_id` int(11) NOT NULL,
  `village_id` int(11) NOT NULL,
  `manage_model` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8ija4wnnyojw5pedcclo6qj01` (`project_mark_id`),
  KEY `FK_dr3s5s3dgc53psynv4phv99d0` (`construct_id`),
  KEY `FK_8asascecvic8ro4iog12i5tuv` (`county_id`),
  KEY `FK_f4skyo096pwlq2cwows0sv3ox` (`group_id`),
  KEY `FK_t6c6n35e9730mftugeong0535` (`manager_id`),
  KEY `FK_r7ht8bnk54j09t7c1aksya252` (`project_category_id`),
  KEY `FK_7wj5ypon2qa4l5s6mm5d76fdt` (`project_detail_mark_id`),
  KEY `FK_2awr9eld0cd8llw1gn74t3im7` (`property_owner_id`),
  KEY `FK_2lfk86onnxtr01wypi82x1rx2` (`town_id`),
  KEY `FK_loiaql631ustvbyak2svah5ji` (`village_id`),
  KEY `FK_ghhn2efvoclpm76uxinjf3iio` (`location_id`),
  CONSTRAINT `FK_2awr9eld0cd8llw1gn74t3im7` FOREIGN KEY (`property_owner_id`) REFERENCES `property_owner` (`id`),
  CONSTRAINT `FK_2lfk86onnxtr01wypi82x1rx2` FOREIGN KEY (`town_id`) REFERENCES `town` (`id`),
  CONSTRAINT `FK_7wj5ypon2qa4l5s6mm5d76fdt` FOREIGN KEY (`project_detail_mark_id`) REFERENCES `project_detail_mark` (`id`),
  CONSTRAINT `FK_8asascecvic8ro4iog12i5tuv` FOREIGN KEY (`county_id`) REFERENCES `county` (`id`),
  CONSTRAINT `FK_8ija4wnnyojw5pedcclo6qj01` FOREIGN KEY (`project_mark_id`) REFERENCES `project_mark` (`id`),
  CONSTRAINT `FK_dr3s5s3dgc53psynv4phv99d0` FOREIGN KEY (`construct_id`) REFERENCES `construct` (`id`),
  CONSTRAINT `FK_f4skyo096pwlq2cwows0sv3ox` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`),
  CONSTRAINT `FK_ghhn2efvoclpm76uxinjf3iio` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `FK_loiaql631ustvbyak2svah5ji` FOREIGN KEY (`village_id`) REFERENCES `village` (`id`),
  CONSTRAINT `FK_r7ht8bnk54j09t7c1aksya252` FOREIGN KEY (`project_category_id`) REFERENCES `project_category` (`id`),
  CONSTRAINT `FK_t6c6n35e9730mftugeong0535` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table watercourse
# ------------------------------------------------------------

DROP TABLE IF EXISTS `watercourse`;

CREATE TABLE `watercourse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_image` varchar(255) DEFAULT NULL,
  `endpoint_latitude` varchar(255) DEFAULT NULL,
  `endpoint_longitude` varchar(255) DEFAULT NULL,
  `estuary_height` decimal(19,2) DEFAULT NULL,
  `estuary_width` decimal(19,2) DEFAULT NULL,
  `flow_villages` varchar(255) DEFAULT NULL,
  `hedi_height` decimal(19,2) DEFAULT NULL,
  `hedi_width` decimal(19,2) DEFAULT NULL,
  `last_dredging_time` datetime DEFAULT NULL,
  `left_width` decimal(19,2) DEFAULT NULL,
  `length` decimal(19,2) DEFAULT NULL,
  `middle_image` varchar(255) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  `right_width` decimal(19,2) DEFAULT NULL,
  `section_image` varchar(255) DEFAULT NULL,
  `start_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table waterworks
# ------------------------------------------------------------

DROP TABLE IF EXISTS `waterworks`;

CREATE TABLE `waterworks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day_provide_amount` decimal(19,2) DEFAULT NULL,
  `have_cleaner` int(11) DEFAULT NULL,
  `have_protect_area` int(11) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_regular_check` int(11) DEFAULT NULL,
  `provide_amount` decimal(19,2) DEFAULT NULL,
  `provide_population` int(11) DEFAULT NULL,
  `provide_village_count` int(11) DEFAULT NULL,
  `water_model` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
