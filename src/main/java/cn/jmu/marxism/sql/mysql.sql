-- 建数据库

CREATE DATABASE `marxism` CHARACTER SET 'utf8';

-- 建user表

CREATE TABLE `user` (
                        `userId` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
                        `username` varchar(20) DEFAULT NULL,
                        `password` char(41) NOT NULL,
                        `identification` char(1) NOT NULL,
                        PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;