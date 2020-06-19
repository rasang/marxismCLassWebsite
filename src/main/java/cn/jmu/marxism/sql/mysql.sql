-- 建数据库

CREATE DATABASE `marxism` CHARACTER SET 'utf8';

-- 建user表

CREATE TABLE `user` (
  `userId` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` char(41) NOT NULL,
  `identification` char(1) NOT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE KEY `usernameUnique` (`username`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 建courseIntroduce表

CREATE TABLE `courseIntroduce` (
  `id`  int(1) NOT NULL ,
  `course_summary`  varchar(255) CHARACTER SET utf8 NOT NULL ,
  `course_materials`  varchar(255) CHARACTER SET utf8 NOT NULL ,
  `teaching_characteristics`  varchar(255) CHARACTER SET utf8 NOT NULL ,
  `teaching_conditions`  varchar(255) CHARACTER SET utf8 NOT NULL ,
  `teaching_environment`  varchar(255) CHARACTER SET utf8 NOT NULL ,
  PRIMARY KEY (`id`)
);