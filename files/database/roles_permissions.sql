USE `test`;

DROP TABLE IF EXISTS `roles_permissions`;

CREATE TABLE `roles_permissions` (
  `id`            bigint(32) NOT NULL AUTO_INCREMENT,
  `role_id`       bigint(32) NOT NULL
  COMMENT '角色id',
  `permission_id` bigint(32) NOT NULL
  COMMENT '权限id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin
  COMMENT ='角色权限表';

INSERT INTO roles_permissions (id, role_id, permission_id) VALUES (1, 1, 1);
INSERT INTO roles_permissions (id, role_id, permission_id) VALUES (2, 2, 2);