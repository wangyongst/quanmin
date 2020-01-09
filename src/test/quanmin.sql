/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : quanmin

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 09/01/2020 17:30:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `type` int(11) NULL DEFAULT NULL COMMENT '级别(0一级1二级2操作级）',
  `parentid` int(11) NULL DEFAULT NULL COMMENT '上级Id',
  `order` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES (13, '首页', 'index', 0, 0, 1);
INSERT INTO `admin_permission` VALUES (18, '内容管理', 'content', 0, 0, 3);
INSERT INTO `admin_permission` VALUES (24, '财务管理', 'finance', 0, 0, 4);
INSERT INTO `admin_permission` VALUES (28, '权限中心', 'permission', 0, 0, 5);
INSERT INTO `admin_permission` VALUES (38, '新建', 'index-create', 2, 13, NULL);
INSERT INTO `admin_permission` VALUES (39, '查询', 'index-query', 2, 13, NULL);
INSERT INTO `admin_permission` VALUES (40, '修改', 'index-update', 2, 13, NULL);
INSERT INTO `admin_permission` VALUES (41, '删除', 'index-delete', 2, 13, NULL);
INSERT INTO `admin_permission` VALUES (42, '导出', 'index-export', 2, 13, NULL);
INSERT INTO `admin_permission` VALUES (43, '耗材类型', 'index-material', 2, 13, NULL);
INSERT INTO `admin_permission` VALUES (44, '查询', 'purch-query', 2, 18, NULL);
INSERT INTO `admin_permission` VALUES (45, '导出', 'purch-export', 2, 18, NULL);
INSERT INTO `admin_permission` VALUES (46, '新增', 'purch-create', 2, 18, NULL);
INSERT INTO `admin_permission` VALUES (47, '删除', 'purch-delete', 2, 18, NULL);
INSERT INTO `admin_permission` VALUES (48, '修改', 'purch-update', 2, 18, NULL);
INSERT INTO `admin_permission` VALUES (49, '查询', 'finance-query', 2, 24, NULL);
INSERT INTO `admin_permission` VALUES (50, '导出', 'finance-export', 2, 24, NULL);
INSERT INTO `admin_permission` VALUES (51, '填写发票号', 'finance-fapiao', 2, 24, NULL);
INSERT INTO `admin_permission` VALUES (52, '新建', 'permission-create', 2, 28, NULL);
INSERT INTO `admin_permission` VALUES (53, '查询', 'permission-query', 2, 28, NULL);
INSERT INTO `admin_permission` VALUES (54, '修改', 'permission-update', 2, 28, NULL);
INSERT INTO `admin_permission` VALUES (55, '删除', 'permission-delete', 2, 28, NULL);
INSERT INTO `admin_permission` VALUES (56, '撤回', 'purch-back', 2, 18, NULL);
INSERT INTO `admin_permission` VALUES (57, '审核', 'finance-yes', 2, 24, NULL);
INSERT INTO `admin_permission` VALUES (58, '发起', 'purch-start', 2, 18, NULL);
INSERT INTO `admin_permission` VALUES (59, '价格系数', 'purch-price', 2, 18, NULL);
INSERT INTO `admin_permission` VALUES (60, '用户管理', 'user', 0, 0, 2);

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (13, '系统管理员');
INSERT INTO `admin_role` VALUES (48, '测试角色');
INSERT INTO `admin_role` VALUES (51, '开发');
INSERT INTO `admin_role` VALUES (53, '项目');
INSERT INTO `admin_role` VALUES (54, '121321');

-- ----------------------------
-- Table structure for admin_role2permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_role2permission`;
CREATE TABLE `admin_role2permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NULL DEFAULT NULL COMMENT '角色外键',
  `permissionid` int(11) NULL DEFAULT NULL COMMENT '权限外键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 637 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role2permission
