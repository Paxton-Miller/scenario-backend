/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : scenario

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 20/07/2024 23:11:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '数据库标识自增字段',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '项目名',
  `progress` float NOT NULL DEFAULT 1 COMMENT '项目进度',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '类型，可能是无用字段',
  `is_enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用，默认启用（1）',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user_id` int NOT NULL DEFAULT 1 COMMENT '创建该条目的用户id，默认由超级管理员创建（0）',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` int NULL DEFAULT NULL COMMENT '修改该条目的用户id',
  `modify_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已被删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_constraint_1`(`id` ASC) USING BTREE,
  INDEX `fk_user_id`(`create_user_id` ASC) USING BTREE,
  CONSTRAINT `fk_user_id_project` FOREIGN KEY (`create_user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, 'Water Cycle', 1, 0, 1, NULL, 1, '2024-07-10 11:45:13', NULL, NULL, 0);
INSERT INTO `project` VALUES (2, 'Water Cycle', 1, 0, 1, NULL, 1, '2024-07-10 11:45:42', NULL, NULL, 0);
INSERT INTO `project` VALUES (3, 'Water Cycle', 1, 0, 1, NULL, 1, '2024-07-10 11:45:45', NULL, NULL, 0);
INSERT INTO `project` VALUES (4, 'Water Cycle', 1, 0, 1, NULL, 1, '2024-07-10 11:45:48', NULL, NULL, 0);
INSERT INTO `project` VALUES (5, 'Water Cycle', 1, 0, 1, NULL, 1, '2024-07-10 11:45:50', NULL, NULL, 0);
INSERT INTO `project` VALUES (6, 'Project Demo', 0.44, 0, 1, NULL, 1, '2024-07-10 15:41:40', NULL, NULL, 0);
INSERT INTO `project` VALUES (7, 'Project Demo', 0.185, 0, 1, NULL, 1, '2024-07-10 15:41:51', NULL, NULL, 0);
INSERT INTO `project` VALUES (8, 'Project Demo', 0.901, 0, 1, NULL, 1, '2024-07-10 15:42:24', NULL, NULL, 0);
INSERT INTO `project` VALUES (9, 'Something to remember me by', 1, 0, 1, NULL, 1, '2024-07-10 15:43:00', NULL, NULL, 0);
INSERT INTO `project` VALUES (10, 'AddTest', 1, 0, 1, '', 1, '2024-07-10 18:49:43', NULL, NULL, 0);
INSERT INTO `project` VALUES (11, 'NewPageTest', 1, 0, 1, '', 1, '2024-07-10 19:02:28', NULL, NULL, 0);
INSERT INTO `project` VALUES (12, 'AddPageTest2', 1, 0, 1, '', 1, '2024-07-10 19:06:29', NULL, NULL, 0);
INSERT INTO `project` VALUES (13, 'AddPageTest3', 1, 0, 1, '', 1, '2024-07-10 19:08:11', NULL, NULL, 0);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '数据库标识自增字段',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '原文件名',
  `uuid_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '唯一标识名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源的URL',
  `format` enum('png','jpg','jpeg','svg') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'jpg',
  `access_level` enum('open','restricted') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'open',
  `type` enum('map','chart') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'map',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user_id` int NOT NULL DEFAULT 1 COMMENT '创建该条目的用户id，默认由超级管理员创建（0）',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` int NULL DEFAULT NULL COMMENT '修改该条目的用户id',
  `modify_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已被删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user_id`(`create_user_id` ASC) USING BTREE,
  CONSTRAINT `fk_user_id_resource` FOREIGN KEY (`create_user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (1, '0717.jpg', '4647970b-7a23-459a-80d9-10ae031ba138.jpg', 'http://localhost:8898/assets/resource/4647970b-7a23-459a-80d9-10ae031ba138.jpg', 'jpg', 'restricted', 'map', NULL, 1, '2024-07-19 16:12:45', NULL, NULL, 0);
