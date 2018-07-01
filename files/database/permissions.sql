USE `test`;

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `id`          bigint(32)                                 NOT NULL AUTO_INCREMENT,
  `name`        varchar(32)                                NOT NULL
  COMMENT '权限名称',
  `description` varchar(32)                                NOT NULL
  COMMENT '权限描述',
  `operation`   enum ('read', 'write', 'update', 'delete') NOT NULL
  COMMENT '操作方式',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='权限表';

INSERT INTO permissions (id, name, description, operation) VALUES (1, 'admin_test', '管理员测试', 'read');
INSERT INTO permissions (id, name, description, operation) VALUES (2, 'user_test', '用户测试', 'read');