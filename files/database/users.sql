USE `test`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id`       bigint(20)                    NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin  NOT NULL
  COMMENT '用户名',
  `password` varchar(100) COLLATE utf8_bin NOT NULL
  COMMENT '密码',
  `nickname` varchar(50) COLLATE utf8_bin           DEFAULT NULL
  COMMENT '昵称',
  `gender`   varchar(2) COLLATE utf8_bin            DEFAULT '03'
  COMMENT '性别：01-男；02-女；03-其他',
  `file_id`  bigint(20)                             DEFAULT NULL
  COMMENT '头像图片id',
  `group_id` bigint(20)                    NOT NULL
  COMMENT '所属群组id',
  PRIMARY KEY (`id`),
  KEY `users_id_username_index` (`id`, `username`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;