-- ----------------------------
INSERT INTO `admin_role2permission` VALUES (266, 13, 13);
INSERT INTO `admin_role2permission` VALUES (267, 13, 38);
INSERT INTO `admin_role2permission` VALUES (268, 13, 39);
INSERT INTO `admin_role2permission` VALUES (269, 13, 40);
INSERT INTO `admin_role2permission` VALUES (270, 13, 41);
INSERT INTO `admin_role2permission` VALUES (271, 13, 42);
INSERT INTO `admin_role2permission` VALUES (272, 13, 43);
INSERT INTO `admin_role2permission` VALUES (273, 13, 18);
INSERT INTO `admin_role2permission` VALUES (274, 13, 44);
INSERT INTO `admin_role2permission` VALUES (275, 13, 45);
INSERT INTO `admin_role2permission` VALUES (276, 13, 46);
INSERT INTO `admin_role2permission` VALUES (277, 13, 47);
INSERT INTO `admin_role2permission` VALUES (278, 13, 48);
INSERT INTO `admin_role2permission` VALUES (279, 13, 56);
INSERT INTO `admin_role2permission` VALUES (280, 13, 24);
INSERT INTO `admin_role2permission` VALUES (281, 13, 49);
INSERT INTO `admin_role2permission` VALUES (282, 13, 50);
INSERT INTO `admin_role2permission` VALUES (283, 13, 51);
INSERT INTO `admin_role2permission` VALUES (284, 13, 57);
INSERT INTO `admin_role2permission` VALUES (285, 13, 28);
INSERT INTO `admin_role2permission` VALUES (286, 13, 52);
INSERT INTO `admin_role2permission` VALUES (287, 13, 53);
INSERT INTO `admin_role2permission` VALUES (288, 13, 54);
INSERT INTO `admin_role2permission` VALUES (289, 13, 55);
INSERT INTO `admin_role2permission` VALUES (330, 13, 51);
INSERT INTO `admin_role2permission` VALUES (331, 13, 56);
INSERT INTO `admin_role2permission` VALUES (332, 13, 58);
INSERT INTO `admin_role2permission` VALUES (333, 13, 59);
INSERT INTO `admin_role2permission` VALUES (408, 51, 13);
INSERT INTO `admin_role2permission` VALUES (409, 51, 38);
INSERT INTO `admin_role2permission` VALUES (410, 51, 18);
INSERT INTO `admin_role2permission` VALUES (411, 51, 44);
INSERT INTO `admin_role2permission` VALUES (412, 51, 24);
INSERT INTO `admin_role2permission` VALUES (413, 51, 49);
INSERT INTO `admin_role2permission` VALUES (414, 51, 28);
INSERT INTO `admin_role2permission` VALUES (415, 51, 52);
INSERT INTO `admin_role2permission` VALUES (480, 54, 13);
INSERT INTO `admin_role2permission` VALUES (481, 54, 38);
INSERT INTO `admin_role2permission` VALUES (482, 54, 18);
INSERT INTO `admin_role2permission` VALUES (483, 54, 44);
INSERT INTO `admin_role2permission` VALUES (484, 54, 24);
INSERT INTO `admin_role2permission` VALUES (485, 54, 49);
INSERT INTO `admin_role2permission` VALUES (486, 54, 28);
INSERT INTO `admin_role2permission` VALUES (487, 54, 52);
INSERT INTO `admin_role2permission` VALUES (526, 48, 13);
INSERT INTO `admin_role2permission` VALUES (527, 48, 38);
INSERT INTO `admin_role2permission` VALUES (528, 48, 18);
INSERT INTO `admin_role2permission` VALUES (529, 48, 44);
INSERT INTO `admin_role2permission` VALUES (530, 48, 24);
INSERT INTO `admin_role2permission` VALUES (531, 48, 49);
INSERT INTO `admin_role2permission` VALUES (532, 48, 28);
INSERT INTO `admin_role2permission` VALUES (533, 48, 52);
INSERT INTO `admin_role2permission` VALUES (534, 48, 55);
INSERT INTO `admin_role2permission` VALUES (623, 53, 13);
INSERT INTO `admin_role2permission` VALUES (624, 53, 38);
INSERT INTO `admin_role2permission` VALUES (625, 53, 39);
INSERT INTO `admin_role2permission` VALUES (626, 53, 40);
INSERT INTO `admin_role2permission` VALUES (627, 53, 41);
INSERT INTO `admin_role2permission` VALUES (628, 53, 43);
INSERT INTO `admin_role2permission` VALUES (629, 53, 18);
INSERT INTO `admin_role2permission` VALUES (630, 53, 44);
INSERT INTO `admin_role2permission` VALUES (631, 53, 45);
INSERT INTO `admin_role2permission` VALUES (632, 53, 46);
INSERT INTO `admin_role2permission` VALUES (633, 53, 47);
INSERT INTO `admin_role2permission` VALUES (634, 53, 48);
INSERT INTO `admin_role2permission` VALUES (635, 53, 56);
INSERT INTO `admin_role2permission` VALUES (636, 53, 58);

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `createusername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `createtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `roleid` int(32) NULL DEFAULT NULL COMMENT '角色外键',
  `islock` int(11) NULL DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (23, '系统管理员', NULL, '21232f297a57a5a743894a0e4a801fc3', 'admin', '', '2019-06-14 13:56:29', 13, 1);
