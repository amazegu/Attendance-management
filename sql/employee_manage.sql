/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : employee_manage

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 13/07/2020 10:05:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `Aname` varchar(12) CHARACTER SET utf8 NOT NULL,
  `Apassword` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`Aname`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES ('admin', '123');
COMMIT;

-- ----------------------------
-- Table structure for belong
-- ----------------------------
DROP TABLE IF EXISTS `belong`;
CREATE TABLE `belong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of belong
-- ----------------------------
BEGIN;
INSERT INTO `belong` VALUES (1, 1, 3);
INSERT INTO `belong` VALUES (2, 7, 3);
INSERT INTO `belong` VALUES (3, 9, 3);
INSERT INTO `belong` VALUES (4, 28, 1);
INSERT INTO `belong` VALUES (5, 29, 1);
INSERT INTO `belong` VALUES (6, 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `user_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `sex` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of employee
-- ----------------------------
BEGIN;
INSERT INTO `employee` VALUES (1, 'admin', 'admin', '123', 0);
INSERT INTO `employee` VALUES (2, 'test', 'test', '123', 0);
INSERT INTO `employee` VALUES (3, 'test1', 'test1', '123', 0);
INSERT INTO `employee` VALUES (4, 'test2', 'test2', '123', 0);
INSERT INTO `employee` VALUES (5, 'test3', 'test3', '123', 0);
INSERT INTO `employee` VALUES (6, 'test4', 'test4', '123', 0);
INSERT INTO `employee` VALUES (7, 'test5', 'test5', '123', 0);
INSERT INTO `employee` VALUES (8, 'test6', 'test6', '123', 0);
INSERT INTO `employee` VALUES (9, 'test7', 'test7', '123', 0);
INSERT INTO `employee` VALUES (11, 'test9', 'test9', '123', 0);
INSERT INTO `employee` VALUES (12, 'test10', 'test10', '123', 0);
INSERT INTO `employee` VALUES (13, 'test11', 'test11', '123', 0);
INSERT INTO `employee` VALUES (14, 'test12', 'test12', '123', 0);
INSERT INTO `employee` VALUES (15, 'test13', 'test13', '123', 0);
INSERT INTO `employee` VALUES (16, 'test14', 'test14', '123', 0);
INSERT INTO `employee` VALUES (17, 'test15', 'test15', '123', 0);
INSERT INTO `employee` VALUES (18, 'test16', 'test16', '123', 0);
INSERT INTO `employee` VALUES (19, 'test17', 'test17', '123', 0);
INSERT INTO `employee` VALUES (20, 'test18', 'test18', '123', 0);
INSERT INTO `employee` VALUES (21, 'test19', 'test19', '123', 0);
INSERT INTO `employee` VALUES (22, 'test20', 'test20', '123', 0);
INSERT INTO `employee` VALUES (23, 'test21', 'test21', '123', 0);
INSERT INTO `employee` VALUES (24, 'test22', 'test22', '123', 0);
INSERT INTO `employee` VALUES (25, 'test23', 'test23', '123', 0);
INSERT INTO `employee` VALUES (26, 'test24', 'test24', '123', 0);
INSERT INTO `employee` VALUES (27, 'test25', 'test25', '123', 0);
INSERT INTO `employee` VALUES (28, 'test26', 'test26', '123', 0);
INSERT INTO `employee` VALUES (29, 'test27', 'test27', '123', 0);
INSERT INTO `employee` VALUES (30, 'test28', 'test28', '123', 0);
INSERT INTO `employee` VALUES (31, 'test29', 'test29', '123', 0);
INSERT INTO `employee` VALUES (32, 'test30', 'test30', '123', 0);
COMMIT;

