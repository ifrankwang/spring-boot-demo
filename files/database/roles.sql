USE `test`;

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id`          bigint(32)                   NOT NULL AUTO_INCREMENT,
  `name`        varchar(32) COLLATE utf8_bin NOT NULL
  COMMENT '角色名称',
  `description` varchar(32) COLLATE utf8_bin NOT NULL
  COMMENT '角色描述',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin
  COMMENT ='角色表';

INSERT INTO roles (id, name, description) VALUES (1, 'admin', '管理员');
INSERT INTO roles (id, name, description) VALUES (2, 'user', '用户');