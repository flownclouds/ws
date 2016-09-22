INSERT INTO `county` (`id`, `create_time`, `is_delete`, `update_time`, `is_active`, `name`, `location_id`)
VALUES
	(1, '2016-08-15 18:37:11', 0, '2016-08-15 18:37:11', 1, 'nankang', NULL);

INSERT INTO `role` (`id`, `create_time`, `is_delete`, `update_time`, `is_active`, `name`)
VALUES
	(1, '2016-08-15 18:37:11', 0, '2016-08-15 18:37:11', 1, 'admin'),
	(2, '2016-08-15 18:37:11', 0, '2016-08-15 18:37:11', 1, 'guest');


INSERT INTO `user` (`id`, `create_time`, `is_delete`, `update_time`, `email`, `is_active`, `name`, `password`, `phone`, `real_name`, `salt`, `token`, `county_id`)
VALUES
	(1, '2016-08-15 18:37:11', 0, '2016-09-03 20:50:51', NULL, 1, 'weck', 'AJakUTAHq+04GQOEqBCR/Zz8sFAQyX1u1iL4GRBE5qd4YFEc+A7dB9hUuWpZHm/l3vjicEVb4s5zJd9EjmPDRxM=', NULL, 'weck', 'ADftHJvI/VIGgUIlTER7SqAvDvCazQopk2oELqgj0Ao=', '34afe03e8afe4932b384a4a1c07fd4c4', 1);


INSERT INTO `user_roles` (`user`, `roles`)
VALUES
	(1, 1),
	(1, 2);


INSERT INTO `notification` (`id`, `create_time`, `is_delete`, `update_time`, `category`, `content`, `title`, `county_id`, `user_id`)
VALUES
	(1, '2016-08-15 18:37:11', 0, '2016-09-03 01:01:15', 2, '哦是吗', '夜深啦', 1, 1),
	(2, '2016-08-15 18:37:11', 0, '2016-08-15 18:37:11', 1, '网站公告', '啊呀', NULL, 1),
	(3, '1970-01-18 09:07:14', 0, '2016-09-03 01:01:15', 1, '红红火火恍恍惚惚哈哈哈', '收工啦', NULL, 1);

INSERT INTO `project_category` (`id`, `create_time`, `is_delete`, `update_time`, `name`)
VALUES
	(1, '2016-08-15 18:37:12', 0, '2016-08-15 18:37:11', '泵站'),
	(2, '2016-08-15 18:37:11', 0, '2016-08-15 18:37:11', '渡槽'),
	(3, '2016-08-15 18:37:11', 0, '2016-08-15 18:37:11', '河道');