-- ----------------------------
-- Table structure for holiday
-- ----------------------------
DROP TABLE IF EXISTS `holiday`;
CREATE TABLE `holiday` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hDate` date NOT NULL,
  `festival` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of holiday
-- ----------------------------
BEGIN;
INSERT INTO `holiday` VALUES (1, '2020-01-01', '元旦');
INSERT INTO `holiday` VALUES (2, '2020-01-13', '成人の日');
INSERT INTO `holiday` VALUES (3, '2020-02-11', '建國紀念の日');
INSERT INTO `holiday` VALUES (4, '2020-02-23', '天皇誕生日');
INSERT INTO `holiday` VALUES (5, '2020-02-24', '振替休日');
INSERT INTO `holiday` VALUES (6, '2020-03-20', '春分の日');
INSERT INTO `holiday` VALUES (7, '2020-04-29', '昭和の日');
INSERT INTO `holiday` VALUES (8, '2020-05-03', '憲法紀念日');
INSERT INTO `holiday` VALUES (9, '2020-05-04', 'みどりの日');
INSERT INTO `holiday` VALUES (10, '2020-05-05', 'こどもの日');
INSERT INTO `holiday` VALUES (11, '2020-05-06', '振替休日');
INSERT INTO `holiday` VALUES (12, '2020-07-23', '海の日');
INSERT INTO `holiday` VALUES (13, '2020-07-24', 'スボーツの日');
INSERT INTO `holiday` VALUES (14, '2020-08-10', '山の日');
INSERT INTO `holiday` VALUES (15, '2020-09-21', '敬老の日');
INSERT INTO `holiday` VALUES (16, '2020-09-22', '秋分の日');
INSERT INTO `holiday` VALUES (17, '2020-11-03', '文化の日');
INSERT INTO `holiday` VALUES (18, '2020-11-23', '勤労感謝の日');
COMMIT;

-- ----------------------------
-- Table structure for mission
-- ----------------------------
DROP TABLE IF EXISTS `mission`;
CREATE TABLE `mission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NOT NULL,
  `date` date NOT NULL,
  `design` varchar(5) COLLATE utf8_bin DEFAULT '0.0',
  `test` varchar(5) COLLATE utf8_bin DEFAULT '0.0',
  `meeting` varchar(5) COLLATE utf8_bin DEFAULT '0.0',
  `study` varchar(5) COLLATE utf8_bin DEFAULT '0.0',
  `code` varchar(5) COLLATE utf8_bin DEFAULT '0.0',
  PRIMARY KEY (`id`,`eid`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for program
-- ----------------------------
DROP TABLE IF EXISTS `program`;
CREATE TABLE `program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `startime` time DEFAULT NULL,
  `endtime` time DEFAULT NULL,
  `rest` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `adress` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of program
-- ----------------------------
BEGIN;
INSERT INTO `program` VALUES (1, 'test12', '09:00:00', '18:00:00', '1.0', 'test1', '2020-06-01', '2020-06-30');
INSERT INTO `program` VALUES (3, 'test23', '09:00:00', '18:00:00', '1.0', 'test', '2020-06-04', '2020-06-27');
COMMIT;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NOT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `date` date NOT NULL,
  `situation` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `worktime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remarks` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `rest` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of record
-- ----------------------------
BEGIN;
INSERT INTO `record` VALUES (1, 1, '08:00:00', '16:00:00', '2020-03-21', NULL, '7.0', '11', '1.0');
INSERT INTO `record` VALUES (2, 1, '08:00:00', '16:00:00', '2020-03-22', NULL, '7.0', '12', '1.0');
INSERT INTO `record` VALUES (3, 1, '08:00:00', '16:00:00', '2020-03-23', NULL, '7.0', '13', '1.0');
INSERT INTO `record` VALUES (4, 1, '08:00:00', '16:00:00', '2020-03-24', NULL, '7.0', '14', '1.0');
INSERT INTO `record` VALUES (5, 1, '08:00:00', '16:00:00', '2020-03-25', NULL, '7.0', '15', '1.0');
INSERT INTO `record` VALUES (6, 1, '08:00:00', '16:00:00', '2020-03-26', NULL, '7.0', '16', '1.0');
INSERT INTO `record` VALUES (7, 1, '08:00:00', '16:00:00', '2020-03-27', NULL, '7.0', '17', '1.0');
INSERT INTO `record` VALUES (8, 1, '08:00:00', '16:00:00', '2020-03-28', NULL, '7.0', '18', '1.0');
INSERT INTO `record` VALUES (9, 1, '08:00:00', '16:00:00', '2020-03-29', NULL, '7.0', '19', '1.0');
INSERT INTO `record` VALUES (10, 1, '08:00:00', '16:00:00', '2020-03-30', NULL, '7.0', '20', '1.0');
INSERT INTO `record` VALUES (11, 1, '08:00:00', '16:00:00', '2020-03-31', NULL, '7.0', '21', '1.0');
INSERT INTO `record` VALUES (12, 1, '08:00:00', '16:00:00', '2020-04-01', NULL, '7.0', '22', '1.0');
INSERT INTO `record` VALUES (13, 1, '08:00:00', '16:00:00', '2020-04-02', NULL, '7.0', '23', '1.0');
INSERT INTO `record` VALUES (14, 1, '08:00:00', '16:00:00', '2020-04-03', NULL, '7.0', '24', '1.0');
INSERT INTO `record` VALUES (15, 1, '08:00:00', '16:00:00', '2020-04-04', NULL, '7.0', '25', '1.0');
INSERT INTO `record` VALUES (16, 1, '08:00:00', '16:00:00', '2020-04-05', NULL, '7.0', '26', '1.0');
INSERT INTO `record` VALUES (17, 1, '08:00:00', '16:00:00', '2020-04-06', NULL, '7.0', '27', '1.0');
INSERT INTO `record` VALUES (18, 1, '08:00:00', '16:00:00', '2020-04-07', NULL, '7.0', '28', '1.0');
INSERT INTO `record` VALUES (19, 1, '08:00:00', '16:00:00', '2020-04-08', NULL, '7.0', '29', '1.0');
INSERT INTO `record` VALUES (20, 1, '08:00:00', '16:00:00', '2020-04-09', NULL, '7.0', '30', '1.0');
INSERT INTO `record` VALUES (21, 1, '08:00:00', '16:00:00', '2020-04-10', NULL, '7.0', '31', '1.0');
INSERT INTO `record` VALUES (22, 1, '08:00:00', '16:00:00', '2020-04-11', NULL, '7.0', '32', '1.0');
INSERT INTO `record` VALUES (23, 1, '08:00:00', '16:00:00', '2020-04-12', NULL, '7.0', '33', '1.0');
INSERT INTO `record` VALUES (24, 1, '08:00:00', '16:00:00', '2020-04-13', NULL, '7.0', '34', '1.0');
INSERT INTO `record` VALUES (25, 1, '08:00:00', '16:00:00', '2020-04-14', NULL, '7.0', '35', '1.0');
INSERT INTO `record` VALUES (26, 1, '08:00:00', '16:00:00', '2020-04-15', NULL, '7.0', '36', '1.0');
INSERT INTO `record` VALUES (27, 1, '08:00:00', '16:00:00', '2020-04-16', NULL, '7.0', '37', '1.0');
INSERT INTO `record` VALUES (28, 1, '08:00:00', '16:00:00', '2020-04-17', NULL, '7.0', '38', '1.0');
INSERT INTO `record` VALUES (29, 1, '08:00:00', '16:00:00', '2020-04-18', NULL, '7.0', '39', '1.0');
INSERT INTO `record` VALUES (30, 1, '08:00:00', '16:00:00', '2020-04-19', NULL, '7.0', '40', '1.0');
INSERT INTO `record` VALUES (31, 1, '08:00:00', '16:00:00', '2020-04-20', NULL, '7.0', '41', '1.0');
INSERT INTO `record` VALUES (32, 1, '08:00:00', '16:00:00', '2020-04-21', NULL, '7.0', '42', '1.0');
INSERT INTO `record` VALUES (33, 1, '08:00:00', '16:00:00', '2020-04-22', NULL, '7.0', '43', '1.0');
INSERT INTO `record` VALUES (34, 1, '08:00:00', '16:00:00', '2020-04-23', NULL, '7.0', '44', '1.0');
INSERT INTO `record` VALUES (35, 1, '08:00:00', '16:00:00', '2020-04-24', NULL, '7.0', '45', '1.0');
INSERT INTO `record` VALUES (36, 1, '08:00:00', '16:00:00', '2020-04-25', NULL, '7.0', '46', '1.0');
INSERT INTO `record` VALUES (37, 1, '08:00:00', '16:00:00', '2020-04-26', NULL, '7.0', '47', '1.0');
INSERT INTO `record` VALUES (38, 1, '08:00:00', '16:00:00', '2020-04-27', NULL, '7.0', '48', '1.0');
INSERT INTO `record` VALUES (39, 1, '08:00:00', '16:00:00', '2020-04-28', NULL, '7.0', '49', '1.0');
INSERT INTO `record` VALUES (40, 1, '08:00:00', '16:00:00', '2020-04-29', NULL, '7.0', '50', '1.0');
INSERT INTO `record` VALUES (41, 1, '08:00:00', '16:00:00', '2020-04-30', NULL, '7.0', '51', '1.0');
INSERT INTO `record` VALUES (42, 1, '08:00:00', '16:00:00', '2020-05-01', NULL, '7.0', '52', '1.0');
INSERT INTO `record` VALUES (43, 1, '08:00:00', '16:00:00', '2020-05-02', NULL, '7.0', '53', '1.0');
INSERT INTO `record` VALUES (44, 1, '08:00:00', '16:00:00', '2020-05-03', NULL, '7.0', '54', '1.0');
INSERT INTO `record` VALUES (45, 1, '08:00:00', '16:00:00', '2020-05-04', NULL, '7.0', '55', '1.0');
INSERT INTO `record` VALUES (46, 1, '08:00:00', '16:00:00', '2020-05-05', NULL, '7.0', '56', '1.0');
INSERT INTO `record` VALUES (47, 1, '08:00:00', '16:00:00', '2020-05-06', NULL, '7.0', '57', '1.0');
INSERT INTO `record` VALUES (48, 1, '08:00:00', '16:00:00', '2020-05-07', NULL, '7.0', '58', '1.0');
INSERT INTO `record` VALUES (49, 1, '08:00:00', '16:00:00', '2020-05-08', NULL, '7.0', '59', '1.0');
INSERT INTO `record` VALUES (50, 1, '08:00:00', '16:00:00', '2020-05-09', NULL, '7.0', '60', '1.0');
INSERT INTO `record` VALUES (51, 1, '08:00:00', '16:00:00', '2020-05-10', NULL, '7.0', '61', '1.0');
INSERT INTO `record` VALUES (52, 1, '08:00:00', '16:00:00', '2020-05-11', NULL, '7.0', '62', '1.0');
INSERT INTO `record` VALUES (53, 1, '08:00:00', '16:00:00', '2020-05-12', NULL, '7.0', '11', '1.0');
INSERT INTO `record` VALUES (54, 1, '08:00:00', '16:00:00', '2020-05-13', NULL, '7.0', '12', '1.0');
INSERT INTO `record` VALUES (55, 1, '08:00:00', '16:00:00', '2020-05-14', NULL, '7.0', '13', '1.0');
INSERT INTO `record` VALUES (56, 1, '08:00:00', '16:00:00', '2020-05-15', NULL, '7.0', '14', '1.0');
INSERT INTO `record` VALUES (57, 1, '08:00:00', '16:00:00', '2020-05-16', NULL, '7.0', '15', '1.0');
INSERT INTO `record` VALUES (58, 1, '08:00:00', '16:00:00', '2020-05-17', NULL, '7.0', '16', '1.0');
INSERT INTO `record` VALUES (59, 1, '08:00:00', '16:00:00', '2020-05-18', NULL, '7.0', '17', '1.0');
INSERT INTO `record` VALUES (60, 1, '08:00:00', '16:00:00', '2020-05-19', NULL, '7.0', '18', '1.0');
INSERT INTO `record` VALUES (61, 1, '08:00:00', '16:00:00', '2020-05-20', NULL, '7.0', '19', '1.0');
INSERT INTO `record` VALUES (62, 1, '08:00:00', '16:00:00', '2020-05-21', NULL, '7.0', '20', '1.0');
INSERT INTO `record` VALUES (63, 1, '08:00:00', '16:00:00', '2020-05-22', NULL, '7.0', '21', '1.0');
INSERT INTO `record` VALUES (64, 1, '08:00:00', '16:00:00', '2020-05-23', NULL, '7.0', '22', '1.0');
INSERT INTO `record` VALUES (65, 1, '08:00:00', '16:00:00', '2020-05-24', NULL, '7.0', '23', '1.0');
INSERT INTO `record` VALUES (66, 1, '08:00:00', '16:00:00', '2020-05-25', NULL, '7.0', '24', '1.0');
INSERT INTO `record` VALUES (67, 1, '08:00:00', '16:00:00', '2020-05-26', NULL, '7.0', '25', '1.0');
INSERT INTO `record` VALUES (68, 1, '08:00:00', '16:00:00', '2020-05-27', NULL, '7.0', '26', '1.0');
INSERT INTO `record` VALUES (69, 1, '08:00:00', '16:00:00', '2020-05-28', NULL, '7.0', '27', '1.0');
INSERT INTO `record` VALUES (70, 1, '08:00:00', '16:00:00', '2020-05-29', NULL, '7.0', '28', '1.0');
INSERT INTO `record` VALUES (71, 1, '08:00:00', '16:00:00', '2020-05-30', NULL, '7.0', '29', '1.0');
INSERT INTO `record` VALUES (72, 1, '08:00:00', '16:00:00', '2020-05-31', NULL, '7.0', '30', '1.0');
INSERT INTO `record` VALUES (73, 1, '08:00:00', '16:00:00', '2020-06-01', NULL, '7.0', '31', '1.0');
INSERT INTO `record` VALUES (74, 1, '08:00:00', '16:00:00', '2020-06-02', NULL, '7.0', '32', '1.0');
INSERT INTO `record` VALUES (75, 1, '08:00:00', '16:00:00', '2020-06-03', NULL, '7.0', '33', '1.0');
INSERT INTO `record` VALUES (76, 1, '08:00:00', '16:00:00', '2020-06-04', NULL, '7.0', '34', '1.0');
INSERT INTO `record` VALUES (77, 1, '08:00:00', '16:00:00', '2020-06-05', NULL, '7.0', '35', '1.0');
INSERT INTO `record` VALUES (78, 1, '08:00:00', '16:00:00', '2020-06-06', NULL, '7.0', '36', '1.0');
INSERT INTO `record` VALUES (79, 1, '08:00:00', '16:00:00', '2020-06-07', NULL, '7.0', '37', '1.0');
INSERT INTO `record` VALUES (80, 1, '08:00:00', '16:00:00', '2020-06-08', NULL, '7.0', '38', '1.0');
INSERT INTO `record` VALUES (81, 1, '08:00:00', '16:00:00', '2020-06-09', NULL, '7.0', '39', '1.0');
INSERT INTO `record` VALUES (82, 1, '08:00:00', '16:00:00', '2020-06-10', NULL, '7.0', '40', '1.0');
INSERT INTO `record` VALUES (83, 1, '08:00:00', '16:00:00', '2020-06-11', NULL, '7.0', '41', '1.0');
INSERT INTO `record` VALUES (84, 1, '08:00:00', '16:00:00', '2020-06-12', NULL, '7.0', '42', '1.0');
INSERT INTO `record` VALUES (85, 1, '08:00:00', '16:00:00', '2020-06-13', NULL, '7.0', '43', '1.0');
INSERT INTO `record` VALUES (86, 1, '08:00:00', '16:00:00', '2020-06-14', NULL, '7.0', '44', '1.0');
INSERT INTO `record` VALUES (87, 1, '08:00:00', '16:00:00', '2020-06-15', NULL, '7.0', '45', '1.0');
INSERT INTO `record` VALUES (88, 1, '08:00:00', '16:00:00', '2020-06-16', NULL, '7.0', '46', '1.0');
INSERT INTO `record` VALUES (89, 1, '08:00:00', '16:00:00', '2020-06-17', NULL, '7.0', '47', '1.0');
INSERT INTO `record` VALUES (90, 1, '08:00:00', '16:00:00', '2020-06-18', NULL, '7.0', '48', '1.0');
INSERT INTO `record` VALUES (91, 1, '08:00:00', '16:00:00', '2020-06-19', NULL, '7.0', '49', '1.0');
INSERT INTO `record` VALUES (92, 1, '18:02:17', '00:00:00', '2020-06-24', '', '0', '111', '1.0');
INSERT INTO `record` VALUES (93, 1, '09:34:36', NULL, '2020-06-29', NULL, NULL, NULL, NULL);
INSERT INTO `record` VALUES (94, 1, '10:33:32', NULL, '2020-07-08', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NOT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `date` date NOT NULL,
  `situation` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `worktime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remarks` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `rest` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of report
-- ----------------------------
BEGIN;
INSERT INTO `report` VALUES (1, 1, '08:00:00', '16:00:00', '2020-06-04', '  ', '7.0', '34', '1.0');
INSERT INTO `report` VALUES (2, 1, '08:00:00', '16:00:00', '2020-06-01', '  ', '7.0', '31', '1.0');
INSERT INTO `report` VALUES (3, 1, '08:00:00', '16:00:00', '2020-06-02', '  ', '7.0', '32', '1.0');
INSERT INTO `report` VALUES (4, 1, '08:00:00', '16:00:00', '2020-06-03', '  ', '7.0', '33', '1.0');
INSERT INTO `report` VALUES (5, 1, '08:00:00', '16:00:00', '2020-06-19', '  ', '7.0', '49', '1.0');
INSERT INTO `report` VALUES (6, 1, '08:00:00', '16:00:00', '2020-06-18', '  ', '7.0', '48', '1.0');
INSERT INTO `report` VALUES (7, 1, '08:00:00', '16:00:00', '2020-06-17', '  ', '7.0', '47', '1.0');
INSERT INTO `report` VALUES (8, 1, '08:00:00', '16:00:00', '2020-06-16', '  ', '7.0', '46', '1.0');
INSERT INTO `report` VALUES (9, 1, '08:00:00', '16:00:00', '2020-06-15', '  ', '7.0', '45', '1.0');
INSERT INTO `report` VALUES (10, 1, '08:00:00', '16:00:00', '2020-06-14', '  ', '7.0', '44', '1.0');
INSERT INTO `report` VALUES (11, 1, '08:00:00', '16:00:00', '2020-06-13', '  ', '7.0', '43', '1.0');
INSERT INTO `report` VALUES (12, 1, '08:00:00', '16:00:00', '2020-06-12', '  ', '7.0', '42', '1.0');
INSERT INTO `report` VALUES (13, 1, '08:00:00', '16:00:00', '2020-06-11', '  ', '7.0', '41', '1.0');
INSERT INTO `report` VALUES (14, 1, '08:00:00', '16:00:00', '2020-06-10', '  ', '7.0', '40', '1.0');
INSERT INTO `report` VALUES (15, 1, '08:00:00', '16:00:00', '2020-06-09', '  ', '7.0', '39', '1.0');
INSERT INTO `report` VALUES (16, 1, '08:00:00', '16:00:00', '2020-06-08', '  ', '7.0', '38', '1.0');
INSERT INTO `report` VALUES (17, 1, '08:00:00', '16:00:00', '2020-06-07', '  ', '7.0', '37', '1.0');
INSERT INTO `report` VALUES (18, 1, '08:00:00', '16:00:00', '2020-06-06', '  ', '7.0', '36', '1.0');
INSERT INTO `report` VALUES (19, 1, '08:00:00', '16:00:00', '2020-06-05', '  ', '7.0', '35', '1.0');
INSERT INTO `report` VALUES (24, 1, '08:00:00', '16:00:00', '2020-05-01', '  ', '7.0', '52', '1.0');
INSERT INTO `report` VALUES (25, 1, '08:00:00', '16:00:00', '2020-05-02', '  ', '7.0', '53', '1.0');
INSERT INTO `report` VALUES (26, 1, '08:00:00', '16:00:00', '2020-05-03', '  ', '7.0', '54', '1.0');
INSERT INTO `report` VALUES (27, 1, '08:00:00', '16:00:00', '2020-05-04', '  ', '7.0', '55', '1.0');
INSERT INTO `report` VALUES (28, 1, '08:00:00', '16:00:00', '2020-05-05', '  ', '7.0', '56', '1.0');
INSERT INTO `report` VALUES (29, 1, '08:00:00', '16:00:00', '2020-05-06', '  ', '7.0', '57', '1.0');
INSERT INTO `report` VALUES (30, 1, '08:00:00', '16:00:00', '2020-05-07', '  ', '7.0', '58', '1.0');
INSERT INTO `report` VALUES (31, 1, '08:00:00', '16:00:00', '2020-05-08', '  ', '7.0', '59', '1.0');
INSERT INTO `report` VALUES (32, 1, '08:00:00', '16:00:00', '2020-05-09', '  ', '7.0', '60', '1.0');
INSERT INTO `report` VALUES (33, 1, '08:00:00', '16:00:00', '2020-05-10', '  ', '7.0', '61', '1.0');
INSERT INTO `report` VALUES (34, 1, '08:00:00', '16:00:00', '2020-05-11', '  ', '7.0', '62', '1.0');
INSERT INTO `report` VALUES (35, 1, '08:00:00', '16:00:00', '2020-05-12', '  ', '7.0', '11', '1.0');
INSERT INTO `report` VALUES (36, 1, '08:00:00', '16:00:00', '2020-05-13', '  ', '7.0', '12', '1.0');
INSERT INTO `report` VALUES (37, 1, '08:00:00', '16:00:00', '2020-05-14', '  ', '7.0', '13', '1.0');
INSERT INTO `report` VALUES (38, 1, '08:00:00', '16:00:00', '2020-05-15', '  ', '7.0', '14', '1.0');
INSERT INTO `report` VALUES (39, 1, '08:00:00', '16:00:00', '2020-05-16', '  ', '7.0', '15', '1.0');
INSERT INTO `report` VALUES (40, 1, '08:00:00', '16:00:00', '2020-05-17', '  ', '7.0', '16', '1.0');
INSERT INTO `report` VALUES (41, 1, '08:00:00', '16:00:00', '2020-05-18', '  ', '7.0', '17', '1.0');
INSERT INTO `report` VALUES (42, 1, '08:00:00', '16:00:00', '2020-05-19', '  ', '7.0', '18', '1.0');
INSERT INTO `report` VALUES (43, 1, '08:00:00', '16:00:00', '2020-05-20', '  ', '7.0', '19', '1.0');
INSERT INTO `report` VALUES (44, 1, '08:00:00', '16:00:00', '2020-05-21', '  ', '7.0', '20', '1.0');
INSERT INTO `report` VALUES (45, 1, '08:00:00', '16:00:00', '2020-05-22', '  ', '7.0', '21', '1.0');
INSERT INTO `report` VALUES (46, 1, '08:00:00', '16:00:00', '2020-05-23', '  ', '7.0', '22', '1.0');
INSERT INTO `report` VALUES (47, 1, '08:00:00', '16:00:00', '2020-05-24', '  ', '7.0', '23', '1.0');
INSERT INTO `report` VALUES (48, 1, '08:00:00', '16:00:00', '2020-05-25', '  ', '7.0', '24', '1.0');
INSERT INTO `report` VALUES (49, 1, '08:00:00', '16:00:00', '2020-05-26', '  ', '7.0', '25', '1.0');
INSERT INTO `report` VALUES (50, 1, '08:00:00', '16:00:00', '2020-05-27', '  ', '7.0', '26', '1.0');
INSERT INTO `report` VALUES (51, 1, '08:00:00', '16:00:00', '2020-05-28', '  ', '7.0', '27', '1.0');
INSERT INTO `report` VALUES (52, 1, '08:00:00', '16:00:00', '2020-05-29', '  ', '7.0', '28', '1.0');
INSERT INTO `report` VALUES (53, 1, '08:00:00', '16:00:00', '2020-05-30', '  ', '7.0', '29', '1.0');
INSERT INTO `report` VALUES (54, 1, '08:00:00', '16:00:00', '2020-05-31', '  ', '7.0', '30', '1.0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
