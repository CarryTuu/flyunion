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

 Date: 21/12/2024 20:40:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for constants
-- ----------------------------
DROP TABLE IF EXISTS `constants`;
CREATE TABLE `constants`
(
    `key`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `value`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    PRIMARY KEY (`key`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fleet
-- ----------------------------
DROP TABLE IF EXISTS `fleet`;
CREATE TABLE `fleet`
(
    `name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `model`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `economy`  double                                                       NOT NULL,
    `business` double                                                       NOT NULL,
    `cargo`    double                                                       NOT NULL,
    `number`   int                                                          NULL DEFAULT NULL,
    PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flight-log
-- ----------------------------
DROP TABLE IF EXISTS `flight-log`;
CREATE TABLE `flight-log`
(
    `id`    varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `code`  varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NOT NULL,
    `plane` char(5) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci      NOT NULL,
    `pilot` int                                                           NOT NULL,
    `date`  datetime(0)                                                   NOT NULL,
    `rate`  int                                                           NOT NULL,
    `oil`   double                                                        NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flight-plan
-- ----------------------------
DROP TABLE IF EXISTS `flight-plan`;
CREATE TABLE `flight-plan`
(
    `plan-id`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `departure` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      NOT NULL,
    `arrival`   char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      NOT NULL,
    `route`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    PRIMARY KEY (`plan-id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for plane
-- ----------------------------
DROP TABLE IF EXISTS `plane`;
CREATE TABLE `plane`
(
    `code`       varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `owner`      int                                                          NOT NULL,
    `fleet`      varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `model`      varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `status`     varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    `time`       int                                                          NOT NULL,
    `durability` double                                                       NOT NULL,
    PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `cid`        int                                                            NOT NULL,
    `username`   varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci   NOT NULL,
    `callsign`   int                                                            NOT NULL,
    `password`   varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `email`      varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci   NOT NULL,
    `permission` int                                                            NOT NULL,
    `status`     varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci   NOT NULL,
    PRIMARY KEY (`cid`) USING BTREE,
    UNIQUE INDEX `callsign` (`callsign`) USING BTREE,
    UNIQUE INDEX `password` (`password`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
