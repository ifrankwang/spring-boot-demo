/*
 Navicat Premium Data Transfer

 Source Server         : LOCAL
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 12/03/2019 17:22:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`
(
  `id`           bigint(32) NOT NULL AUTO_INCREMENT,
  `resource_id`  bigint(32) NOT NULL COMMENT '资源id',
  `operation_id` int(16)    NOT NULL COMMENT '操作id',
  `create_time`  datetime   NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='权限表';

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation`
(
  `id`           int(11)                       NOT NULL AUTO_INCREMENT,
  `name`         varchar(255) COLLATE utf8_bin NOT NULL COMMENT '名称（中文）',
  `tag`          varchar(255) COLLATE utf8_bin NOT NULL COMMENT '名称（英文）',
  `access_level` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT 'PRIVATE' COMMENT '适用范围：PRIVATE-自己创建的；PROTECTED-团队内的；PUBLIC-所有的',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='权限操作表';

-- ----------------------------
-- Records of operation
-- ----------------------------
BEGIN;
INSERT INTO `operation`
VALUES (4, '删除', 'DELETE', 'PRIVATE');
COMMIT;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`
(
  `id`          bigint(32)                    NOT NULL AUTO_INCREMENT,
  `name`        varchar(255) COLLATE utf8_bin NOT NULL COMMENT '名称（中文）',
  `tag`         varchar(255) COLLATE utf8_bin NOT NULL COMMENT '标签（英文）',
  `create_time` datetime                      NOT NULL COMMENT '创建时间',
  `parent_id`   bigint(32) DEFAULT NULL COMMENT '亲代id',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='权限资源表';

-- ----------------------------
-- Table structure for resource_operation
-- ----------------------------
DROP TABLE IF EXISTS `resource_operation`;
CREATE TABLE `resource_operation`
(
  `id`           bigint(32) NOT NULL AUTO_INCREMENT,
  `resource_id`  bigint(32) NOT NULL COMMENT '权限资源id',
  `operation_id` int(16)    NOT NULL COMMENT '权限操作id',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='权限资源可选操作表';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
  `id`          bigint(32)                    NOT NULL AUTO_INCREMENT,
  `name`        varchar(255) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `generic`     tinyint(1)                    NOT NULL DEFAULT '1' COMMENT '是否为非业务类角色',
  `create_time` datetime                      NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='角色表';

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority`
(
  `id`           bigint(32) NOT NULL AUTO_INCREMENT,
  `role_id`      bigint(32) NOT NULL COMMENT '角色id',
  `authority_id` bigint(32) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='角色权限表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
  `id`          bigint(32)                    NOT NULL AUTO_INCREMENT,
  `name`        varchar(255) COLLATE utf8_bin NOT NULL COMMENT '展示名称',
  `email`       varchar(255) COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `password`    varchar(255) COLLATE utf8_bin NOT NULL COMMENT '密码（加密）',
  `create_time` datetime                      NOT NULL COMMENT '创建时间',
  `enabled`     tinyint(1)                    NOT NULL DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user`
VALUES (1, 'Admin', 'admin', '$2a$10$eLN0qc4u22eSMQn1bbXTVuyQ/DmjEOqFigsFuwFgJvElg7VW/iNcu', '2019-02-01 08:08:15', 1);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
  `id`      bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) NOT NULL COMMENT '用户id',
  `role_id` bigint(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='用户角色表';

SET FOREIGN_KEY_CHECKS = 1;
