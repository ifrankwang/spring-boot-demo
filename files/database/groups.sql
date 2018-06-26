USE `test`;

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `id`   bigint(20)  NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL
  COMMENT '组织名称',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  COMMENT ='组织表';

INSERT INTO groups (id, name) VALUES (1, 'ADMIN');
INSERT INTO groups (id, name) VALUES (2, 'COMMON_USER');