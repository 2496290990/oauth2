/*
 Navicat Premium Data Transfer

 Source Server         : 本机Mysql
 Source Server Type    : MySQL
 Source Server Version : 50631
 Source Host           : localhost:3306
 Source Schema         : zs_im

 Target Server Type    : MySQL
 Target Server Version : 50631
 File Encoding         : 65001

 Date: 17/03/2021 17:54:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for im_chat_group
-- ----------------------------
DROP TABLE IF EXISTS `im_chat_group`;
CREATE TABLE `im_chat_group`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `create_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建这id',
  `group_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '群名称',
  `group_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '群简介',
  `group_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '群头像',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `group_tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '群标签',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `owner_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '群主id',
  `level` int(10) NULL DEFAULT NULL COMMENT '群等级',
  `by1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by5` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by6` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `del_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0伤处1正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '聊天群组表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_chat_group
-- ----------------------------

-- ----------------------------
-- Table structure for im_chat_history
-- ----------------------------
DROP TABLE IF EXISTS `im_chat_history`;
CREATE TABLE `im_chat_history`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `sender_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发送者id',
  `recipient` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接受人id',
  `group_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '群组id',
  `message_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '消息类型',
  `text` varchar(2550) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '消息内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `send_del` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0删除 1展示',
  `receive_del` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0删除1展示',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件地址',
  `by1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by5` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by6` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '聊天记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_chat_history
-- ----------------------------
INSERT INTO `im_chat_history` VALUES ('1364224952860971010', '2496290990', '2142611459', '', 'string', '，我们现在是好友啦，开始聊天吧', '2021-02-23 22:45:49', '1', '1', '', 'string', 'string', 'string', 'string', 'string', 'string');
INSERT INTO `im_chat_history` VALUES ('1364225055298457601', '2142611459', '2496290990', '', 'string', '，我们现在是好友啦，开始聊天吧', '2021-02-23 22:46:14', '1', '1', '', 'string', 'string', 'string', 'string', 'string', 'string');

-- ----------------------------
-- Table structure for im_circle_of_friend
-- ----------------------------
DROP TABLE IF EXISTS `im_circle_of_friend`;
CREATE TABLE `im_circle_of_friend`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `utterer` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发表人id',
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发表的文字内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0已删除 1正常',
  `by1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by5` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '动态记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_circle_of_friend
-- ----------------------------
INSERT INTO `im_circle_of_friend` VALUES ('566706221177516032', NULL, NULL, '2021-03-09 16:49:18', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_circle_of_friend` VALUES ('566708890944286720', NULL, NULL, '2021-03-09 16:59:54', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_circle_of_friend` VALUES ('566709603128717312', '123', '测试朋友圈内容', '2021-03-09 17:02:44', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for im_circle_oss
-- ----------------------------
DROP TABLE IF EXISTS `im_circle_oss`;
CREATE TABLE `im_circle_oss`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `circle_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '动态id',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序字段',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件url',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `del_flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0删除1正常',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `by1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by5` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '朋友圈文件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_circle_oss
-- ----------------------------
INSERT INTO `im_circle_oss` VALUES ('566708903187460096', '566708890944286720', 0, 'https://ftp.bmp.ovh/imgs/2021/02/d4613b0d8dc3ba77.jpg', '2021-03-09 17:00:00', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_circle_oss` VALUES ('566708909197897728', '566708890944286720', 1, 'https://ftp.bmp.ovh/imgs/2021/02/4e6f90e873bc999d.jpg', '2021-03-09 17:00:00', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_circle_oss` VALUES ('566708913908101120', '566708890944286720', 2, 'https://ftp.bmp.ovh/imgs/2021/02/e3866cb5102fab98.jpg', '2021-03-09 17:00:00', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_circle_oss` VALUES ('566709622103748608', '566709603128717312', 0, 'https://ftp.bmp.ovh/imgs/2021/02/59c11a585c0259b4.jpg', '2021-03-09 17:02:55', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_circle_oss` VALUES ('566709636477628416', '566709603128717312', 1, 'https://ftp.bmp.ovh/imgs/2021/02/e0fedfe560b82c68.jpg', '2021-03-09 17:02:55', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_circle_oss` VALUES ('566709650071367680', '566709603128717312', 2, 'https://ftp.bmp.ovh/imgs/2021/02/830eed10810f32f6.jpg', '2021-03-09 17:02:55', '1', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for im_circle_review
-- ----------------------------
DROP TABLE IF EXISTS `im_circle_review`;
CREATE TABLE `im_circle_review`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '雪花id',
  `circle_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '朋友圈id',
  `review_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评论人的id',
  `review_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评论内容',
  `parent_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '父级id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '删除标记-0已删除1正常',
  `by1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用字段',
  `by2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sel_id`(`id`) USING BTREE COMMENT '主键id索引',
  INDEX `sel_pre`(`parent_id`) USING BTREE COMMENT '父级id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '朋友圈评论表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_circle_review
-- ----------------------------
INSERT INTO `im_circle_review` VALUES ('568534766792355840', '123', '123', '这是一条json格式的评论', NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for im_friend_apply
-- ----------------------------
DROP TABLE IF EXISTS `im_friend_apply`;
CREATE TABLE `im_friend_apply`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `proposer` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '申请人',
  `friend_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '好友id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '申请理由',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人id',
  `state` int(10) NULL DEFAULT NULL COMMENT '0 拒绝 1 同意',
  `by1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by5` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '好友申请记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_friend_apply
-- ----------------------------

-- ----------------------------
-- Table structure for im_friend_group
-- ----------------------------
DROP TABLE IF EXISTS `im_friend_group`;
CREATE TABLE `im_friend_group`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键雪花id',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户id',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分组名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `group_total` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分组人数',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '好友分组设置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_friend_group
-- ----------------------------

-- ----------------------------
-- Table structure for im_group_apply
-- ----------------------------
DROP TABLE IF EXISTS `im_group_apply`;
CREATE TABLE `im_group_apply`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键雪花id',
  `apply_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '申请者id',
  `apply_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '申请理由',
  `group_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '群组id',
  `inform_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '通知人id',
  `state` int(10) NULL DEFAULT NULL COMMENT '0拒绝1同意',
  `confirm_user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '处理人id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(10) NULL DEFAULT NULL COMMENT '0删除1正常',
  `by1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用字段',
  `by2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by5` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '群组申请明细表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_group_apply
-- ----------------------------

-- ----------------------------
-- Table structure for im_group_member
-- ----------------------------
DROP TABLE IF EXISTS `im_group_member`;
CREATE TABLE `im_group_member`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `group_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '群组id',
  `type` int(10) NULL DEFAULT NULL COMMENT '0群主1管理员2普通成员',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '群员账号',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '群昵称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '加入时间',
  `by1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by5` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by6` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '群组成员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_group_member
-- ----------------------------

-- ----------------------------
-- Table structure for im_my_friend
-- ----------------------------
DROP TABLE IF EXISTS `im_my_friend`;
CREATE TABLE `im_my_friend`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `my_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '我的id',
  `friend_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '好友id',
  `particular` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0 正常好友 1特别关系',
  `blocked` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0正常 1 黑名单 2被拉黑',
  `del_flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0删除 1正常',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简介',
  `friend_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '好友分组',
  `by1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '不要',
  `by2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '好友关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_my_friend
-- ----------------------------

-- ----------------------------
-- Table structure for im_review_oss
-- ----------------------------
DROP TABLE IF EXISTS `im_review_oss`;
CREATE TABLE `im_review_oss`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `review_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评论的id',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片地址',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '删除标记0已删除1正常',
  `by1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用字段1',
  `by2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '评论图片表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_review_oss
-- ----------------------------
INSERT INTO `im_review_oss` VALUES ('568564365991489536', '123', 'http://www.baidu.com', 1, '2021-03-14 19:52:54', NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for im_user
-- ----------------------------
DROP TABLE IF EXISTS `im_user`;
CREATE TABLE `im_user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '生成的雪花id',
  `account` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '随机盐',
  `sex` int(10) NULL DEFAULT NULL COMMENT '0女1男2保密',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `del_flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0删除1正常2封禁',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `account_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像地址',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简介',
  `birth` date NULL DEFAULT NULL COMMENT '生日',
  `by1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用1',
  `by2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用2',
  `by3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用3',
  `by4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用4',
  `by5` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用5',
  `by6` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用6',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_sel`(`id`) USING BTREE COMMENT 'id',
  INDEX `account_index`(`account`) USING BTREE COMMENT '账号'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_user
-- ----------------------------
INSERT INTO `im_user` VALUES ('569604250817671168', '372322840', '$2a$10$JU2Zo4te7u/AOoKSANq0XehBhyTGHNpXd7CZElQDKP7.WEWjWm23G', 'MIUi59-', 0, '17712345678', '1234@qq.com', '2021-03-17 16:45:07', '1', NULL, 'https://ftp.bmp.ovh/imgs/2021/02/830eed10810f32f6.jpg', '郑抒', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_user` VALUES ('569619660615069696', '790771525', '$2a$10$IVT4T9vvxygusUKD4v1q9eMG.BsqNok92njR1NMAoy6itsW0DrwE.', 'tX9?q2h', 0, '17712345679', '1234@qq.com', '2021-03-17 17:46:16', '1', NULL, 'https://ftp.bmp.ovh/imgs/2021/02/830eed10810f32f6.jpg', '测试用户', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for im_verify_log
-- ----------------------------
DROP TABLE IF EXISTS `im_verify_log`;
CREATE TABLE `im_verify_log`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT ' 主键id',
  `register_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '注册的邮箱或者是手机号',
  `verify_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '验证码',
  `type` int(10) NULL DEFAULT NULL COMMENT '0注册 1修改资料',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `by1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `by5` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '验证码记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of im_verify_log
-- ----------------------------
INSERT INTO `im_verify_log` VALUES ('561207641905967104', 'zjh292411@gmail.com', '590070', 0, '2021-02-22 12:39:55', '2099-02-22 12:42:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_verify_log` VALUES ('561207641905967105', 'zjh292411@gmail.com', '590071', 0, '2021-02-22 12:42:55', '2099-02-22 12:42:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_verify_log` VALUES ('561572389386334208', '2496290990@qq.com', '590307', 0, '2021-02-23 12:49:17', '2021-02-23 12:52:17', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_verify_log` VALUES ('561573192998203392', '2496290990@qq.com', '145710', 0, '2021-02-23 12:52:28', '2021-02-23 12:55:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_verify_log` VALUES ('561576567621955584', '2496290990@qq.com', '197263', 0, '2021-02-23 13:05:53', '2021-02-23 13:08:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_verify_log` VALUES ('561588411703111680', '2142611459@qq.com', '033268', 0, '2021-02-23 13:52:57', '2021-02-23 13:55:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `im_verify_log` VALUES ('569578580150464512', '2142611459@qq.com', '484149', 0, '2021-03-17 15:03:09', '2021-03-17 15:06:01', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
