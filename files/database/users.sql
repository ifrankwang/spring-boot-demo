USE `test`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id`       bigint(32)                                         NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8_bin                       NOT NULL
  COMMENT '用户名',
  `password` varchar(64) COLLATE utf8_bin                       NOT NULL
  COMMENT '密码',
  `nickname` varchar(16) COLLATE utf8_bin                                DEFAULT NULL
  COMMENT '昵称',
  `gender`   enum ('MALE', 'FEMALE', 'OTHERS') COLLATE utf8_bin NOT NULL DEFAULT 'OTHERS'
  COMMENT '性别：MALE-男；FEMALE-女；OTHERS-其他',
  PRIMARY KEY (`id`),
  KEY `users_id_username_index` (`id`, `username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

INSERT INTO users (id, username, password, nickname, gender)
VALUES (1, 'admin', '$2a$10$phIiCUhvswioPKA1Ca6cnuO8vLAf4PruyfPHYkKAkdt6qROR707Zm', 'admin', 'OTHERS');
INSERT INTO users (id, username, password, nickname, gender)
VALUES (2, 'frank', '$2a$10$phIiCUhvswioPKA1Ca6cnuO8vLAf4PruyfPHYkKAkdt6qROR707Zm', 'frank', 'OTHERS');