INSERT INTO `admin_user` VALUES (74, '倪瓒', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13909876543', '系统管理员', '2019-08-10 18:44:27', NULL, 0);
INSERT INTO `admin_user` VALUES (79, '开发', NULL, '25d55ad283aa400af464c76d713c07ad', '12345678', '系统管理员', '2019-08-18 10:02:40', 51, 1);
INSERT INTO `admin_user` VALUES (82, '葛付晖', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13761108797', '系统管理员', '2019-08-21 09:22:13', 53, 0);
INSERT INTO `admin_user` VALUES (83, '邓婷婷', NULL, 'e10adc3949ba59abbe56e057f20f883e', '18221486392', '系统管理员', '2019-08-21 09:22:58', 53, 1);
INSERT INTO `admin_user` VALUES (84, '汪泳', NULL, 'e10adc3949ba59abbe56e057f20f883e', '18119445588', '系统管理员', '2019-08-21 10:50:05', NULL, 0);
INSERT INTO `admin_user` VALUES (87, '王燕', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13661727640', '施尧', '2019-09-23 14:22:44', 13, 0);
INSERT INTO `admin_user` VALUES (88, '张勇', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13788976324', '施尧', '2019-09-24 14:48:42', NULL, 0);
INSERT INTO `admin_user` VALUES (90, '周亚萍', NULL, '5d93ceb70e2bf5daa84ec3d0cd2c731a', '13661724867', '施尧', '2019-10-01 15:49:37', 13, 1);
INSERT INTO `admin_user` VALUES (91, '楷崎', NULL, 'e10adc3949ba59abbe56e057f20f883e', '18121451290', '施尧', '2019-09-29 11:56:03', NULL, 0);
INSERT INTO `admin_user` VALUES (93, '施尧', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13482498847', '系统管理员', '2019-09-30 18:23:18', 13, 1);
INSERT INTO `admin_user` VALUES (94, '高建方', NULL, 'e10adc3949ba59abbe56e057f20f883e', '15094355386', '施尧', '2019-10-08 11:27:12', 13, 0);
INSERT INTO `admin_user` VALUES (95, '甘佩玲', NULL, 'e10adc3949ba59abbe56e057f20f883e', '18377566126', '施尧', '2019-10-08 11:28:11', 13, 0);
INSERT INTO `admin_user` VALUES (96, '金晶', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13816537427', '施尧', '2019-10-08 11:28:46', 13, 0);
INSERT INTO `admin_user` VALUES (97, '张书恒', NULL, 'e10adc3949ba59abbe56e057f20f883e', '18657299718', '施尧', '2019-10-08 11:45:25', 53, 1);
INSERT INTO `admin_user` VALUES (98, '永尚', NULL, 'e10adc3949ba59abbe56e057f20f883e', '15921009488', '施尧', '2019-10-09 10:22:38', NULL, 0);
INSERT INTO `admin_user` VALUES (99, '炫杨', NULL, 'e10adc3949ba59abbe56e057f20f883e', '15121008569', '施尧', '2019-10-09 10:24:19', NULL, 0);
INSERT INTO `admin_user` VALUES (100, '测试供应商', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13999999999', '施尧', '2019-10-09 11:01:31', NULL, 0);

-- ----------------------------
-- Table structure for front_user
-- ----------------------------
DROP TABLE IF EXISTS `front_user`;
CREATE TABLE `front_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方登录',
  `createtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册时间',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of front_user
-- ----------------------------
INSERT INTO `front_user` VALUES (23, '系统管理员', NULL, '21232f297a57a5a743894a0e4a801fc3', 'admin', NULL, '2019-06-14 13:56:29', '13');
INSERT INTO `front_user` VALUES (74, '倪瓒', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13909876543', '', '2019-08-10 18:44:27', NULL);
INSERT INTO `front_user` VALUES (79, '开发', NULL, '25d55ad283aa400af464c76d713c07ad', '12345678', NULL, '2019-08-18 10:02:40', '51');
INSERT INTO `front_user` VALUES (82, '葛付晖', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13761108797', '', '2019-08-21 09:22:13', '53');
INSERT INTO `front_user` VALUES (83, '邓婷婷', NULL, 'e10adc3949ba59abbe56e057f20f883e', '18221486392', '', '2019-08-21 09:22:58', '53');
INSERT INTO `front_user` VALUES (84, '汪泳', NULL, 'e10adc3949ba59abbe56e057f20f883e', '18119445588', 'o6kUd5A1CKjSmMOicIVQslLtFqj8', '2019-08-21 10:50:05', NULL);
INSERT INTO `front_user` VALUES (87, '王燕', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13661727640', 'o6kUd5JSHekXj6HlmBTj7l6VWkh8', '2019-09-23 14:22:44', '13');
INSERT INTO `front_user` VALUES (88, '张勇', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13788976324', NULL, '2019-09-24 14:48:42', NULL);
INSERT INTO `front_user` VALUES (90, '周亚萍', NULL, '5d93ceb70e2bf5daa84ec3d0cd2c731a', '13661724867', 'o6kUd5CkkFwtR8mW5GNDWkeEZ0yk', '2019-10-01 15:49:37', '13');
INSERT INTO `front_user` VALUES (91, '楷崎', NULL, 'e10adc3949ba59abbe56e057f20f883e', '18121451290', 'o6kUd5FtJcd9udMnowqPm7vaQjsk', '2019-09-29 11:56:03', NULL);
INSERT INTO `front_user` VALUES (93, '施尧', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13482498847', 'o6kUd5GHvDTrjXyFRrmNdYAAUUpw', '2019-09-30 18:23:18', '13');
INSERT INTO `front_user` VALUES (94, '高建方', NULL, 'e10adc3949ba59abbe56e057f20f883e', '15094355386', NULL, '2019-10-08 11:27:12', '13');
INSERT INTO `front_user` VALUES (95, '甘佩玲', NULL, 'e10adc3949ba59abbe56e057f20f883e', '18377566126', NULL, '2019-10-08 11:28:11', '13');
INSERT INTO `front_user` VALUES (96, '金晶', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13816537427', NULL, '2019-10-08 11:28:46', '13');
INSERT INTO `front_user` VALUES (97, '张书恒', NULL, 'e10adc3949ba59abbe56e057f20f883e', '18657299718', NULL, '2019-10-08 11:45:25', '53');
INSERT INTO `front_user` VALUES (98, '永尚', NULL, 'e10adc3949ba59abbe56e057f20f883e', '15921009488', 'o6kUd5KGKtXs9b8KgsqJtvSGaWIY', '2019-10-09 10:22:38', NULL);
INSERT INTO `front_user` VALUES (99, '炫杨', NULL, 'e10adc3949ba59abbe56e057f20f883e', '15121008569', 'o6kUd5BdE-FVGx4snSztWfER2RIw', '2019-10-09 10:24:19', NULL);
INSERT INTO `front_user` VALUES (100, '测试供应商', NULL, 'e10adc3949ba59abbe56e057f20f883e', '13999999999', 'o6kUd5LmmUILT0Gy2El6eWMl4oIo', '2019-10-09 11:01:31', NULL);

-- ----------------------------
-- Table structure for setting_class
-- ----------------------------
DROP TABLE IF EXISTS `setting_class`;
CREATE TABLE `setting_class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for setting_subject
-- ----------------------------
DROP TABLE IF EXISTS `setting_subject`;
CREATE TABLE `setting_subject`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科目名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for video_course
-- ----------------------------
DROP TABLE IF EXISTS `video_course`;
CREATE TABLE `video_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程标题',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程简介',
  `price` decimal(11, 2) NULL DEFAULT NULL COMMENT '课程价格',
  `type` int(11) NULL DEFAULT NULL COMMENT '收费类型（0免费1收费)',
  `subjectId` int(11) NULL DEFAULT NULL COMMENT '科目外键',
  `teacher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲师',
  `teacherInstroduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲师简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for video_lession
-- ----------------------------
DROP TABLE IF EXISTS `video_lession`;
CREATE TABLE `video_lession`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `courseId` int(11) NULL DEFAULT NULL COMMENT '课程外键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课时标题',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课时简介',
  `times` decimal(11, 2) NULL DEFAULT NULL COMMENT '课时时长',
  `type` int(11) NULL DEFAULT NULL COMMENT '试听课程（1免费)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
