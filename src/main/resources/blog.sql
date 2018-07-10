/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-05-09 10:35:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  `CATEGORY_ID` varchar(32) DEFAULT NULL COMMENT '所属分类',
  `TYPE` varchar(32) DEFAULT NULL COMMENT '0原创,1转载',
  `LOAD_URL` varchar(2048) DEFAULT NULL COMMENT '转载地址',
  `AUTHOR` varchar(255) DEFAULT NULL COMMENT '作者',
  `TITLE` varchar(255) DEFAULT NULL COMMENT '标题',
  `LABEL` varchar(255) DEFAULT NULL COMMENT '文章标签',
  `CONTENT` mediumtext COMMENT '文章内容',
  `CREATE_TIME` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `ALTER_TIME` varchar(32) DEFAULT NULL COMMENT '修改时间',
  `STATE` varchar(16) DEFAULT NULL COMMENT '审核状态Enum',
  `IS_DEL` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IS_DEL` (`IS_DEL`),
  KEY `fk_t_blog_t_user_1` (`USER_ID`),
  CONSTRAINT `fk_t_blog_t_user_1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Table structure for t_blog_statistics
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_statistics`;
CREATE TABLE `t_blog_statistics` (
  `ID` varchar(32) NOT NULL,
  `BLOG_ID` varchar(32) DEFAULT NULL COMMENT '文章ID',
  `READ_COUNT` varchar(64) DEFAULT NULL COMMENT '阅读数',
  `THUMB_UP_COUNT` varchar(64) DEFAULT NULL COMMENT '点赞数',
  `IS_DEL` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_t_blog_statistics_t_blog_1` (`BLOG_ID`),
  KEY `fk_t_blog_statistics_t_blog_2` (`IS_DEL`),
  CONSTRAINT `fk_t_blog_statistics_t_blog_1` FOREIGN KEY (`BLOG_ID`) REFERENCES `t_blog` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_t_blog_statistics_t_blog_2` FOREIGN KEY (`IS_DEL`) REFERENCES `t_blog` (`IS_DEL`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客信息统计表';

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `ID` varchar(32) NOT NULL,
  `PID` varchar(32) DEFAULT NULL,
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `CONTENT` varchar(32) DEFAULT NULL COMMENT '分类名',
  `CREATE_DATE` varchar(32) DEFAULT NULL,
  `IS_DEL` int(2) DEFAULT NULL,
  `SORT` int(8) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类别表';

-- ----------------------------
-- Table structure for t_code
-- ----------------------------
DROP TABLE IF EXISTS `t_code`;
CREATE TABLE `t_code` (
  `ID` varchar(32) NOT NULL,
  `CODE` varchar(16) DEFAULT NULL,
  `NAME` varchar(64) DEFAULT NULL,
  `PID` varchar(32) DEFAULT NULL,
  `PNAME` varchar(64) DEFAULT NULL,
  `SORT` int(8) DEFAULT NULL,
  `IS_DEL` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Table structure for t_response_first
-- ----------------------------
DROP TABLE IF EXISTS `t_response_first`;
CREATE TABLE `t_response_first` (
  `ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户id（外键）',
  `BLOG_ID` varchar(32) DEFAULT NULL COMMENT '博客ID',
  `CONTENT` text COMMENT '回复创建时间',
  `CREATE_TIME` varchar(32) DEFAULT NULL COMMENT '一级回复表',
  `IS_DEL` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_t_response_1_t_blog_1` (`BLOG_ID`),
  KEY `fk_t_response_1_t_blog_2` (`IS_DEL`),
  CONSTRAINT `fk_t_response_1_t_blog_1` FOREIGN KEY (`BLOG_ID`) REFERENCES `t_blog` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_t_response_1_t_blog_2` FOREIGN KEY (`IS_DEL`) REFERENCES `t_blog` (`IS_DEL`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一级回复表';

-- ----------------------------
-- Table structure for t_response_second
-- ----------------------------
DROP TABLE IF EXISTS `t_response_second`;
CREATE TABLE `t_response_second` (
  `ID` varchar(32) NOT NULL,
  `RESPONSE_ID` varchar(32) DEFAULT NULL COMMENT '一级回复ID',
  `USER_ID` varchar(32) DEFAULT NULL,
  `CONTENT` text,
  `CREATE_TIME` varchar(32) DEFAULT NULL,
  `IS_DEL` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_t_response_2_t_response_1_1` (`RESPONSE_ID`),
  KEY `fk_t_response_2_t_response_1_2` (`IS_DEL`),
  CONSTRAINT `fk_t_response_2_t_response_1_1` FOREIGN KEY (`RESPONSE_ID`) REFERENCES `t_response_first` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_t_response_2_t_response_1_2` FOREIGN KEY (`IS_DEL`) REFERENCES `t_response_first` (`IS_DEL`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二级回复表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `ID` varchar(32) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(255) DEFAULT NULL,
  `QUESTION` varchar(255) DEFAULT NULL COMMENT '密保问题',
  `ANSWER` varchar(255) DEFAULT NULL COMMENT '密保答案',
  `REG_TIME` varchar(255) DEFAULT NULL COMMENT '注册时间',
  `IS_DEL` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IS_DEL` (`IS_DEL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目录表';

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `SEX` varchar(10) DEFAULT NULL,
  `BIRTHDAY` varchar(32) DEFAULT NULL,
  `EMAIL` varchar(64) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  `IS_DEL` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_t_user_info_t_user_1` (`USER_ID`),
  KEY `fk_t_user_info_t_user_2` (`IS_DEL`),
  CONSTRAINT `fk_t_user_info_t_user_1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_t_user_info_t_user_2` FOREIGN KEY (`IS_DEL`) REFERENCES `t_user` (`IS_DEL`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ç¨æ·ä¿¡æ¯è¡¨用户信息表';
