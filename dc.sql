/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : dc

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 12/06/2024 14:43:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '数据库标识自增字段',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '条目名',
  `pid` int NOT NULL DEFAULT -1 COMMENT '父条目的category_id，-1表示其就是根节点',
  `level` int NOT NULL DEFAULT 1 COMMENT '层级',
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
  CONSTRAINT `fk_user_id` FOREIGN KEY (`create_user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '水', -1, 1, 0, 1, NULL, 1, '2024-04-20 20:31:27', 1, '2024-05-09 16:39:04', 0);
INSERT INTO `category` VALUES (2, '土', -1, 1, 0, 1, NULL, 1, '2024-04-20 18:58:16', NULL, NULL, 0);
INSERT INTO `category` VALUES (3, '气', -1, 1, 0, 1, NULL, 1, '2024-04-20 18:58:16', NULL, NULL, 0);
INSERT INTO `category` VALUES (4, '生', -1, 1, 0, 1, NULL, 1, '2024-04-20 18:58:16', NULL, NULL, 0);
INSERT INTO `category` VALUES (5, '水文循环', 1, 2, 0, 1, NULL, 1, '2024-05-08 21:18:52', NULL, NULL, 0);
INSERT INTO `category` VALUES (6, '洪水', 1, 2, 0, 1, NULL, 1, '2024-04-20 20:31:27', NULL, NULL, 0);
INSERT INTO `category` VALUES (7, '水生态系统', 1, 2, 0, 1, NULL, 1, '2024-04-20 20:31:27', NULL, NULL, 0);
INSERT INTO `category` VALUES (8, '水质', 1, 2, 0, 1, NULL, 1, '2024-04-20 20:31:27', NULL, NULL, 0);
INSERT INTO `category` VALUES (9, 'string', 5, 3, 0, 1, '', 1, '2024-05-08 21:18:52', 1, '2024-04-20 16:04:24', 0);
INSERT INTO `category` VALUES (10, 'string', 5, 3, 0, 1, 'string', 1, '2024-05-08 21:18:52', 1, '2024-04-20 19:00:14', 0);

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '数据库标识自增字段',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '条目名',
  `pid` int NOT NULL DEFAULT 1 COMMENT '父条目的category_id，即所属目录',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '类型，可能是无用字段',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '研究区域',
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `size` bigint NOT NULL DEFAULT 0 COMMENT '存储字节数',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接，或者path，待议',
  `is_enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用，默认启用（1）',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user_id` int NOT NULL DEFAULT 1 COMMENT '创建该条目的用户id，默认由超级管理员创建（0）',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` int NULL DEFAULT NULL COMMENT '修改该条目的用户id',
  `modify_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已被删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_constraint`(`id` ASC) USING BTREE,
  UNIQUE INDEX `uk_constraint_item`(`id` ASC) USING BTREE,
  INDEX `fk_user_id_item`(`create_user_id` ASC) USING BTREE,
  INDEX `fk_pid`(`pid` ASC) USING BTREE,
  CONSTRAINT `fk_pid` FOREIGN KEY (`pid`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_id_item` FOREIGN KEY (`create_user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (1, 'item1', 1, 0, '南京', '摘要', 1000, NULL, 1, NULL, 1, '2024-04-20 20:31:27', NULL, NULL, 0);
INSERT INTO `item` VALUES (2, 'item2', 5, 0, '南京', '摘要', 0, NULL, 1, NULL, 1, '2024-05-08 21:18:52', NULL, NULL, 0);
INSERT INTO `item` VALUES (3, 'testFromSwagger', 1, 0, '', 'summary', 0, '', 1, '', 1, '2024-04-20 20:31:27', NULL, NULL, 0);
INSERT INTO `item` VALUES (4, 'string', 1, 0, 'string', 'string', 0, 'string', 1, 'string', 1, '2024-04-20 20:31:27', NULL, NULL, 0);
INSERT INTO `item` VALUES (5, 'string', 1, 0, 'string', 'string', 0, 'string', 1, 'string', 1, '2024-04-20 20:31:27', NULL, NULL, 0);
INSERT INTO `item` VALUES (6, 'string', 1, 0, 'string', 'string', 0, 'string', 1, 'string', 1, '2024-04-20 20:31:27', NULL, NULL, 0);
INSERT INTO `item` VALUES (7, 'string', 1, 0, 'string', 'string', 0, 'string', 1, 'string', 1, '2024-04-20 20:31:27', NULL, NULL, 0);
INSERT INTO `item` VALUES (8, 'test', 1, 0, '浦西', '浦西', 0, NULL, 1, NULL, 1, '2024-06-12 11:17:39', NULL, NULL, 0);

-- ----------------------------
-- Table structure for item_access
-- ----------------------------
DROP TABLE IF EXISTS `item_access`;
CREATE TABLE `item_access`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '数据库标识自增字段',
  `user_id` int NOT NULL DEFAULT 1 COMMENT '用户id',
  `item_id` int NOT NULL DEFAULT 1 COMMENT '数据项id',
  `permission_level` enum('none','read','write','admin') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'none',
  `is_enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用，默认启用（1）',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user_id` int NOT NULL DEFAULT 1 COMMENT '创建该条目的用户id，默认由超级管理员创建（0）',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` int NULL DEFAULT NULL COMMENT '修改该条目的用户id',
  `modify_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已被删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_constraint`(`id` ASC) USING BTREE,
  UNIQUE INDEX `uk_constraint_item`(`id` ASC) USING BTREE,
  INDEX `fk_user_id_itemAccess`(`user_id` ASC) USING BTREE,
  INDEX `fk_item_id`(`item_id` ASC) USING BTREE,
  CONSTRAINT `fk_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_id_itemAccess` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of item_access
-- ----------------------------
INSERT INTO `item_access` VALUES (8, 12, 1, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (9, 11, 1, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (10, 10, 1, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (11, 6, 1, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (12, 3, 1, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (13, 2, 1, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (14, 1, 1, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (15, 12, 2, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (16, 11, 2, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (17, 10, 2, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (18, 6, 2, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (19, 3, 2, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (20, 2, 2, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (21, 1, 2, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (22, 12, 3, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (23, 11, 3, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (24, 10, 3, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (25, 6, 3, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (26, 3, 3, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (27, 2, 3, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (28, 1, 3, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (29, 12, 4, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (30, 11, 4, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (31, 10, 4, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (32, 6, 4, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (33, 3, 4, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (34, 2, 4, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (35, 1, 4, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (36, 12, 5, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (37, 11, 5, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (38, 10, 5, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (39, 6, 5, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (40, 3, 5, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (41, 2, 5, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (42, 1, 5, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (43, 12, 6, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (44, 11, 6, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (45, 10, 6, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (46, 6, 6, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (47, 3, 6, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (48, 2, 6, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (49, 1, 6, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (50, 12, 7, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (51, 11, 7, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (52, 10, 7, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (53, 6, 7, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (54, 3, 7, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (55, 2, 7, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (56, 1, 7, 'none', 1, NULL, 1, '2024-06-12 11:16:47', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (71, 1, 8, 'none', 1, NULL, 1, '2024-06-12 11:17:39', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (72, 2, 8, 'none', 1, NULL, 1, '2024-06-12 11:17:39', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (73, 3, 8, 'none', 1, NULL, 1, '2024-06-12 11:17:39', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (74, 6, 8, 'none', 1, NULL, 1, '2024-06-12 11:17:39', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (75, 10, 8, 'none', 1, NULL, 1, '2024-06-12 11:17:39', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (76, 11, 8, 'none', 1, NULL, 1, '2024-06-12 11:17:39', NULL, NULL, 0);
INSERT INTO `item_access` VALUES (77, 12, 8, 'none', 1, NULL, 1, '2024-06-12 11:17:39', NULL, NULL, 0);

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
-- Triggers structure for table item
-- ----------------------------
DROP TRIGGER IF EXISTS `after_item_insert`;
delimiter ;;
CREATE TRIGGER `after_item_insert` AFTER INSERT ON `item` FOR EACH ROW BEGIN
  DECLARE finished INTEGER DEFAULT 0;
  DECLARE user_id INT;
  DECLARE user_cursor CURSOR FOR SELECT id FROM users;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = 1;

  OPEN user_cursor;

  fetch_user: LOOP
    FETCH user_cursor INTO user_id;
    IF finished THEN
      LEAVE fetch_user;
    END IF;

    INSERT INTO item_access (user_id, item_id, permission_level, create_user_id)
    VALUES (user_id, NEW.id, 'none', 1);
  END LOOP;

  CLOSE user_cursor;
END
;;
delimiter ;

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
