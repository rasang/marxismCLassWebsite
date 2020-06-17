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