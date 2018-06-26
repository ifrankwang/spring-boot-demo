USE `test`;

DROP TABLE IF EXISTS `files`;

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
  DEFAULT CHARSET = utf8
  COMMENT ='文件信息表';