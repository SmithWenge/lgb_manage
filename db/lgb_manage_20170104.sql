/*
Navicat MySQL Data Transfer

Source Server         : wenge
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : lgb_manage

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-01-04 19:26:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for lgb_adminuser
-- ----------------------------
DROP TABLE IF EXISTS `lgb_adminuser`;
CREATE TABLE `lgb_adminuser` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(45) NOT NULL,
  `adminLoginName` varchar(45) NOT NULL,
  `adminLoginPass` varchar(100) NOT NULL,
  `adminIsLock` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:否 1:是',
  `adminRole` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:管理员 2:学习管理 3:报名录入人员 4:财务人员 5:浏览 6:系主任',
  `adminEmail` varchar(45) NOT NULL COMMENT '管理员邮箱',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0',
  `adminIsChanged` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:否 1:是',
  `adminIsReturn` varchar(45) NOT NULL DEFAULT '0' COMMENT '0:否 1:是',
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
-- Records of lgb_adminuser
-- ----------------------------
INSERT INTO `lgb_adminuser` VALUES ('1', '平凡', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '0', '1', 'weiniyangfg@163.com', '0', '0', '0');
INSERT INTO `lgb_adminuser` VALUES ('18', '财务', 'caiwu', 'da3238691682424cd77f6c01b8e88f74ffd37abefe62b03e5013dc99f94facd0', '0', '4', 'caiwu@173.com', '0', '0', '0');
INSERT INTO `lgb_adminuser` VALUES ('19', '李白', 'xizhuren', '8885a8cd5044cc808f7d0c38359a217795b1dfb5b7fb14f16fe252c23e7a0b47', '0', '6', '1980849329@qq.com', '0', '0', '0');
INSERT INTO `lgb_adminuser` VALUES ('20', '杜甫', 'baoming', '09aa71d7933ebdf7e22f980446bcbad1e692711d5c511f7dc460bf2f9dc5f2f2', '0', '3', '1980849329@qq.com', '0', '0', '0');

-- ----------------------------
-- Table structure for lgb_changecourse
-- ----------------------------
DROP TABLE IF EXISTS `lgb_changecourse`;
CREATE TABLE `lgb_changecourse` (
  `changeCourseId` int(10) NOT NULL AUTO_INCREMENT,
  `courseId` int(10) NOT NULL COMMENT '新课程Id',
  `studentId` int(10) NOT NULL,
  `changeTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operationUser` varchar(255) NOT NULL,
  `financeFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否需要费用 0表示不需要 1表示需要 2费用已处理',
  `finance` int(10) NOT NULL DEFAULT '0' COMMENT '费用金额',
  `oldCourseActualTuition` int(255) NOT NULL DEFAULT '0' COMMENT '原来课程的实际收费',
  `oldCourseId` int(11) NOT NULL COMMENT '原来课程Id',
  `studentCourseId` int(11) NOT NULL COMMENT '原来选课记录',
  PRIMARY KEY (`changeCourseId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lgb_changecourse
-- ----------------------------

-- ----------------------------
-- Table structure for lgb_config
-- ----------------------------
DROP TABLE IF EXISTS `lgb_config`;
CREATE TABLE `lgb_config` (
  `configId` int(11) NOT NULL AUTO_INCREMENT,
  `background_color_setting` varchar(15) NOT NULL DEFAULT 'FFFECE',
  `studentCourseLimit` int(11) NOT NULL DEFAULT '3' COMMENT '每个学员最多可以选择课程的数目',
  `financeMessage` text NOT NULL COMMENT '缴费优惠基本信息',
  `refundMessage` text NOT NULL COMMENT '退课收费规则基本信息',
  PRIMARY KEY (`configId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lgb_config
-- ----------------------------
INSERT INTO `lgb_config` VALUES ('1', 'FFFECE', '3', '1.离休人员只收100元RMB\r\n2.班长职位人员免300元RMB\r\n3.其它人员正常收费', '1.如果没有开课退所有费用\r\n2.如果上课未超过8节退80%\r\n3.如果上课未超过15节退50%');

-- ----------------------------
-- Table structure for lgb_course
-- ----------------------------
DROP TABLE IF EXISTS `lgb_course`;
CREATE TABLE `lgb_course` (
  `courseId` int(11) NOT NULL AUTO_INCREMENT,
  `departmentId` int(11) NOT NULL,
  `majorId` int(11) NOT NULL,
  `courseNum` varchar(45) NOT NULL COMMENT '课程编号',
  `courseName` varchar(45) NOT NULL COMMENT '课程名',
  `courseEnrollmentNum` int(11) NOT NULL DEFAULT '0' COMMENT '计划招生人数',
  `courseStuNum` int(11) NOT NULL DEFAULT '0' COMMENT '实际招生人数',
  `courseTeacherOne` int(11) NOT NULL COMMENT '任课教师1',
  `courseTeacherTwo` int(11) DEFAULT NULL COMMENT '任课教师2',
  `courseTuition` int(11) NOT NULL DEFAULT '0' COMMENT '课程学费',
  `courseLimitNum` int(11) NOT NULL DEFAULT '0' COMMENT '报名限制人数',
  `courseYears` tinyint(2) NOT NULL DEFAULT '1',
  `courseGraLimitNum` int(11) NOT NULL DEFAULT '0',
  `courseSumFlag` tinyint(2) NOT NULL DEFAULT '1' COMMENT '统计标示 0:否 1:是',
  `courseRemark` varchar(45) DEFAULT NULL,
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:非删除 1:删除',
  `courseRoom` tinyint(2) NOT NULL,
  `teacherOneName` varchar(45) NOT NULL,
  `teacherTwoName` varchar(45) DEFAULT NULL,
  `courseMaster` tinyint(2) NOT NULL DEFAULT '-1' COMMENT '班长 关联用户主键 -1就是没有班长',
  `courseGrade` tinyint(2) NOT NULL DEFAULT '1' COMMENT '课程年级 1:一年级 2:二年级 3:三年级 4:四年级 5:五年级',
  `courseStatus` tinyint(2) NOT NULL DEFAULT '0' COMMENT '课程是否毕业: 0:未毕业 1:毕业 毕业课程是删除状态',
  `courseAgeLimit` tinyint(4) NOT NULL DEFAULT '100' COMMENT '课程报名人员年龄限制',
  PRIMARY KEY (`courseId`),
  KEY `courseDepartmentId_idx` (`departmentId`),
  KEY `courseMajorId_idx` (`majorId`),
  CONSTRAINT `courseDepartmentId` FOREIGN KEY (`departmentId`) REFERENCES `lgb_department` (`departmentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `courseMajorId` FOREIGN KEY (`majorId`) REFERENCES `lgb_major` (`majorId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

-- ----------------------------
-- Records of lgb_course
-- ----------------------------
INSERT INTO `lgb_course` VALUES ('1', '13', '14', 'C00001', '图书管理_1班', '50', '0', '9', '9', '300', '50', '1', '50', '1', '', '0', '1', '土桥', null, '-1', '1', '0', '70');
INSERT INTO `lgb_course` VALUES ('2', '13', '15', 'C00002', '信息加工_1班', '50', '0', '9', '9', '300', '50', '1', '50', '1', '', '0', '2', '土桥', null, '-1', '1', '0', '70');
INSERT INTO `lgb_course` VALUES ('3', '14', '16', 'C00003', '会计学_1班', '50', '0', '35', '35', '400', '50', '1', '50', '1', '', '0', '3', '史汉文', null, '-1', '1', '0', '70');
INSERT INTO `lgb_course` VALUES ('4', '14', '17', 'C00004', '审计_1班', '50', '0', '35', '35', '400', '50', '1', '50', '1', '', '0', '1', '史汉文', null, '-1', '1', '0', '70');

-- ----------------------------
-- Table structure for lgb_coursetime
-- ----------------------------
DROP TABLE IF EXISTS `lgb_coursetime`;
CREATE TABLE `lgb_coursetime` (
  `courseTimeid` int(11) NOT NULL AUTO_INCREMENT,
  `timeWeek` tinyint(2) NOT NULL COMMENT '周次:1:周一 2:周二 3:周三 4:周四 5:周五 6:周六 7:周日',
  `timeSpecific` varchar(20) NOT NULL COMMENT '具体时间：A:8点20  B: 9点  C:10点20  D:13点  E:15点  ',
  `courseRoom` tinyint(2) NOT NULL COMMENT '上课教室',
  `courseId` int(11) NOT NULL,
  PRIMARY KEY (`courseTimeid`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb4 COMMENT='上课时间表';

-- ----------------------------
-- Records of lgb_coursetime
-- ----------------------------
INSERT INTO `lgb_coursetime` VALUES ('146', '1', 'a', '1', '1');
INSERT INTO `lgb_coursetime` VALUES ('147', '3', 'a', '1', '1');
INSERT INTO `lgb_coursetime` VALUES ('148', '2', 'a', '2', '2');
INSERT INTO `lgb_coursetime` VALUES ('149', '4', 'a', '2', '2');
INSERT INTO `lgb_coursetime` VALUES ('150', '5', 'a', '3', '3');
INSERT INTO `lgb_coursetime` VALUES ('151', '5', 'c', '3', '3');
INSERT INTO `lgb_coursetime` VALUES ('152', '5', 'b', '1', '4');
INSERT INTO `lgb_coursetime` VALUES ('153', '5', 'd', '1', '4');

-- ----------------------------
-- Table structure for lgb_department
-- ----------------------------
DROP TABLE IF EXISTS `lgb_department`;
CREATE TABLE `lgb_department` (
  `departmentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `departmentName` varchar(45) NOT NULL COMMENT '‫系别名',
  `departmentCourseNum` int(11) NOT NULL DEFAULT '0' COMMENT '该系课程数',
  `departmentStuNum` int(11) NOT NULL DEFAULT '0' COMMENT '该系学员数',
  `departmentStartDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '报名开始时间',
  `departmentStopDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '报名结束时间',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:未删除 1:删除',
  `adminId` int(11) DEFAULT NULL,
  PRIMARY KEY (`departmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='系别表';

-- ----------------------------
-- Records of lgb_department
-- ----------------------------
INSERT INTO `lgb_department` VALUES ('13', '信息管理系', '0', '0', '2017-01-04 00:00:57', '2017-01-07 00:00:10', '0', '19');
INSERT INTO `lgb_department` VALUES ('14', '财务管理', '0', '0', '2016-01-04 00:00:48', '2017-01-07 00:00:59', '0', '19');

-- ----------------------------
-- Table structure for lgb_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `lgb_dictionary`;
CREATE TABLE `lgb_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupKey` int(11) NOT NULL,
  `groupValue` varchar(45) NOT NULL,
  `itemKey` int(11) NOT NULL,
  `itemValue` varchar(45) NOT NULL,
  `status` int(11) NOT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10009 DEFAULT CHARSET=utf8mb4 COMMENT='数据字典';

-- ----------------------------
-- Records of lgb_dictionary
-- ----------------------------
INSERT INTO `lgb_dictionary` VALUES ('1', '1', 'adminRole', '1', '管理员', '1', '1');
INSERT INTO `lgb_dictionary` VALUES ('2', '1', 'adminRole', '2', '学习管理', '1', '2');
INSERT INTO `lgb_dictionary` VALUES ('3', '1', 'adminRole', '3', '报名录入人员', '1', '3');
INSERT INTO `lgb_dictionary` VALUES ('4', '1', 'adminRole', '4', '财务人员', '1', '4');
INSERT INTO `lgb_dictionary` VALUES ('5', '1', 'adminRole', '5', '浏览', '1', '5');
INSERT INTO `lgb_dictionary` VALUES ('6', '1', 'adminRole', '6', '系主任', '1', '6');
INSERT INTO `lgb_dictionary` VALUES ('7', '2', 'gender', '1', '男', '1', '7');
INSERT INTO `lgb_dictionary` VALUES ('8', '2', 'gender', '2', '女', '1', '8');
INSERT INTO `lgb_dictionary` VALUES ('9', '3', 'stuType', '1', '离休人员', '1', '9');
INSERT INTO `lgb_dictionary` VALUES ('10', '3', 'stuType', '2', '退休人员', '1', '10');
INSERT INTO `lgb_dictionary` VALUES ('11', '3', 'stuType', '3', '其他人员', '1', '11');
INSERT INTO `lgb_dictionary` VALUES ('12', '4', 'stuIdentifiedType', '1', '身份证', '1', '12');
INSERT INTO `lgb_dictionary` VALUES ('13', '4', 'stuIdentifiedType', '2', '工作证', '1', '13');
INSERT INTO `lgb_dictionary` VALUES ('14', '5', 'stuOldWorkPlaceType', '1', '机关', '1', '14');
INSERT INTO `lgb_dictionary` VALUES ('15', '5', 'stuOldWorkPlaceType', '2', '事业', '1', '15');
INSERT INTO `lgb_dictionary` VALUES ('16', '5', 'stuOldWorkPlaceType', '3', '企业', '1', '16');
INSERT INTO `lgb_dictionary` VALUES ('17', '5', 'stuOldWorkPlaceType', '4', '军队', '1', '17');
INSERT INTO `lgb_dictionary` VALUES ('18', '5', 'stuOldWorkPlaceType', '5', '其他', '1', '18');
INSERT INTO `lgb_dictionary` VALUES ('19', '6', 'stuPolitical', '1', '中共党员', '1', '19');
INSERT INTO `lgb_dictionary` VALUES ('20', '6', 'stuPolitical', '2', '民主党派', '1', '20');
INSERT INTO `lgb_dictionary` VALUES ('21', '6', 'stuPolitical', '3', '无党派', '1', '21');
INSERT INTO `lgb_dictionary` VALUES ('22', '7', 'stuOldWorkType', '1', '教师', '1', '22');
INSERT INTO `lgb_dictionary` VALUES ('23', '7', 'stuOldWorkType', '2', '企业干部', '1', '23');
INSERT INTO `lgb_dictionary` VALUES ('24', '7', 'stuOldWorkType', '3', '机关干部', '1', '24');
INSERT INTO `lgb_dictionary` VALUES ('25', '7', 'stuOldWorkType', '4', '工人', '1', '25');
INSERT INTO `lgb_dictionary` VALUES ('26', '7', 'stuOldWorkType', '5', '其他', '1', '26');
INSERT INTO `lgb_dictionary` VALUES ('27', '8', 'teacherState', '1', '聘用', '1', '27');
INSERT INTO `lgb_dictionary` VALUES ('28', '8', 'teacherState', '2', '解聘', '1', '28');
INSERT INTO `lgb_dictionary` VALUES ('29', '9', 'courseDiscount', '1', '离休', '1', '1');
INSERT INTO `lgb_dictionary` VALUES ('34', '9', 'courseDiscount', '2', '免300', '1', '2');
INSERT INTO `lgb_dictionary` VALUES ('38', '9', 'courseDiscount', '3', '没有折扣', '1', '3');
INSERT INTO `lgb_dictionary` VALUES ('39', '10', 'logAction', '1', '检索', '1', '39');
INSERT INTO `lgb_dictionary` VALUES ('40', '10', 'logAction', '2', '删除', '1', '40');
INSERT INTO `lgb_dictionary` VALUES ('41', '10', 'logAction', '3', '添加', '1', '41');
INSERT INTO `lgb_dictionary` VALUES ('42', '10', 'logAction', '4', '修改', '1', '42');
INSERT INTO `lgb_dictionary` VALUES ('43', '10', 'logAction', '5', '其他', '1', '43');
INSERT INTO `lgb_dictionary` VALUES ('44', '11', 'adminIsChanged', '0', '否', '1', '44');
INSERT INTO `lgb_dictionary` VALUES ('45', '11', 'adminIsChanged', '1', '是', '1', '45');
INSERT INTO `lgb_dictionary` VALUES ('46', '12', 'adminIsReturn', '0', '否', '1', '46');
INSERT INTO `lgb_dictionary` VALUES ('47', '12', 'adminIsReturn', '1', '是', '1', '47');
INSERT INTO `lgb_dictionary` VALUES ('48', '13', 'logLevel', '1', '提示', '1', '48');
INSERT INTO `lgb_dictionary` VALUES ('49', '13', 'logLevel', '2', '其他信息', '1', '49');
INSERT INTO `lgb_dictionary` VALUES ('50', '14', 'adminIsLock', '0', '否', '1', '50');
INSERT INTO `lgb_dictionary` VALUES ('51', '14', 'adminIsLock', '1', '是', '1', '51');
INSERT INTO `lgb_dictionary` VALUES ('52', '15', 'educational', '1', '研究生以上', '1', '52');
INSERT INTO `lgb_dictionary` VALUES ('53', '15', 'educational', '2', '大学', '1', '53');
INSERT INTO `lgb_dictionary` VALUES ('54', '15', 'educational', '3', '大专', '1', '54');
INSERT INTO `lgb_dictionary` VALUES ('55', '15', 'educational', '4', '高中（含中专）', '1', '55');
INSERT INTO `lgb_dictionary` VALUES ('56', '15', 'educational', '5', '初小', '1', '56');
INSERT INTO `lgb_dictionary` VALUES ('57', '8', 'teacherState', '3', '备用', '1', '57');
INSERT INTO `lgb_dictionary` VALUES ('58', '16', 'courseRoom', '1', '210', '1', '58');
INSERT INTO `lgb_dictionary` VALUES ('59', '16', 'courseRoom', '2', '212', '1', '59');
INSERT INTO `lgb_dictionary` VALUES ('60', '16', 'courseRoom', '3', '213', '1', '60');
INSERT INTO `lgb_dictionary` VALUES ('61', '16', 'courseRoom', '4', '215', '1', '61');
INSERT INTO `lgb_dictionary` VALUES ('62', '16', 'courseRoom', '5', '216', '1', '62');
INSERT INTO `lgb_dictionary` VALUES ('63', '16', 'courseRoom', '6', '307', '1', '63');
INSERT INTO `lgb_dictionary` VALUES ('64', '16', 'courseRoom', '7', '308', '1', '64');
INSERT INTO `lgb_dictionary` VALUES ('65', '16', 'courseRoom', '8', '309', '1', '65');
INSERT INTO `lgb_dictionary` VALUES ('66', '16', 'courseRoom', '9', '310', '1', '66');
INSERT INTO `lgb_dictionary` VALUES ('67', '16', 'courseRoom', '10', '401', '1', '67');
INSERT INTO `lgb_dictionary` VALUES ('68', '16', 'courseRoom', '11', '402', '1', '68');
INSERT INTO `lgb_dictionary` VALUES ('69', '16', 'courseRoom', '12', '403', '1', '69');
INSERT INTO `lgb_dictionary` VALUES ('70', '16', 'courseRoom', '13', '405', '1', '70');
INSERT INTO `lgb_dictionary` VALUES ('71', '16', 'courseRoom', '14', '406', '1', '71');
INSERT INTO `lgb_dictionary` VALUES ('72', '16', 'courseRoom', '15', '407', '1', '72');
INSERT INTO `lgb_dictionary` VALUES ('73', '16', 'courseRoom', '16', '408', '1', '73');
INSERT INTO `lgb_dictionary` VALUES ('74', '16', 'courseRoom', '17', '409', '1', '74');
INSERT INTO `lgb_dictionary` VALUES ('75', '16', 'courseRoom', '18', '417', '1', '75');
INSERT INTO `lgb_dictionary` VALUES ('76', '16', 'courseRoom', '19', '418', '1', '76');
INSERT INTO `lgb_dictionary` VALUES ('77', '16', 'courseRoom', '20', '501', '1', '77');
INSERT INTO `lgb_dictionary` VALUES ('78', '16', 'courseRoom', '21', '502', '1', '78');
INSERT INTO `lgb_dictionary` VALUES ('79', '16', 'courseRoom', '22', '503', '1', '79');
INSERT INTO `lgb_dictionary` VALUES ('80', '16', 'courseRoom', '23', '505', '1', '80');
INSERT INTO `lgb_dictionary` VALUES ('82', '16', 'courseRoom', '25', '507', '1', '82');
INSERT INTO `lgb_dictionary` VALUES ('83', '16', 'courseRoom', '26', '509', '1', '83');
INSERT INTO `lgb_dictionary` VALUES ('84', '16', 'courseRoom', '27', '510', '1', '84');
INSERT INTO `lgb_dictionary` VALUES ('85', '16', 'courseRoom', '28', '511', '1', '85');
INSERT INTO `lgb_dictionary` VALUES ('86', '16', 'courseRoom', '29', '513', '1', '86');
INSERT INTO `lgb_dictionary` VALUES ('87', '16', 'courseRoom', '30', '515', '1', '87');
INSERT INTO `lgb_dictionary` VALUES ('88', '16', 'courseRoom', '31', '516', '1', '88');
INSERT INTO `lgb_dictionary` VALUES ('89', '16', 'courseRoom', '32', '601', '1', '89');
INSERT INTO `lgb_dictionary` VALUES ('90', '16', 'courseRoom', '33', '602', '1', '90');
INSERT INTO `lgb_dictionary` VALUES ('91', '16', 'courseRoom', '34', '606', '1', '91');
INSERT INTO `lgb_dictionary` VALUES ('92', '16', 'courseRoom', '35', '608', '1', '92');
INSERT INTO `lgb_dictionary` VALUES ('93', '16', 'courseRoom', '36', '701', '1', '93');
INSERT INTO `lgb_dictionary` VALUES ('94', '16', 'courseRoom', '37', '702', '1', '94');
INSERT INTO `lgb_dictionary` VALUES ('95', '16', 'courseRoom', '38', '802', '1', '95');
INSERT INTO `lgb_dictionary` VALUES ('96', '17', 'timeSpecific', '1', '8点20', '1', '96');
INSERT INTO `lgb_dictionary` VALUES ('97', '17', 'timeSpecific', '2', '10点25', '1', '97');
INSERT INTO `lgb_dictionary` VALUES ('98', '17', 'timeSpecific', '3', '13点', '1', '98');
INSERT INTO `lgb_dictionary` VALUES ('99', '17', 'timeSpecific', '4', '15点05', '1', '99');
INSERT INTO `lgb_dictionary` VALUES ('101', '18', 'courseSumFlag', '0', '否', '1', '101');
INSERT INTO `lgb_dictionary` VALUES ('102', '18', 'courseSumFlag', '1', '是', '1', '102');
INSERT INTO `lgb_dictionary` VALUES ('103', '19', 'courseYears', '1', '一年制', '1', '103');
INSERT INTO `lgb_dictionary` VALUES ('104', '19', 'courseYears', '2', '二年制', '1', '104');
INSERT INTO `lgb_dictionary` VALUES ('105', '19', 'courseYears', '3', '三年制', '1', '105');
INSERT INTO `lgb_dictionary` VALUES ('106', '19', 'courseYears', '4', '四年制', '1', '106');
INSERT INTO `lgb_dictionary` VALUES ('107', '19', 'courseYears', '5', '五年制', '1', '107');
INSERT INTO `lgb_dictionary` VALUES ('109', '20', 'timeWeek', '1', '周一', '1', '109');
INSERT INTO `lgb_dictionary` VALUES ('110', '20', 'timeWeek', '2', '周二', '1', '110');
INSERT INTO `lgb_dictionary` VALUES ('111', '20', 'timeWeek', '3', '周三', '1', '111');
INSERT INTO `lgb_dictionary` VALUES ('112', '20', 'timeWeek', '4', '周四', '1', '112');
INSERT INTO `lgb_dictionary` VALUES ('113', '20', 'timeWeek', '5', '周五', '1', '113');
INSERT INTO `lgb_dictionary` VALUES ('114', '20', 'timeWeek', '6', '周六', '1', '114');
INSERT INTO `lgb_dictionary` VALUES ('115', '20', 'timeWeek', '7', '周日', '1', '115');
INSERT INTO `lgb_dictionary` VALUES ('116', '21', 'signUpComeFrom', '1', '在线报名', '1', '116');
INSERT INTO `lgb_dictionary` VALUES ('117', '21', 'signUpComeFrom', '2', '校内报名', '1', '117');
INSERT INTO `lgb_dictionary` VALUES ('118', '22', 'memberLevel', '1', '副市级', '1', '1');
INSERT INTO `lgb_dictionary` VALUES ('119', '22', 'memberLevel', '2', '机关局', '1', '2');
INSERT INTO `lgb_dictionary` VALUES ('120', '22', 'memberLevel', '3', '机关副局', '1', '3');
INSERT INTO `lgb_dictionary` VALUES ('121', '22', 'memberLevel', '4', '普通干部', '1', '4');
INSERT INTO `lgb_dictionary` VALUES ('122', '22', 'memberLevel', '5', '企业局', '1', '5');
INSERT INTO `lgb_dictionary` VALUES ('123', '22', 'memberLevel', '6', '企业副局', '1', '6');
INSERT INTO `lgb_dictionary` VALUES ('124', '22', 'memberLevel', '7', '部队局', '1', '7');
INSERT INTO `lgb_dictionary` VALUES ('125', '22', 'memberLevel', '8', '部队副局', '1', '8');
INSERT INTO `lgb_dictionary` VALUES ('126', '23', 'memberJob', '1', '部队', '1', '1');
INSERT INTO `lgb_dictionary` VALUES ('127', '23', 'memberJob', '2', '医院', '1', '2');
INSERT INTO `lgb_dictionary` VALUES ('128', '23', 'memberJob', '3', '政府', '1', '3');
INSERT INTO `lgb_dictionary` VALUES ('129', '24', 'memberCheck', '1', '通过', '1', '1');
INSERT INTO `lgb_dictionary` VALUES ('130', '24', 'memberCheck', '2', '不通过', '1', '1');
INSERT INTO `lgb_dictionary` VALUES ('131', '25', 'tuitionFlag', '0', '未缴费', '1', '1');
INSERT INTO `lgb_dictionary` VALUES ('132', '25', 'tuitionFlag', '1', '已缴费', '1', '2');
INSERT INTO `lgb_dictionary` VALUES ('133', '26', 'billFlag', '1', '已开发票', '1', '1');
INSERT INTO `lgb_dictionary` VALUES ('134', '26', 'billFlag', '0', '未开发票', '1', '2');
INSERT INTO `lgb_dictionary` VALUES ('135', '27', 'refundTuitionFlag', '0', '未退款', '1', '1');
INSERT INTO `lgb_dictionary` VALUES ('136', '27', 'refundTuitionFlag', '1', '已退款', '1', '2');
INSERT INTO `lgb_dictionary` VALUES ('137', '28', 'financeFlag', '0', '不需要', '1', '1');
INSERT INTO `lgb_dictionary` VALUES ('138', '28', 'financeFlag', '1', '需要', '1', '2');
INSERT INTO `lgb_dictionary` VALUES ('139', '28', 'financeFlag', '2', '费用已处理', '1', '2');
INSERT INTO `lgb_dictionary` VALUES ('10000', '10', 'logAction', '-1', '请选择', '1', '10000');
INSERT INTO `lgb_dictionary` VALUES ('10001', '13', 'logLevel', '-1', '请选择', '1', '10001');
INSERT INTO `lgb_dictionary` VALUES ('10002', '2', 'gender', '-1', '请选择', '1', '10002');
INSERT INTO `lgb_dictionary` VALUES ('10003', '3', 'stuType', '-1', '请选择', '1', '10003');
INSERT INTO `lgb_dictionary` VALUES ('10004', '7', 'stuOldWorkType', '-1', '请选择', '1', '10004');
INSERT INTO `lgb_dictionary` VALUES ('10005', '15', 'educational', '-1', '请选择', '1', '10005');
INSERT INTO `lgb_dictionary` VALUES ('10006', '6', 'stuPolitical', '-1', '请选择', '1', '10006');
INSERT INTO `lgb_dictionary` VALUES ('10007', '5', 'stuOldWorkPlaceType', '-1', '请选择', '1', '10007');
INSERT INTO `lgb_dictionary` VALUES ('10008', '21', 'signUpComeFrom', '-1', '请选择', '1', '2');

-- ----------------------------
-- Table structure for lgb_disciplinary
-- ----------------------------
DROP TABLE IF EXISTS `lgb_disciplinary`;
CREATE TABLE `lgb_disciplinary` (
  `disciplinaryId` int(11) NOT NULL AUTO_INCREMENT,
  `stuName` varchar(45) NOT NULL,
  `stuCardNum` varchar(45) NOT NULL,
  `disciTime` date NOT NULL,
  `disciCount` int(11) DEFAULT '1',
  `disciCause` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`disciplinaryId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lgb_disciplinary
-- ----------------------------

-- ----------------------------
-- Table structure for lgb_log
-- ----------------------------
DROP TABLE IF EXISTS `lgb_log`;
CREATE TABLE `lgb_log` (
  `logId` int(11) NOT NULL AUTO_INCREMENT,
  `logAction` tinyint(2) NOT NULL COMMENT '操作：1：检索 2：删除 3：添加 4：修改 5：其他',
  `logLevel` tinyint(2) NOT NULL COMMENT '级别：1：提示',
  `logContent` varchar(255) NOT NULL COMMENT '内容',
  `logUser` varchar(45) NOT NULL COMMENT '操作者',
  `logTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除：0：否1：删除',
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB AUTO_INCREMENT=940 DEFAULT CHARSET=utf8mb4 COMMENT='日志表\n';

-- ----------------------------
-- Records of lgb_log
-- ----------------------------
INSERT INTO `lgb_log` VALUES ('901', '5', '1', '登陆系统', 'admin', '2017-01-04 14:30:59', '0');
INSERT INTO `lgb_log` VALUES ('902', '3', '1', '添加用户xizhuren', 'admin', '2017-01-04 14:36:34', '0');
INSERT INTO `lgb_log` VALUES ('903', '3', '1', '添加用户baoming', 'admin', '2017-01-04 14:37:12', '0');
INSERT INTO `lgb_log` VALUES ('904', '3', '1', '添加系信息管理系', 'admin', '2017-01-04 14:38:29', '0');
INSERT INTO `lgb_log` VALUES ('905', '3', '1', '添加系财务管理', 'admin', '2017-01-04 14:39:11', '0');
INSERT INTO `lgb_log` VALUES ('906', '3', '1', '添加专业图书管理', 'admin', '2017-01-04 14:39:38', '0');
INSERT INTO `lgb_log` VALUES ('907', '3', '1', '添加专业信息加工与处理', 'admin', '2017-01-04 14:40:29', '0');
INSERT INTO `lgb_log` VALUES ('908', '3', '1', '添加专业会计学', 'admin', '2017-01-04 14:40:55', '0');
INSERT INTO `lgb_log` VALUES ('909', '3', '1', '添加专业审计学', 'admin', '2017-01-04 14:41:17', '0');
INSERT INTO `lgb_log` VALUES ('910', '4', '1', '编辑教师土桥', 'admin', '2017-01-04 14:41:54', '0');
INSERT INTO `lgb_log` VALUES ('911', '3', '1', '添加教师史汉文', 'admin', '2017-01-04 14:45:31', '0');
INSERT INTO `lgb_log` VALUES ('912', '3', '1', '添加课程图书管理_1班', 'admin', '2017-01-04 14:49:11', '0');
INSERT INTO `lgb_log` VALUES ('913', '3', '1', '添加课程信息加工_1班', 'admin', '2017-01-04 14:50:37', '0');
INSERT INTO `lgb_log` VALUES ('914', '3', '1', '添加课程会计学_1班', 'admin', '2017-01-04 14:51:58', '0');
INSERT INTO `lgb_log` VALUES ('915', '3', '1', '添加课程审计_1班', 'admin', '2017-01-04 14:53:00', '0');
INSERT INTO `lgb_log` VALUES ('916', '5', '1', '登陆系统', 'admin', '2017-01-04 15:08:02', '0');
INSERT INTO `lgb_log` VALUES ('917', '5', '1', '登陆系统', 'admin', '2017-01-04 15:21:48', '0');
INSERT INTO `lgb_log` VALUES ('918', '3', '1', '添加用户张文岳', '平凡', '2017-01-04 15:23:51', '0');
INSERT INTO `lgb_log` VALUES ('919', '3', '1', '添加用户符雪纯', '平凡', '2017-01-04 15:26:26', '0');
INSERT INTO `lgb_log` VALUES ('920', '3', '1', '添加用户宗雅洁', '平凡', '2017-01-04 15:28:48', '0');
INSERT INTO `lgb_log` VALUES ('921', '4', '1', '编辑系信息管理系', 'admin', '2017-01-04 15:32:39', '0');
INSERT INTO `lgb_log` VALUES ('922', '4', '1', '编辑系财务管理', 'admin', '2017-01-04 15:32:49', '0');
INSERT INTO `lgb_log` VALUES ('923', '4', '1', '编辑系信息管理系', 'admin', '2017-01-04 15:33:21', '0');
INSERT INTO `lgb_log` VALUES ('924', '4', '1', '编辑系财务管理', 'admin', '2017-01-04 15:34:02', '0');
INSERT INTO `lgb_log` VALUES ('925', '5', '1', '登陆系统', 'admin', '2017-01-04 17:39:34', '0');
INSERT INTO `lgb_log` VALUES ('926', '5', '1', '登陆系统', 'admin', '2017-01-04 18:37:25', '0');
INSERT INTO `lgb_log` VALUES ('927', '5', '1', '登陆系统', 'caiwu', '2017-01-04 18:47:37', '0');
INSERT INTO `lgb_log` VALUES ('928', '5', '1', '登陆系统', 'caiwu', '2017-01-04 18:52:50', '0');
INSERT INTO `lgb_log` VALUES ('929', '5', '1', '登陆系统', 'caiwu', '2017-01-04 18:55:43', '0');
INSERT INTO `lgb_log` VALUES ('930', '4', '1', '收费149 : 149', 'caiwu', '2017-01-04 18:56:07', '0');
INSERT INTO `lgb_log` VALUES ('931', '4', '1', '收费150 : 150', 'caiwu', '2017-01-04 18:56:15', '0');
INSERT INTO `lgb_log` VALUES ('932', '4', '1', '收费153 : 153', 'caiwu', '2017-01-04 18:56:25', '0');
INSERT INTO `lgb_log` VALUES ('933', '4', '1', '收费154 : 154', 'caiwu', '2017-01-04 18:57:02', '0');
INSERT INTO `lgb_log` VALUES ('934', '4', '1', '收费157 : 157', 'caiwu', '2017-01-04 18:57:08', '0');
INSERT INTO `lgb_log` VALUES ('935', '4', '1', '收费158 : 158', 'caiwu', '2017-01-04 18:57:14', '0');
INSERT INTO `lgb_log` VALUES ('936', '4', '1', '收费161 : 161', 'caiwu', '2017-01-04 18:57:20', '0');
INSERT INTO `lgb_log` VALUES ('937', '4', '1', '收费162 : 162', 'caiwu', '2017-01-04 18:57:26', '0');
INSERT INTO `lgb_log` VALUES ('938', '5', '1', '登陆系统', 'admin', '2017-01-04 18:57:51', '0');
INSERT INTO `lgb_log` VALUES ('939', '5', '1', '登陆系统', 'admin', '2017-01-04 19:13:43', '0');

-- ----------------------------
-- Table structure for lgb_major
-- ----------------------------
DROP TABLE IF EXISTS `lgb_major`;
CREATE TABLE `lgb_major` (
  `majorId` int(11) NOT NULL AUTO_INCREMENT,
  `departmentId` int(11) NOT NULL,
  `majorName` varchar(20) NOT NULL COMMENT '专业名称',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除专业：0：未删除 1：删除',
  PRIMARY KEY (`majorId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='专业表';

-- ----------------------------
-- Records of lgb_major
-- ----------------------------
INSERT INTO `lgb_major` VALUES ('14', '13', '图书管理', '0');
INSERT INTO `lgb_major` VALUES ('15', '13', '信息加工与处理', '0');
INSERT INTO `lgb_major` VALUES ('16', '14', '会计学', '0');
INSERT INTO `lgb_major` VALUES ('17', '14', '审计学', '0');

-- ----------------------------
-- Table structure for lgb_refundstudentcourse
-- ----------------------------
DROP TABLE IF EXISTS `lgb_refundstudentcourse`;
CREATE TABLE `lgb_refundstudentcourse` (
  `refundId` int(11) NOT NULL AUTO_INCREMENT,
  `studentCourseId` int(11) NOT NULL,
  `refundMoney` int(11) NOT NULL DEFAULT '0',
  `studentId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  `refundTuitionFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否退款 0未退款 1已退款',
  `refundUser` varchar(45) NOT NULL,
  `refundTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`refundId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lgb_refundstudentcourse
-- ----------------------------

-- ----------------------------
-- Table structure for lgb_room
-- ----------------------------
DROP TABLE IF EXISTS `lgb_room`;
CREATE TABLE `lgb_room` (
  `roomId` int(11) NOT NULL AUTO_INCREMENT,
  `roomName` varchar(45) NOT NULL,
  `roomFloor` tinyint(2) NOT NULL,
  `roomCapacity` int(11) NOT NULL,
  `roomRemark` varchar(45) DEFAULT NULL,
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:非删除 1:删除',
  PRIMARY KEY (`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lgb_room
-- ----------------------------
INSERT INTO `lgb_room` VALUES ('1', '210室', '2', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('2', '212室', '2', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('3', '213室', '2', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('4', '215室', '2', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('5', '216室', '2', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('6', '307室', '3', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('7', '308室', '3', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('8', '309室', '3', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('9', '310室', '3', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('10', '401室', '4', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('11', '402室', '4', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('12', '403室', '4', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('13', '405室', '4', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('14', '406室', '4', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('15', '407室', '4', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('16', '408室', '4', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('17', '409室', '4', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('18', '417室', '4', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('19', '418室', '4', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('20', '501室', '5', '30', null, '0');
INSERT INTO `lgb_room` VALUES ('21', '502室', '5', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('22', '503室', '5', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('23', '505室', '5', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('25', '507室', '5', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('26', '509室', '5', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('27', '510室', '5', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('28', '511室', '5', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('29', '513室', '5', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('30', '515室', '5', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('31', '516室', '5', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('32', '601室', '6', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('33', '602室', '6', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('34', '606室', '6', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('35', '608室', '6', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('36', '701室', '7', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('37', '702室', '7', '45', null, '0');
INSERT INTO `lgb_room` VALUES ('38', '802室', '8', '45', null, '0');

-- ----------------------------
-- Table structure for lgb_student
-- ----------------------------
DROP TABLE IF EXISTS `lgb_student`;
CREATE TABLE `lgb_student` (
  `stuId` int(11) NOT NULL AUTO_INCREMENT,
  `stuCardNum` varchar(45) DEFAULT NULL,
  `stuName` varchar(45) NOT NULL,
  `stuGender` tinyint(2) NOT NULL DEFAULT '1',
  `stuTelOne` varchar(100) NOT NULL,
  `stuTelTwo` varchar(100) DEFAULT NULL,
  `stuType` tinyint(2) NOT NULL DEFAULT '1' COMMENT '人员类型：离休人员 退休人员 其他人员',
  `stuIdentifiedType` tinyint(5) NOT NULL DEFAULT '1' COMMENT '1：身份证 2：工作证',
  `stuIdentifiedNum` varchar(45) NOT NULL COMMENT '证件号',
  `stuOldWorkPlaceType` tinyint(2) NOT NULL DEFAULT '1' COMMENT '原单位类别：机关 事业 企业 军队',
  `stuOldWorkPlaceName` varchar(45) NOT NULL COMMENT '原单位名称',
  `stuPolitical` tinyint(2) NOT NULL DEFAULT '1' COMMENT '政治面貌：中共党员 民主党派 无党派',
  `stuOldWorkType` tinyint(2) NOT NULL DEFAULT '3' COMMENT '原职务（原职业）：教师 企业干部 机关干部 工人 其他',
  `stuNationality` varchar(45) NOT NULL DEFAULT '汉族' COMMENT '民族\n',
  `stuBirthday` date NOT NULL COMMENT '出生日期',
  `stuLastEightNum` varchar(45) NOT NULL COMMENT '身份证号后8位',
  `stuCheck` varchar(45) DEFAULT NULL COMMENT '审验情况',
  `stuHealth` varchar(45) DEFAULT NULL COMMENT '健康情况',
  `stuLocation` varchar(100) NOT NULL COMMENT '现在住址',
  `stuEducational` tinyint(2) NOT NULL DEFAULT '1' COMMENT '文化程度：研究生以上 大学 大专 高中（含中专） 初小',
  `stuLevel` tinyint(2) DEFAULT '1' COMMENT '级别',
  `stuSpeciality` varchar(45) DEFAULT NULL COMMENT '特长',
  `stuPreferential` tinyint(2) NOT NULL DEFAULT '3' COMMENT '优惠',
  `stuDependentsTel` varchar(45) DEFAULT NULL COMMENT '家属电话',
  `stuDependentsDesc` varchar(45) DEFAULT NULL COMMENT '家属关系',
  `stuRemarkOne` varchar(45) DEFAULT NULL COMMENT '备注',
  `stuRemarkTwo` varchar(45) DEFAULT NULL COMMENT '备注2',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '删除标示 0: 非删除 1: 删除',
  `studentStartDate` date NOT NULL,
  `stuPicture` varchar(100) DEFAULT 'default_student',
  `innerCardNum` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`stuId`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COMMENT='学员（用户）表';

-- ----------------------------
-- Records of lgb_student
-- ----------------------------
INSERT INTO `lgb_student` VALUES ('2', '200104', '梁彦博', '1', '18640831723', '', '3', '1', '152123199311176611', '1', '高中', '1', '3', '汉族', '1993-11-17', '11176611', '2', '无', '登记卡飞机', '2', '1', 'java', '3', '18640831722', '无', '1', '2', '0', '2003-11-17', 'default_student.jpg', null);
INSERT INTO `lgb_student` VALUES ('63', '200105', '张文岳', '1', '0NiN3a67U9dyx5GYtA32RA==', 'Uvdm2THOSD2rcc9/EY/ymQ==', '2', '1', '137654288722984789', '1', '大连交通大学', '1', '3', '汉族', '2017-01-05', '22984789', '1', '健康', '大连市盖州街120号', '2', '2', '乒乓球', '3', '13987765498', '良好', '无', '无', '0', '2017-01-25', '01-04-2017-15-22-16-345', '200105');
INSERT INTO `lgb_student` VALUES ('64', '200106', '符雪纯', '2', '0NiN3a67U9dyx5GYtA32RA==', 'JBvJHl5Vs9Y=', '2', '1', '138776777898467264', '1', '大连交通大学', '1', '3', '汉族', '2017-01-09', '98467264', '1', '健康', '大连市盖州街120号', '2', '2', '篮球', '3', '13987765498', '良好', '无', '无', '0', '2017-01-18', '01-04-2017-15-24-47-978', '200106');
INSERT INTO `lgb_student` VALUES ('65', '200107', '宗雅洁', '2', 'ipZt39R5+e7UwXkmkflXlA==', 'uOHAQf9qoHGrcc9/EY/ymQ==', '2', '1', '138928744983798889', '1', ' 大连交通大学', '1', '3', '汉族', '2017-01-12', '83798889', '1', '健康', '大连市盖州街120号', '2', '1', '羽毛球', '3', '13987765498', '良好', '无', '无', '0', '2017-01-10', '01-04-2017-15-27-02-021', '200107');

-- ----------------------------
-- Table structure for lgb_studentcheck
-- ----------------------------
DROP TABLE IF EXISTS `lgb_studentcheck`;
CREATE TABLE `lgb_studentcheck` (
  `checkId` int(11) NOT NULL AUTO_INCREMENT,
  `checkTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '打卡时间',
  `checkUserName` varchar(45) NOT NULL COMMENT '考勤人员名字',
  `checkCardNum` varchar(45) NOT NULL COMMENT '考勤卡号',
  `checkFlag` varchar(45) NOT NULL DEFAULT '0' COMMENT '打卡状态 0:有课打卡 1:无课打卡',
  PRIMARY KEY (`checkId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lgb_studentcheck
-- ----------------------------

-- ----------------------------
-- Table structure for lgb_studentcourse
-- ----------------------------
DROP TABLE IF EXISTS `lgb_studentcourse`;
CREATE TABLE `lgb_studentcourse` (
  `studentCourseId` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) NOT NULL COMMENT '学生ID',
  `courseId` int(11) NOT NULL COMMENT '课程ID',
  `courseDiscount` tinyint(2) DEFAULT '10' COMMENT '离休,免300,没有折扣',
  `actualTuition` int(11) DEFAULT '0' COMMENT '真实缴费',
  `tuitionFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否付款 0:否 1:是',
  `signUpComeFrom` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:在线报名 2:校内报名',
  `signUpUser` varchar(45) DEFAULT NULL COMMENT '报名操作用户',
  `signUpTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '报名时间',
  `financeTime` timestamp NULL DEFAULT NULL COMMENT '缴费时间',
  `financeUser` varchar(45) DEFAULT NULL COMMENT '缴费用户',
  `billFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:未开发票 1:开发票',
  `billNumber` varchar(45) DEFAULT NULL COMMENT '发票号',
  `deleteFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标示: 0 未删除 1 删除 2表示退课 3表示换课',
  `stuScore` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '学成课程成绩',
  PRIMARY KEY (`studentCourseId`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8mb4 COMMENT='学生课程中间表';

-- ----------------------------
-- Records of lgb_studentcourse
-- ----------------------------
INSERT INTO `lgb_studentcourse` VALUES ('149', '2', '2', '3', '300', '1', '1', '梁彦博', '2017-01-04 17:19:47', '2017-01-04 18:56:07', 'caiwu', '0', '2017010418560784', '0', '99');
INSERT INTO `lgb_studentcourse` VALUES ('150', '2', '1', '3', '300', '1', '1', '梁彦博', '2017-01-04 17:19:48', '2017-01-04 18:56:15', 'caiwu', '0', '2017010418561565', '0', '100');
INSERT INTO `lgb_studentcourse` VALUES ('153', '63', '2', '3', '300', '1', '1', '张文岳', '2017-01-04 17:30:29', '2017-01-04 18:56:25', 'caiwu', '0', '2017010418562590', '0', '-1');
INSERT INTO `lgb_studentcourse` VALUES ('154', '63', '1', '3', '300', '1', '1', '张文岳', '2017-01-04 17:30:31', '2017-01-04 18:57:02', 'caiwu', '0', '2017010418570230', '0', '100');
INSERT INTO `lgb_studentcourse` VALUES ('157', '64', '2', '3', '300', '1', '1', '符雪纯', '2017-01-04 17:33:11', '2017-01-04 18:57:08', 'caiwu', '0', '2017010418570870', '0', '-1');
INSERT INTO `lgb_studentcourse` VALUES ('158', '64', '1', '3', '300', '1', '1', '符雪纯', '2017-01-04 17:33:12', '2017-01-04 18:57:14', 'caiwu', '0', '2017010418571437', '0', '-1');
INSERT INTO `lgb_studentcourse` VALUES ('161', '65', '2', '3', '300', '1', '1', '宗雅洁', '2017-01-04 17:33:33', '2017-01-04 18:57:20', 'caiwu', '0', '2017010418572072', '0', '-1');
INSERT INTO `lgb_studentcourse` VALUES ('162', '65', '1', '3', '300', '1', '1', '宗雅洁', '2017-01-04 17:33:34', '2017-01-04 18:57:26', 'caiwu', '0', '2017010418572651', '0', '-1');

-- ----------------------------
-- Table structure for lgb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `lgb_teacher`;
CREATE TABLE `lgb_teacher` (
  `teacherId` int(11) NOT NULL AUTO_INCREMENT,
  `teacherCardNum` varchar(45) DEFAULT NULL COMMENT '卡号',
  `teacherName` varchar(45) NOT NULL,
  `teacherGender` tinyint(2) NOT NULL COMMENT '教师性别：1：男 2：女',
  `teacherState` tinyint(2) NOT NULL DEFAULT '1' COMMENT '教师状态：1：聘用 2：解聘 3: 备用',
  `teacherBirthday` date NOT NULL,
  `teacherTel` varchar(45) DEFAULT NULL,
  `teacherSubject` varchar(45) DEFAULT NULL COMMENT '现拟任学科',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除： 0：未删除 1：删除',
  `departmentId` int(11) NOT NULL,
  `teacherIdentifiedCardNum` varchar(45) NOT NULL,
  `teacherWorkDate` date NOT NULL COMMENT '聘用时间',
  `teacherOldWorkplace` varchar(100) DEFAULT NULL,
  `teacherSchool` varchar(45) DEFAULT NULL COMMENT '毕业院校',
  `teacherEducational` tinyint(2) NOT NULL COMMENT '学历',
  `teacherMajor` varchar(45) NOT NULL COMMENT '所学专业',
  `teacherHealth` varchar(45) DEFAULT NULL,
  `teacherTitle` varchar(45) DEFAULT NULL,
  `teacherFamilyName` varchar(45) DEFAULT NULL COMMENT '家属姓名',
  `teacherFamilyTel` varchar(45) DEFAULT NULL,
  `teacherResume` text COMMENT '学习简历',
  `teacherWorkDesc` text COMMENT '工作经验',
  `teacherCheck` text COMMENT '学校考核',
  `teacherOverDesc` text COMMENT '解雇原因',
  PRIMARY KEY (`teacherId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COMMENT='教师表';

-- ----------------------------
-- Records of lgb_teacher
-- ----------------------------
INSERT INTO `lgb_teacher` VALUES ('9', '486415698', '土桥', '1', '1', '1865-12-30', '11111112222', '信息加工与处理', '0', '13', '111111111111111111', '1887-12-30', '大连市', '哈佛大院', '1', '计算机', '健康', '教授', '梨园', '11111111111', '毕业与哈佛大院', '100多年工作经验', '无', null);
INSERT INTO `lgb_teacher` VALUES ('35', '486415699', '史汉文', '1', '1', '1993-03-27', '18632239374', '会计', '0', '14', '130635199403272316', '2017-01-04', '阿里巴巴集团', '大连交通大学', '2', '财务管理', '健康', '高级财务', '史汉文', '18632239374', '本科毕业', '十年工作经验', '准', null);

-- ----------------------------
-- Table structure for lgb_teachercourse
-- ----------------------------
DROP TABLE IF EXISTS `lgb_teachercourse`;
CREATE TABLE `lgb_teachercourse` (
  `teacherCourseId` int(11) NOT NULL AUTO_INCREMENT,
  `teacherId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  PRIMARY KEY (`teacherCourseId`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COMMENT='教室课程中间表';

-- ----------------------------
-- Records of lgb_teachercourse
-- ----------------------------
INSERT INTO `lgb_teachercourse` VALUES ('58', '9', '1');
INSERT INTO `lgb_teachercourse` VALUES ('59', '9', '2');
INSERT INTO `lgb_teachercourse` VALUES ('60', '35', '3');
INSERT INTO `lgb_teachercourse` VALUES ('61', '35', '4');

-- ----------------------------
-- Table structure for lgb_teachersubject
-- ----------------------------
DROP TABLE IF EXISTS `lgb_teachersubject`;
CREATE TABLE `lgb_teachersubject` (
  `subjectId` int(11) NOT NULL AUTO_INCREMENT,
  `teacherId` int(11) NOT NULL,
  `subjectName` varchar(45) NOT NULL,
  PRIMARY KEY (`subjectId`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lgb_teachersubject
-- ----------------------------
INSERT INTO `lgb_teachersubject` VALUES ('32', '35', '会计');