INSERT INTO `resource` VALUES (2, 'Weixin Image_20240703103405.png', 'b25cbc16-a15c-47fb-a422-d5ccaf0f7d7b.png', 'http://localhost:8898/assets/resource/b25cbc16-a15c-47fb-a422-d5ccaf0f7d7b.png', 'png', 'restricted', 'map', NULL, 1, '2024-07-19 16:12:51', NULL, NULL, 0);
INSERT INTO `resource` VALUES (3, 'Weixin Image_20240703124200.png', '971016c6-63f4-41b2-ae4e-650b69cc675a.png', 'http://localhost:8898/assets/resource/971016c6-63f4-41b2-ae4e-650b69cc675a.png', 'png', 'restricted', 'map', NULL, 1, '2024-07-19 16:12:58', NULL, NULL, 0);
INSERT INTO `resource` VALUES (4, 'Screenshot 2024-07-13 192051.png', '620abeef-4bfc-47cc-b737-bcfcedf38038.png', 'http://localhost:8898/assets/resource/620abeef-4bfc-47cc-b737-bcfcedf38038.png', 'png', 'restricted', 'map', NULL, 1, '2024-07-19 16:13:00', NULL, NULL, 0);
INSERT INTO `resource` VALUES (5, 'Screenshot 2024-07-13 221353.png', 'be753c8d-df40-4cd7-87e3-20364245bd9e.png', 'http://localhost:8898/assets/resource/be753c8d-df40-4cd7-87e3-20364245bd9e.png', 'png', 'restricted', 'map', NULL, 1, '2024-07-19 16:13:02', NULL, NULL, 0);
INSERT INTO `resource` VALUES (6, 'Weixin Image_20240703103405.png', '08012721-a630-4c99-bd1f-b7e253e14ea7.png', 'http://localhost:8898/assets/resource/08012721-a630-4c99-bd1f-b7e253e14ea7.png', 'png', 'restricted', 'map', NULL, 1, '2024-07-19 16:13:05', NULL, NULL, 0);
INSERT INTO `resource` VALUES (7, 'Beijing.png', 'd460b6a5-4978-425d-9e7a-43d703aa3156.png', 'http://localhost:8898/assets/resource/d460b6a5-4978-425d-9e7a-43d703aa3156.png', 'png', 'restricted', 'map', NULL, 1, '2024-07-19 16:13:06', NULL, NULL, 0);
INSERT INTO `resource` VALUES (8, 'Hongkong.jpg', '3e25768c-e4c1-49fc-8ae7-398bb9ed4571.jpg', 'http://localhost:8898/assets/resource/3e25768c-e4c1-49fc-8ae7-398bb9ed4571.jpg', 'jpg', 'open', 'map', NULL, 1, '2024-07-19 16:13:07', NULL, NULL, 0);
INSERT INTO `resource` VALUES (9, 'Nanjing.jpg', '28bbbb45-f2b1-448d-956f-15dd9b2829cc.jpg', 'http://localhost:8898/assets/resource/28bbbb45-f2b1-448d-956f-15dd9b2829cc.jpg', 'jpg', 'restricted', 'map', NULL, 1, '2024-07-19 16:13:09', NULL, NULL, 0);
INSERT INTO `resource` VALUES (10, 'Tokyo.jpg', 'd4818f17-1ec8-46c1-9bd2-2eaecf450a79.jpg', 'http://localhost:8898/assets/resource/d4818f17-1ec8-46c1-9bd2-2eaecf450a79.jpg', 'jpg', 'open', 'map', NULL, 1, '2024-07-19 16:13:11', NULL, NULL, 0);
INSERT INTO `resource` VALUES (11, 'Barcelona.jpg', 'b4aed116-3e33-455c-96d6-7a31102b1741.jpg', 'http://localhost:8898/assets/resource/b4aed116-3e33-455c-96d6-7a31102b1741.jpg', 'jpg', 'open', 'map', NULL, 1, '2024-07-19 16:13:13', NULL, NULL, 0);
INSERT INTO `resource` VALUES (12, 'London.jpg', '1a9ca959-fe2a-4e1f-bd54-b62e05bb6d58.jpg', 'http://localhost:8898/assets/resource/1a9ca959-fe2a-4e1f-bd54-b62e05bb6d58.jpg', 'jpg', 'open', 'map', NULL, 1, '2024-07-19 16:13:15', NULL, NULL, 0);
INSERT INTO `resource` VALUES (13, 'Madrid.jpg', '53d2abba-2c6c-483b-824c-61d377f75dd7.jpg', 'http://localhost:8898/assets/resource/53d2abba-2c6c-483b-824c-61d377f75dd7.jpg', 'jpg', 'open', 'map', NULL, 1, '2024-07-19 16:13:18', NULL, NULL, 0);
INSERT INTO `resource` VALUES (14, 'Moscow.jpg', '20491faf-2349-47f1-9322-5e19b4032ebe.jpg', 'http://localhost:8898/assets/resource/20491faf-2349-47f1-9322-5e19b4032ebe.jpg', 'jpg', 'open', 'map', NULL, 1, '2024-07-19 16:13:21', NULL, NULL, 0);
INSERT INTO `resource` VALUES (15, 'New York.jpg', '7fbe41ce-ad36-401d-bc74-0b27424ba2ec.jpg', 'http://localhost:8898/assets/resource/7fbe41ce-ad36-401d-bc74-0b27424ba2ec.jpg', 'jpg', 'open', 'map', NULL, 1, '2024-07-19 16:13:23', NULL, NULL, 0);
INSERT INTO `resource` VALUES (16, 'Berlin.jpg', 'e5e299b3-eb8b-4575-9bd6-6fdea9bf5202.jpg', 'http://localhost:8898/assets/resource/e5e299b3-eb8b-4575-9bd6-6fdea9bf5202.jpg', 'jpg', 'open', 'map', NULL, 1, '2024-07-19 16:13:24', NULL, NULL, 0);
INSERT INTO `resource` VALUES (17, 'Paris.jpg', 'f51588a2-e20e-4b63-b769-87f129fcb7ba.jpg', 'http://localhost:8898/assets/resource/f51588a2-e20e-4b63-b769-87f129fcb7ba.jpg', 'jpg', 'open', 'map', NULL, 1, '2024-07-19 16:13:26', NULL, NULL, 0);
INSERT INTO `resource` VALUES (18, 'Singapore.jpg', '324549f2-f242-454f-b62e-4a5a0f4068e9.jpg', 'http://localhost:8898/assets/resource/324549f2-f242-454f-b62e-4a5a0f4068e9.jpg', 'jpg', 'open', 'map', NULL, 1, '2024-07-19 16:13:27', NULL, NULL, 0);
INSERT INTO `resource` VALUES (19, 'Barcelona.jpg', '5e44a0c2-abf2-43b6-857a-5d7d87984111.jpg', 'http://localhost:8898/assets/resource/5e44a0c2-abf2-43b6-857a-5d7d87984111.jpg', 'jpg', 'restricted', 'map', NULL, 1, '2024-07-19 16:13:29', NULL, NULL, 0);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '数据库标识自增字段',
  `collaborator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '协作成员的id',
  `permission_level` enum('write','read') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'write',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scenario_id` int NOT NULL DEFAULT 1 COMMENT '所属的场景',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user_id` int NOT NULL DEFAULT 1 COMMENT '创建该条目的用户id，默认由超级管理员创建（0）',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` int NULL DEFAULT NULL COMMENT '修改该条目的用户id',
  `modify_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已被删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `scenario_id`(`scenario_id` ASC) USING BTREE,
  INDEX `fk_user_id`(`create_user_id` ASC) USING BTREE,
  CONSTRAINT `fk_scenario_id_room` FOREIGN KEY (`scenario_id`) REFERENCES `scenario` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_id_room` FOREIGN KEY (`create_user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, '1,2,3,', 'write', '2e5df8f0-f67b-47ea-bb23-9a7f88d681e0', 43, '', 1, '2024-07-20 19:15:41', 3, '2024-07-20 21:40:24', 0);
INSERT INTO `room` VALUES (2, '1,3,', 'write', '24c778d1-1b5e-420f-80ff-7d741096fc37', 44, '', 1, '2024-07-20 19:15:56', 3, '2024-07-20 22:25:30', 0);
INSERT INTO `room` VALUES (3, '1,', 'write', '7b597917-5918-43b5-9821-8f0c2aba742d', 45, '', 1, '2024-07-20 19:16:13', NULL, NULL, 0);

-- ----------------------------
-- Table structure for scenario
-- ----------------------------
DROP TABLE IF EXISTS `scenario`;
CREATE TABLE `scenario`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '数据库标识自增字段',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '场景名',
  `project_id` int NOT NULL DEFAULT 1 COMMENT '场景所属项目的id',
  `graph_x` float NOT NULL DEFAULT 0 COMMENT 'Graph中X坐标',
  `graph_y` float NOT NULL DEFAULT 0 COMMENT 'Graph中Y坐标',
  `graph_width` float NOT NULL DEFAULT 80,
  `graph_height` float NOT NULL DEFAULT 40,
  `node` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'graph中node自动生成的标识',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '类型，可能是无用字段',
  `is_enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用，默认启用（1）',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user_id` int NOT NULL DEFAULT 1 COMMENT '创建该条目的用户id，默认由超级管理员创建（0）',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` int NULL DEFAULT NULL COMMENT '修改该条目的用户id',
  `modify_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已被删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_constraint_1`(`id` ASC) USING BTREE,
  INDEX `fk_user_id`(`create_user_id` ASC) USING BTREE,
  CONSTRAINT `fk_user_id_scenario` FOREIGN KEY (`create_user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of scenario
-- ----------------------------
INSERT INTO `scenario` VALUES (1, 'scenario1', 1, 650, 50, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-10 11:46:36', 1, '2024-07-16 15:14:38', 1);
INSERT INTO `scenario` VALUES (2, 'scenario2', 1, 650, 170, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-10 11:46:49', 1, '2024-07-16 15:14:38', 1);
INSERT INTO `scenario` VALUES (3, 'scenario3', 1, 0, 0, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-10 11:46:55', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (4, 'scenario4', 1, 750, 270, 100, 50, NULL, 0, 1, NULL, 1, '2024-07-10 11:47:02', 1, '2024-07-16 15:14:38', 1);
INSERT INTO `scenario` VALUES (5, 'scenario5', 1, 760, 410, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 10:02:36', 1, '2024-07-16 15:14:38', 1);
INSERT INTO `scenario` VALUES (6, 'something', 1, 700, 240, 90, 40, NULL, 0, 1, NULL, 1, '2024-07-12 17:15:45', 1, '2024-07-15 10:03:17', 1);
INSERT INTO `scenario` VALUES (7, 'string', 0, 450, 260, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-12 18:36:05', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (8, 'string', 1, 450, 260, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-12 18:47:37', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (9, 'string', 2, 10, 10, 40, 40, NULL, 0, 1, 'string', 1, '2024-07-12 18:48:40', 1, '2024-07-12 18:51:24', 1);
INSERT INTO `scenario` VALUES (10, 'string', 2, 10, 10, 40, 40, NULL, 0, 1, 'string', 1, '2024-07-12 18:48:40', 1, '2024-07-12 18:51:24', 1);
INSERT INTO `scenario` VALUES (11, 'bug', 1, 750, 405, 60, 30, NULL, 0, 1, NULL, 1, '2024-07-13 22:31:47', 1, '2024-07-15 12:57:04', 1);
INSERT INTO `scenario` VALUES (12, 'newNode', 1, 550, 380, 130, 40, NULL, 0, 1, NULL, 1, '2024-07-13 22:31:55', 1, '2024-07-15 10:03:17', 1);
INSERT INTO `scenario` VALUES (13, 'testScenario', 1, 920, 210, 100, 40, NULL, 0, 1, NULL, 1, '2024-07-15 10:13:39', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (14, 'untitled', 1, 1020, 320, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 10:13:39', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (15, 'untitled', 1, 840, 320, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 10:13:39', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (16, 'testScenario', 1, 920, 210, 100, 40, NULL, 0, 1, NULL, 1, '2024-07-15 10:14:03', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (17, 'untitled', 1, 1020, 320, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 10:14:03', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (18, 'untitled', 1, 840, 320, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 10:14:03', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (19, 'test', 1, 940, 220, 60, 30, NULL, 0, 1, NULL, 1, '2024-07-15 10:32:26', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (20, '或者', 1, 830, 340, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 10:32:26', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (21, 'Better', 1, 1040, 340, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 10:32:26', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (22, 'untitled', 1, 760, 370, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 10:35:56', 1, '2024-07-15 14:41:57', 1);
INSERT INTO `scenario` VALUES (23, 'untitled', 1, 1004, 180, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 10:35:56', 1, '2024-07-15 10:43:07', 1);
INSERT INTO `scenario` VALUES (24, 'untitled', 1, 1004, 190, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 11:02:24', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (25, 'untitled', 1, 1004, 190, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 11:02:42', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (26, 'untitled', 1, 1004, 190, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 11:02:42', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (27, 'untitled', 1, 1004, 190, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 11:02:42', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (28, 'untitled', 1, 1084, 240, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 11:15:23', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (29, 'untitled', 1, 974, 230, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 11:15:23', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (30, 'untitled', 1, 1004, 210, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 11:49:04', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (31, 'untitled', 1, 1004, 250, 80, 40, NULL, 0, 1, NULL, 1, '2024-07-15 11:51:40', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (32, 'string', 1, 0, 0, 10, 10, NULL, 0, 1, 'string', 1, '2024-07-15 11:53:42', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (33, 'string', 1, 0, 0, 10, 10, NULL, 0, 1, 'string', 1, '2024-07-15 11:55:16', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (34, 'string', 1, 0, 0, 10, 10, NULL, 0, 1, 'string', 1, '2024-07-15 11:57:35', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (35, 'string', 1, 0, 0, 10, 10, 'string', 0, 1, 'string', 1, '2024-07-15 11:58:03', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (36, 'untitled', 1, 959, 230, 80, 40, '1431f543-12e0-4da2-b721-9adcc6efb3b6', 0, 1, NULL, 1, '2024-07-15 11:59:40', 1, '2024-07-15 12:01:16', 1);
INSERT INTO `scenario` VALUES (37, 'untitled', 1, 974, 210, 80, 40, '8e2ddfab-24d5-4d4e-9ad4-d7a2fc06ff97', 0, 1, NULL, 1, '2024-07-15 12:52:10', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (38, 'untitled', 1, 1054, 250, 80, 40, '61e8e264-51af-4e9d-89de-b9ddefd0d2d6', 0, 1, NULL, 1, '2024-07-15 12:54:46', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (39, 'untitled', 1, 1030, 210, 80, 40, '02bec743-704e-4693-bbec-dfea0bbd3775', 0, 1, NULL, 1, '2024-07-15 12:55:44', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (40, 'sth', 1, 700, 470, 40, 30, '0f79cb1f-3b85-497f-97d8-8d2b6617f4f4', 0, 1, NULL, 1, '2024-07-15 12:57:04', 1, '2024-07-15 14:41:57', 1);
INSERT INTO `scenario` VALUES (41, 'test', 1, 540, 275, 80, 40, 'be9be52a-4468-4600-8b10-54827b15f2bd', 0, 1, NULL, 1, '2024-07-15 13:00:09', 1, '2024-07-16 15:14:38', 1);
INSERT INTO `scenario` VALUES (42, 'untitled', 1, 180, 100, 80, 40, '10e3644d-836d-47a2-ad55-7538f4efa9fd', 0, 1, NULL, 1, '2024-07-16 15:14:20', NULL, NULL, 1);
INSERT INTO `scenario` VALUES (43, 'scenario1', 1, 560, 110, 80, 40, '3cf504ad-a1ea-405f-9a7f-bb9c95536300', 0, 1, NULL, 1, '2024-07-19 22:30:37', NULL, NULL, 0);
INSERT INTO `scenario` VALUES (44, 'scenario2', 1, 560, 300, 80, 40, 'b832f7ad-e0f8-4a20-9e9f-2af236a26020', 0, 1, NULL, 1, '2024-07-19 22:30:37', NULL, NULL, 0);
INSERT INTO `scenario` VALUES (45, 'scenario3', 1, 740, 200, 80, 40, '52bb3e6e-3108-4953-be40-aa424038d70f', 0, 1, NULL, 1, '2024-07-19 22:30:37', NULL, NULL, 0);

-- ----------------------------
-- Table structure for scenario_collaborator
-- ----------------------------
DROP TABLE IF EXISTS `scenario_collaborator`;
CREATE TABLE `scenario_collaborator`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '数据库标识自增字段',
  `scenario_id` int NOT NULL DEFAULT 1 COMMENT '场景id',
  `user_id` int NOT NULL DEFAULT 1 COMMENT '协作者id',
  `permission_level` enum('none','read','write','admin') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'none',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user_id` int NOT NULL DEFAULT 1 COMMENT '创建该条目的用户id，默认由超级管理员创建（0）',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` int NULL DEFAULT NULL COMMENT '修改该条目的用户id',
  `modify_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已被删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_constraint_1`(`id` ASC) USING BTREE,
  INDEX `fk_user_id`(`user_id` ASC) USING BTREE,
  INDEX `fk_create_user_id`(`create_user_id` ASC) USING BTREE,
  INDEX `fk_scenario_id`(`scenario_id` ASC) USING BTREE,
  CONSTRAINT `fk_create_user_id_scenario_collaborator` FOREIGN KEY (`create_user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_scenario_id` FOREIGN KEY (`scenario_id`) REFERENCES `scenario` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_id_scenario_collaborator` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of scenario_collaborator
-- ----------------------------
INSERT INTO `scenario_collaborator` VALUES (1, 1, 1, 'admin', NULL, 1, '2024-07-10 11:58:24', NULL, NULL, 0);
INSERT INTO `scenario_collaborator` VALUES (2, 1, 2, 'write', NULL, 1, '2024-07-10 11:58:46', NULL, NULL, 0);
INSERT INTO `scenario_collaborator` VALUES (3, 1, 3, 'admin', NULL, 1, '2024-07-10 11:58:55', NULL, NULL, 0);
INSERT INTO `scenario_collaborator` VALUES (4, 1, 12, 'admin', NULL, 1, '2024-07-10 11:59:07', NULL, NULL, 0);
INSERT INTO `scenario_collaborator` VALUES (5, 1, 6, 'read', NULL, 1, '2024-07-10 11:59:14', NULL, NULL, 0);

-- ----------------------------
-- Table structure for scenario_relation
-- ----------------------------
DROP TABLE IF EXISTS `scenario_relation`;
CREATE TABLE `scenario_relation`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '数据库标识自增字段',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '关系名',
  `project_id` int NOT NULL DEFAULT 1,
  `source_id` int NOT NULL DEFAULT 1 COMMENT '源场景的id',
  `target_id` int NOT NULL DEFAULT 1 COMMENT '目标场景的id',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '类型，可能是无用字段',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user_id` int NOT NULL DEFAULT 1 COMMENT '创建该条目的用户id，默认由超级管理员创建（0）',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` int NULL DEFAULT NULL COMMENT '修改该条目的用户id',
  `modify_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已被删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_constraint_1`(`id` ASC) USING BTREE,
  INDEX `fk_user_id`(`create_user_id` ASC) USING BTREE,
  INDEX `fk_source_id`(`source_id` ASC) USING BTREE,
  INDEX `fk_target_id`(`target_id` ASC) USING BTREE,
  INDEX `fk_project_id_scenario_relation`(`project_id` ASC) USING BTREE,
  CONSTRAINT `fk_project_id_scenario_relation` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_source_id` FOREIGN KEY (`source_id`) REFERENCES `scenario` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_target_id` FOREIGN KEY (`target_id`) REFERENCES `scenario` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_id_scenarioRelation` FOREIGN KEY (`create_user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of scenario_relation
-- ----------------------------
INSERT INTO `scenario_relation` VALUES (1, '', 1, 1, 2, 0, NULL, 1, '2024-07-10 11:57:12', 1, '2024-07-16 15:14:38', 1);
INSERT INTO `scenario_relation` VALUES (2, '', 1, 2, 1, 0, NULL, 1, '2024-07-10 11:57:22', 1, '2024-07-16 15:14:38', 1);
INSERT INTO `scenario_relation` VALUES (3, 'derive', 1, 1, 3, 0, NULL, 1, '2024-07-10 11:57:30', NULL, NULL, 1);
INSERT INTO `scenario_relation` VALUES (4, 'derive', 1, 3, 4, 0, NULL, 1, '2024-07-12 21:39:27', NULL, NULL, 1);
INSERT INTO `scenario_relation` VALUES (5, '', 1, 2, 4, 0, '', 1, '2024-07-12 21:39:28', 1, '2024-07-16 15:14:38', 1);
INSERT INTO `scenario_relation` VALUES (6, '', 1, 2, 3, 0, 'string', 1, '2024-07-12 21:39:30', NULL, NULL, 1);
INSERT INTO `scenario_relation` VALUES (7, 'test', 1, 4, 5, 0, NULL, 1, '2024-07-13 19:00:03', 1, '2024-07-16 15:14:38', 1);
INSERT INTO `scenario_relation` VALUES (8, '', 1, 6, 12, 0, NULL, 1, '2024-07-15 09:52:22', 1, '2024-07-15 10:03:17', 1);
INSERT INTO `scenario_relation` VALUES (9, '', 1, 6, 11, 0, NULL, 1, '2024-07-15 10:03:16', NULL, NULL, 1);
INSERT INTO `scenario_relation` VALUES (10, '', 1, 4, 11, 0, NULL, 1, '2024-07-15 10:04:26', 1, '2024-07-15 12:57:52', 1);
INSERT INTO `scenario_relation` VALUES (12, '', 1, 22, 40, 0, NULL, 1, '2024-07-15 12:57:52', 1, '2024-07-15 14:41:57', 1);
INSERT INTO `scenario_relation` VALUES (13, '', 1, 4, 22, 0, NULL, 1, '2024-07-15 13:00:09', 1, '2024-07-15 14:41:57', 1);
INSERT INTO `scenario_relation` VALUES (14, '', 1, 2, 41, 0, NULL, 1, '2024-07-15 13:00:09', 1, '2024-07-16 15:14:38', 1);
INSERT INTO `scenario_relation` VALUES (15, 'Isit?', 1, 42, 41, 0, NULL, 1, '2024-07-16 15:14:20', NULL, NULL, 1);
INSERT INTO `scenario_relation` VALUES (16, '', 1, 43, 44, 0, NULL, 1, '2024-07-19 22:30:37', NULL, NULL, 0);
INSERT INTO `scenario_relation` VALUES (17, '', 1, 44, 45, 0, NULL, 1, '2024-07-19 22:30:37', NULL, NULL, 0);
INSERT INTO `scenario_relation` VALUES (18, '', 1, 45, 43, 0, NULL, 1, '2024-07-19 22:30:37', NULL, NULL, 0);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '数据库标识',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '注册用邮箱',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'http://localhost:8080/avatar/default_avatar.jpg',
  `type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '用户类型，超级管理员（0）',
  `is_enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user_id` int NOT NULL DEFAULT 1 COMMENT '创建该用户的用户id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` int NULL DEFAULT NULL COMMENT '修改该用户的用户id',
  `modify_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已被删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_constraint_1`(`id` ASC) USING BTREE,
  UNIQUE INDEX `uk_constraint_2`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '超级管理员', 'admin@outlook.com', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 'http://127.0.0.1:8898/assets/avatar/default_avatar.jpg', 0, 1, NULL, 1, '2024-07-19 16:13:40', NULL, NULL, 0);
INSERT INTO `users` VALUES (2, 'test1', 'test1@outlook.com', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 'http://127.0.0.1:8898/assets/avatar/b25cbc16-a15c-47fb-a422-d5ccaf0f7d7b.png', 0, 1, NULL, 1, '2024-07-19 16:34:54', NULL, NULL, 0);
INSERT INTO `users` VALUES (3, 'test', 'test@outlook.com', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 'http://127.0.0.1:8898/assets/avatar/971016c6-63f4-41b2-ae4e-650b69cc675a.png', 0, 1, NULL, 0, '2024-07-19 16:34:37', NULL, NULL, 0);
INSERT INTO `users` VALUES (6, 'string', 'string', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 'http://127.0.0.1:8898/assets/avatar/default_avatar.jpg', 1, 1, NULL, 0, '2024-07-19 16:13:47', NULL, NULL, 0);
INSERT INTO `users` VALUES (10, 'string', 'string2', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 'http://127.0.0.1:8898/assets/avatar/default_avatar.jpg', 1, 1, NULL, 10, '2024-07-19 16:13:48', NULL, NULL, 0);
INSERT INTO `users` VALUES (11, 'string', 'string3', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 'http://127.0.0.1:8898/assets/avatar/default_avatar.jpg', 1, 1, NULL, 11, '2024-07-19 16:13:49', NULL, NULL, 0);
INSERT INTO `users` VALUES (12, 'string', 'string4', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 'http://127.0.0.1:8898/assets/avatar/default_avatar.jpg', 1, 1, NULL, 12, '2024-07-19 16:13:52', NULL, NULL, 0);

-- ----------------------------
-- Triggers structure for table users
-- ----------------------------
DROP TRIGGER IF EXISTS `set_create_user_id`;
delimiter ;;
CREATE TRIGGER `set_create_user_id` BEFORE INSERT ON `users` FOR EACH ROW BEGIN
    SET NEW.create_user_id = NEW.id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table users
-- ----------------------------
DROP TRIGGER IF EXISTS `after_user_insert`;
delimiter ;;
CREATE TRIGGER `after_user_insert` AFTER INSERT ON `users` FOR EACH ROW BEGIN
  DECLARE finished INTEGER DEFAULT 0;
  DECLARE item_id INT;
  DECLARE item_cursor CURSOR FOR SELECT id FROM item;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = 1;

  OPEN item_cursor;

  fetch_item: LOOP
    FETCH item_cursor INTO item_id;
    IF finished THEN
      LEAVE fetch_item;
    END IF;

    INSERT INTO item_access (user_id, item_id, permission_level, create_user_id)
    VALUES (NEW.id, item_id, 'none', NEW.id);
  END LOOP;

  CLOSE item_cursor;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
