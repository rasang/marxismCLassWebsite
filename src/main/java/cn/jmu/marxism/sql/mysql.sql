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

--建teachPlan表

CREATE TABLE `teachPlan` (
  `id`  int(2) NOT NULL ,
  `content`  varchar(255) CHARACTER SET utf8 NOT NULL ,
  `objective`  varchar(255) CHARACTER SET utf8 NOT NULL ,
  `arrangement`  varchar(255) CHARACTER SET utf8 NOT NULL ,
  `assessment`  varchar(255) CHARACTER SET utf8 NOT NULL ,
  PRIMARY KEY (`id`)
);

--建comment表

CREATE TABLE `comment` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `filename` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  `content` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--建课件原文件名和Url表
CREATE TABLE `learnfileurl`  (
  `filename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,/*课件文件原名*/
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL/*课件文件生成url的uuid.doc*/
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

--建教案原文件名和Url表
CREATE TABLE `teachfileurl`  (
  `filename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,/*教案文件原名*/
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL/*教案文件生成url的uuid.doc*/
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

--建userInfo表

CREATE TABLE `userInfo` (
  `userName` varchar(20) NOT NULL,
  `clazz` char(15) DEFAULT NULL,
  `school` varchar(20) DEFAULT NULL,
  `realName` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`userName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;