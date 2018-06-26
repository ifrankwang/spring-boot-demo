USE `test`;

DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `id`       bigint(20)     NOT NULL AUTO_INCREMENT,
  `balance`  decimal(15, 2) NOT NULL DEFAULT '0.00'
  COMMENT '余额',
  `owner_id` bigint(20)     NOT NULL
  COMMENT '所属用户id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;