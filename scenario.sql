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

 Date: 11/07/2024 21:50:47
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
-- Table structure for scenario
-- ----------------------------
DROP TABLE IF EXISTS `scenario`;
CREATE TABLE `scenario`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '数据库标识自增字段',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '场景名',
  `project_id` int NOT NULL DEFAULT 1 COMMENT '场景所属项目的id',
  `graph_x` float NOT NULL DEFAULT 0 COMMENT 'Graph中X坐标',
  `graph_y` float NOT NULL DEFAULT 0 COMMENT 'Graph中Y坐标',
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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of scenario
-- ----------------------------
INSERT INTO `scenario` VALUES (1, 'scenario1', 1, 0, 0, 0, 1, NULL, 1, '2024-07-10 11:46:36', NULL, NULL, 0);
INSERT INTO `scenario` VALUES (2, 'scenario2', 1, 0, 0, 0, 1, NULL, 1, '2024-07-10 11:46:49', NULL, NULL, 0);
INSERT INTO `scenario` VALUES (3, 'scenario3', 1, 0, 0, 0, 1, NULL, 1, '2024-07-10 11:46:55', NULL, NULL, 0);
INSERT INTO `scenario` VALUES (4, 'scenario4', 1, 0, 0, 0, 1, NULL, 1, '2024-07-10 11:47:02', NULL, NULL, 0);
INSERT INTO `scenario` VALUES (5, 'scenario5', 1, 0, 0, 0, 1, NULL, 1, '2024-07-10 11:47:09', NULL, NULL, 0);

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
  CONSTRAINT `fk_source_id` FOREIGN KEY (`source_id`) REFERENCES `scenario` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_target_id` FOREIGN KEY (`target_id`) REFERENCES `scenario` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_id_scenarioRelation` FOREIGN KEY (`create_user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of scenario_relation
-- ----------------------------
INSERT INTO `scenario_relation` VALUES (1, 'derive', 1, 2, 0, NULL, 1, '2024-07-10 11:57:12', NULL, NULL, 0);
INSERT INTO `scenario_relation` VALUES (2, 'extend', 2, 1, 0, NULL, 1, '2024-07-10 11:57:22', NULL, NULL, 0);
INSERT INTO `scenario_relation` VALUES (3, 'derive', 1, 3, 0, NULL, 1, '2024-07-10 11:57:30', NULL, NULL, 0);
INSERT INTO `scenario_relation` VALUES (4, 'derive', 3, 4, 0, NULL, 1, '2024-07-10 11:57:39', NULL, NULL, 0);

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
INSERT INTO `users` VALUES (1, '超级管理员', 'admin@outlook.com', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 0, 1, NULL, 1, '2024-05-08 15:54:58', NULL, NULL, 0);
INSERT INTO `users` VALUES (2, 'test1', 'test1@outlook.com', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 1, 1, NULL, 1, '2024-05-08 15:54:59', NULL, NULL, 0);
INSERT INTO `users` VALUES (3, 'test', 'test@outlook.com', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 1, 1, NULL, 0, '2024-05-08 15:55:01', NULL, NULL, 0);
INSERT INTO `users` VALUES (6, 'string', 'string', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 1, 1, NULL, 0, '2024-05-08 15:55:03', NULL, NULL, 0);
INSERT INTO `users` VALUES (10, 'string', 'string2', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 1, 1, NULL, 10, '2024-05-08 15:55:04', NULL, NULL, 0);
INSERT INTO `users` VALUES (11, 'string', 'string3', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 1, 1, NULL, 11, '2024-05-08 15:55:12', NULL, NULL, 0);
INSERT INTO `users` VALUES (12, 'string', 'string4', NULL, '$2a$04$N/WYJBWvUgckNbOD5K0u3eKy4LqOEXb2.2teONH0b8b7kXugXqzX.', 1, 1, NULL, 12, '2024-05-08 15:54:46', NULL, NULL, 0);

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
