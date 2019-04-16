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

 Date: 16/04/2019 18:33:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api
-- ----------------------------
DROP TABLE IF EXISTS `api`;
CREATE TABLE `api`
(
    `id`           bigint(32)                  NOT NULL AUTO_INCREMENT,
    `method`       varchar(8) COLLATE utf8_bin NOT NULL COMMENT '请求方式',
    `path`         text COLLATE utf8_bin       NOT NULL COMMENT '请求接口路径',
    `name`         text COLLATE utf8_bin       NOT NULL COMMENT '接口名称',
    `authority_id` bigint(32) DEFAULT NULL COMMENT '涉及到的权限id',
    `creator_id`   bigint(32)                  NOT NULL COMMENT '创建者',
    `create_time`  datetime                    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='接口表';

-- ----------------------------
-- Records of api
-- ----------------------------
BEGIN;
INSERT INTO `api`
VALUES (1, 'GET', '/api/resource/list', '获取模块', 1, 1, '2019-04-12 10:00:41');
INSERT INTO `api`
VALUES (2, 'POST', '/api/resource', '创建模块', 2, 1, '2019-04-12 10:00:41');
INSERT INTO `api`
VALUES (3, 'PUT', '/api/resource/{id}', '更新模块', 3, 1, '2019-04-12 10:00:41');
INSERT INTO `api`
VALUES (4, 'DELETE', '/api/resource/{id}', '删除模块', 4, 1, '2019-04-12 10:00:41');
COMMIT;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`
(
    `id`          bigint(32)                    NOT NULL AUTO_INCREMENT,
    `resource_id` bigint(32)                    NOT NULL COMMENT '资源id',
    `operation`   varchar(128) COLLATE utf8_bin NOT NULL COMMENT '操作',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='权限表';

-- ----------------------------
-- Records of authority
-- ----------------------------
BEGIN;
INSERT INTO `authority`
VALUES (1, 1, 'GET');
INSERT INTO `authority`
VALUES (2, 1, 'CREATE');
INSERT INTO `authority`
VALUES (3, 1, 'UPDATE');
INSERT INTO `authority`
VALUES (4, 1, 'DELETE');
COMMIT;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`
(
    `id`         bigint(32)                    NOT NULL AUTO_INCREMENT,
    `name`       varchar(128) COLLATE utf8_bin NOT NULL COMMENT '名称',
    `creator_id` bigint(32)                    NOT NULL COMMENT '创建者',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='组表';

-- ----------------------------
-- Table structure for group_user
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user`
(
    `id`         bigint(32) NOT NULL AUTO_INCREMENT,
    `group_id`   bigint(32) DEFAULT NULL COMMENT '组id，为空的用户没有在任何组内',
    `user_id`    bigint(32) NOT NULL COMMENT '用户id',
    `role_id`    bigint(32) NOT NULL COMMENT '角色id',
    `creator_id` bigint(32) NOT NULL COMMENT '创建者',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='组用户表';

-- ----------------------------
-- Records of group_user
-- ----------------------------
BEGIN;
INSERT INTO `group_user`
VALUES (1, NULL, 1, 1, 1);
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
    `creator_id`  bigint(32)                    NOT NULL COMMENT '创建者',
    `create_time` datetime                      NOT NULL COMMENT '创建时间',
    `parent_id`   bigint(32)                             DEFAULT NULL COMMENT '亲代id',
    `protected`   tinyint(1)                    NOT NULL DEFAULT '0' COMMENT '是否受保护（无法删除、更新）',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='权限资源表';

-- ----------------------------
-- Records of resource
-- ----------------------------
BEGIN;
INSERT INTO `resource`
VALUES (1, '模块', 'resource', 1, '2019-04-12 09:55:29', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          bigint(32)                    NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) COLLATE utf8_bin NOT NULL COMMENT '名称',
    `generic`     tinyint(1)                    NOT NULL DEFAULT '1' COMMENT '是否为非业务类角色',
    `creator_id`  bigint(32)                    NOT NULL COMMENT '创建者',
    `create_time` datetime                      NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role`
VALUES (1, '系统管理员', 1, 1, '2019-04-12 09:57:12');
COMMIT;

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority`
(
    `id`           bigint(32)                    NOT NULL AUTO_INCREMENT,
    `role_id`      bigint(32)                    NOT NULL COMMENT '角色id',
    `authority_id` bigint(32)                    NOT NULL COMMENT '权限id',
    `access_level` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT 'PRIVATE' COMMENT '适用范围：PRIVATE-自己创建的；PROTECT-团队内的；PUBLIC-所有的',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='角色权限表';

-- ----------------------------
-- Records of role_authority
-- ----------------------------
BEGIN;
INSERT INTO `role_authority`
VALUES (1, 1, 1, 'PUBLIC');
INSERT INTO `role_authority`
VALUES (2, 1, 2, 'PUBLIC');
INSERT INTO `role_authority`
VALUES (3, 1, 3, 'PUBLIC');
INSERT INTO `role_authority`
VALUES (4, 1, 4, 'PUBLIC');
COMMIT;

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
    `creator_id`  bigint(32)                    NOT NULL COMMENT '创建者',
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
VALUES (1, 'Admin', 'admin', '$2a$10$eLN0qc4u22eSMQn1bbXTVuyQ/DmjEOqFigsFuwFgJvElg7VW/iNcu', 1, '2019-02-01 08:08:15',
        1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
