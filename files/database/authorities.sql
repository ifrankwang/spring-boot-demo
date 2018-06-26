USE `test`;

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `id`        bigint(20)  NOT NULL AUTO_INCREMENT,
  `authority` varchar(32) NOT NULL
  COMMENT '权限名称',
  `group_id`  bigint(20)  NOT NULL
  COMMENT '群组id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='权限表';