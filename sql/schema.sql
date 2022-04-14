CREATE TABLE `user` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `user_name` varchar(32) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  `real_name` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;