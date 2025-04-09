/*
 Navicat Premium Data Transfer

 Source Server         : CXA7852
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : flyunion

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 09/04/2025 22:30:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `id` char(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `pilot_count` int NOT NULL,
  `plane_count` int NOT NULL,
  `balance` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `base` char(4) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('342031651290825', '中国南方航空', 4, 10, '2000000', 'ZGGG');
INSERT INTO `company` VALUES ('622994566201426', '中国国际航空', 2, 3, '1200000', 'ZBAA');
INSERT INTO `company` VALUES ('839218643384247', '厦门航空', 1, 1, '1000000', 'ZSAM');

-- ----------------------------
-- Table structure for constants
-- ----------------------------
DROP TABLE IF EXISTS `constants`;
CREATE TABLE `constants`  (
  `key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `value` double NOT NULL,
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of constants
-- ----------------------------
INSERT INTO `constants` VALUES ('ATC_fee', 1000, '空管服务费');

-- ----------------------------
-- Table structure for fleet
-- ----------------------------
DROP TABLE IF EXISTS `fleet`;
CREATE TABLE `fleet`  (
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `economy` double NOT NULL,
  `business` double NOT NULL,
  `cargo` double NOT NULL,
  `number` int NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fleet
-- ----------------------------
INSERT INTO `fleet` VALUES ('Boeing 737-800', 'Boeing 737-800', 149, 8, 0, 5);

-- ----------------------------
-- Table structure for flight-log
-- ----------------------------
DROP TABLE IF EXISTS `flight-log`;
CREATE TABLE `flight-log`  (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `plane` char(5) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `pilot` varchar(6) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `date` datetime(0) NOT NULL,
  `rate` int NOT NULL,
  `oil` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flight-log
-- ----------------------------

-- ----------------------------
-- Table structure for flight-plan
-- ----------------------------
DROP TABLE IF EXISTS `flight-plan`;
CREATE TABLE `flight-plan`  (
  `plan-id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `flight-code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `departure` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `arrival` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `route` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`plan-id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flight-plan
-- ----------------------------
INSERT INTO `flight-plan` VALUES ('384f804039c14d3fa6d14772b2932002', 'MF8001', 'ZYTL', 'ZSHC', 'KARPI W5 TAO W174 XDX B221 ODULO V2 HSH G455 PK W116 JTN V73 SUPAR');
INSERT INTO `flight-plan` VALUES ('60fdf6ee6b1245aa9bdd87058eef4e21', 'MF8002', 'ZSPD', 'ZYTL', 'ODULO B221 XDX W174 FD W172 TEKAM V68 ORIXA');

-- ----------------------------
-- Table structure for plane
-- ----------------------------
DROP TABLE IF EXISTS `plane`;
CREATE TABLE `plane`  (
  `code` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `owner` varchar(6) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `fleet` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `model` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `status` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `time` int NOT NULL,
  `durability` double NOT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plane
-- ----------------------------
INSERT INTO `plane` VALUES ('B-1228', '100013', 'B737-800', 'Boeing 737-800', 'available', 0, 100);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `cid` varchar(6) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `callsign` int NOT NULL,
  `password` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `qq` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `permission` int NOT NULL,
  `status` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `balance` int NOT NULL,
  `company` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `airport` char(4) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `time` int NOT NULL,
  `flight_count` int NOT NULL,
  `job` int NOT NULL,
  `plane_count` int NOT NULL,
  PRIMARY KEY (`cid`) USING BTREE,
  UNIQUE INDEX `callsign`(`callsign`) USING BTREE,
  UNIQUE INDEX `password`(`password`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('100001', 'Ariven', 6067, '$2a$12$ZQaX5.I8yJls6N2avhPA3OF3QDV5OJk1EiN7uYT7iCgwW21Db5haG', '3208629021', '3208629021@qq.com', 1, 'normal', 0, '622994566201426', 'ZBAA', 357, 5, 0, 0);
INSERT INTO `user` VALUES ('100002', 'Nnnnq', 4660, '$2a$12$CO.IgAwxb8Oc6DOK5MJzNeUYWPbGIDo7KYFxAvVRxdYHc9rDcBlp2', '2084791301', '2084791301@qq.com', 1, 'normal', 0, '342031651290825', 'ZGGG', 233, 4, 0, 0);
INSERT INTO `user` VALUES ('100003', 'XLiao', 6327, '$2a$12$LDvTviKfsl1U8fgd/5ESqez5rBNC9l7SBuAM0eHdX6fwdxPTbYKcW', '2456666787', '2456666787@qq.com', 1, 'normal', 0, '622994566201426', 'ZBAA', 533, 10, 0, 0);
INSERT INTO `user` VALUES ('100004', '黄少天', 9952, '$2a$12$ZCjJa4vVLpxTEc06u3G1EOB.YjBZOGqVQ7NGQ4z0/EWDAheQqyh.K', '1820009726', '1820009726@qq.com', 1, 'normal', 0, '342031651290825', 'ZGGG', 925, 15, 0, 0);
INSERT INTO `user` VALUES ('100005', '快乐成长', 9992, '$2a$12$3lkLvYQu3612I5d0iFmjxeZ2yLgELscO9KcP8vpAk.Dgw3fZawD0m', '844281616', '844281616@qq.com', 1, 'normal', 0, '342031651290825', 'ZGGG', 157, 3, 0, 0);
INSERT INTO `user` VALUES ('100006', 'GhostWu', 9771, '$2a$12$BLpW9AbWjZUrOu/VnGYl6OFYdiVpknpfqZXkOZPGgV030ZatWabTG', '280517150', '280517150@qq.com', 1, 'normal', 0, '342031651290825', 'ZGGG', 1235, 20, 0, 0);
INSERT INTO `user` VALUES ('100013', 'FallenEra', 1228, '$2a$12$OKd6tcHy2JRues6IE4vPqOHnCnM4svb/WySMtD5hUGl9ZgqhBb31m', '850824385', '850824385@qq.com', 4, 'normal', 120000, '839218643384247', 'ZBAD', 700, 15, 8, 2);

SET FOREIGN_KEY_CHECKS = 1;
