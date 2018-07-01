USE `test`;

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `id`      bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) NOT NULL
  COMMENT '用户id',
  `role_id` bigint(32) NOT NULL
  COMMENT '角色id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin
  COMMENT ='用户角色表';

INSERT INTO users_roles (id, user_id, role_id) VALUES (1, 1, 1);
INSERT INTO users_roles (id, user_id, role_id) VALUES (2, 2, 2);