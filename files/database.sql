CREATE DATABASE `test`
  COLLATE utf8_bin
  DEFAULT CHARSET = utf8;
USE `test`;

CREATE TABLE `users` (
  `id`       bigint(20)                    NOT NULL AUTO_INCREMENT,
  `username` varchar(50)  COLLATE utf8_bin NOT NULL
  COMMENT '用户名',
  `password` varchar(100) COLLATE utf8_bin NOT NULL
  COMMENT '密码',
  `nickname` varchar(50)  COLLATE utf8_bin          DEFAULT NULL
  COMMENT '昵称',
  `gender`   varchar(2)   COLLATE utf8_bin          DEFAULT '03'
  COMMENT '性别：01-男；02-女；03-其他',
  `file_id`  bigint(20)                             DEFAULT NULL
  COMMENT '头像图片id',
  `group_id` bigint(20)                    NOT NULL
  COMMENT '所属群组id',
  PRIMARY KEY (`id`),
  KEY `users_id_username_index` (`id`, `username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

CREATE TABLE `accounts` (
  `id`      bigint(20)     NOT NULL AUTO_INCREMENT,
  `balance` decimal(15, 2) NOT NULL DEFAULT '0.00'
  COMMENT '余额',
  `user_id` bigint(20)     NOT NULL
  COMMENT '所属用户id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

CREATE TABLE `groups` (
  `id`   bigint(20)  NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL
  COMMENT '组织名称',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='组织表';

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

CREATE TABLE `files` (
  `id`            bigint(20)   NOT NULL AUTO_INCREMENT,
  `name`          varchar(256) NOT NULL
  COMMENT '文件名',
  `type`          varchar(8)   NOT NULL
  COMMENT '文件类型(扩展名)',
  `store_method`  varchar(64)           DEFAULT NULL
  COMMENT '存储方式:oss, ftp, local, Root',
  `store_address` varchar(256) NOT NULL
  COMMENT '存放地址',
  `create_time`   datetime     NOT NULL
  COMMENT '上传时间',
  `size`          int(9)                DEFAULT NULL
  COMMENT '大小(KB)',
  `user_id`       bigint(20)   NOT NULL
  COMMENT '上传用户',
  PRIMARY KEY (`id`),
  KEY `mp_file_user_id_type_index` (`user_id`, `type`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 496
  DEFAULT CHARSET = utf8
  COMMENT ='文件信息表';

INSERT INTO groups (id, name) VALUES (1, 'ADMIN');
INSERT INTO groups (id, name) VALUES (2, 'COMMON_USER');