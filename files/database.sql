SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT,
  `name`     VARCHAR(10)
           COLLATE utf8_bin DEFAULT NULL,
  `username` VARCHAR(10)
             COLLATE utf8_bin DEFAULT NULL,
  `password` VARCHAR(10)
             COLLATE utf8_bin DEFAULT NULL,
  `gender`   VARCHAR(6)
           COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Jack', 'jack', 'jack', 'male');
INSERT INTO `user` VALUES ('2', 'Jone', 'jone', 'jone', 'female');
