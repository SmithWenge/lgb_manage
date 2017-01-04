/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : localhost
 Source Database       : lgb_manage

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : utf-8

 Date: 01/04/2017 12:33:14 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `lgb_adminUser`
-- ----------------------------
DROP TABLE IF EXISTS `lgb_adminUser`;
CREATE TABLE `lgb_adminUser` (
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
--  Records of `lgb_adminUser`
-- ----------------------------
BEGIN;
INSERT INTO `lgb_adminUser` VALUES ('1', '平凡', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '0', '1', 'weiniyangfg@163.com', '0', '0', '0'), ('18', '财务', 'caiwu', 'da3238691682424cd77f6c01b8e88f74ffd37abefe62b03e5013dc99f94facd0', '0', '4', 'caiwu@173.com', '0', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `lgb_changecourse`
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
--  Table structure for `lgb_config`
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
--  Records of `lgb_config`
-- ----------------------------
BEGIN;
INSERT INTO `lgb_config` VALUES ('1', 'FFFECE', '3', '1.离休人员只收100元RMB\r\n2.班长职位人员免300元RMB\r\n3.其它人员正常收费', '1.如果没有开课退所有费用\r\n2.如果上课未超过8节退80%\r\n3.如果上课未超过15节退50%');
COMMIT;

-- ----------------------------
--  Table structure for `lgb_course`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

-- ----------------------------
--  Table structure for `lgb_courseTime`
-- ----------------------------
DROP TABLE IF EXISTS `lgb_courseTime`;
CREATE TABLE `lgb_courseTime` (
  `courseTimeid` int(11) NOT NULL AUTO_INCREMENT,
  `timeWeek` tinyint(2) NOT NULL COMMENT '周次:1:周一 2:周二 3:周三 4:周四 5:周五 6:周六 7:周日',
  `timeSpecific` varchar(20) NOT NULL COMMENT '具体时间：A:8点20  B: 9点  C:10点20  D:13点  E:15点  ',
  `courseRoom` tinyint(2) NOT NULL COMMENT '上课教室',
  `courseId` int(11) NOT NULL,
  PRIMARY KEY (`courseTimeid`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COMMENT='上课时间表';

-- ----------------------------
--  Table structure for `lgb_department`
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='系别表';

-- ----------------------------
--  Table structure for `lgb_dictionary`
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
--  Records of `lgb_dictionary`
-- ----------------------------
BEGIN;
INSERT INTO `lgb_dictionary` VALUES ('1', '1', 'adminRole', '1', '管理员', '1', '1'), ('2', '1', 'adminRole', '2', '学习管理', '1', '2'), ('3', '1', 'adminRole', '3', '报名录入人员', '1', '3'), ('4', '1', 'adminRole', '4', '财务人员', '1', '4'), ('5', '1', 'adminRole', '5', '浏览', '1', '5'), ('6', '1', 'adminRole', '6', '系主任', '1', '6'), ('7', '2', 'gender', '1', '男', '1', '7'), ('8', '2', 'gender', '2', '女', '1', '8'), ('9', '3', 'stuType', '1', '离休人员', '1', '9'), ('10', '3', 'stuType', '2', '退休人员', '1', '10'), ('11', '3', 'stuType', '3', '其他人员', '1', '11'), ('12', '4', 'stuIdentifiedType', '1', '身份证', '1', '12'), ('13', '4', 'stuIdentifiedType', '2', '工作证', '1', '13'), ('14', '5', 'stuOldWorkPlaceType', '1', '机关', '1', '14'), ('15', '5', 'stuOldWorkPlaceType', '2', '事业', '1', '15'), ('16', '5', 'stuOldWorkPlaceType', '3', '企业', '1', '16'), ('17', '5', 'stuOldWorkPlaceType', '4', '军队', '1', '17'), ('18', '5', 'stuOldWorkPlaceType', '5', '其他', '1', '18'), ('19', '6', 'stuPolitical', '1', '中共党员', '1', '19'), ('20', '6', 'stuPolitical', '2', '民主党派', '1', '20'), ('21', '6', 'stuPolitical', '3', '无党派', '1', '21'), ('22', '7', 'stuOldWorkType', '1', '教师', '1', '22'), ('23', '7', 'stuOldWorkType', '2', '企业干部', '1', '23'), ('24', '7', 'stuOldWorkType', '3', '机关干部', '1', '24'), ('25', '7', 'stuOldWorkType', '4', '工人', '1', '25'), ('26', '7', 'stuOldWorkType', '5', '其他', '1', '26'), ('27', '8', 'teacherState', '1', '聘用', '1', '27'), ('28', '8', 'teacherState', '2', '解聘', '1', '28'), ('29', '9', 'courseDiscount', '1', '离休', '1', '1'), ('34', '9', 'courseDiscount', '2', '免300', '1', '2'), ('38', '9', 'courseDiscount', '3', '没有折扣', '1', '3'), ('39', '10', 'logAction', '1', '检索', '1', '39'), ('40', '10', 'logAction', '2', '删除', '1', '40'), ('41', '10', 'logAction', '3', '添加', '1', '41'), ('42', '10', 'logAction', '4', '修改', '1', '42'), ('43', '10', 'logAction', '5', '其他', '1', '43'), ('44', '11', 'adminIsChanged', '0', '否', '1', '44'), ('45', '11', 'adminIsChanged', '1', '是', '1', '45'), ('46', '12', 'adminIsReturn', '0', '否', '1', '46'), ('47', '12', 'adminIsReturn', '1', '是', '1', '47'), ('48', '13', 'logLevel', '1', '提示', '1', '48'), ('49', '13', 'logLevel', '2', '其他信息', '1', '49'), ('50', '14', 'adminIsLock', '0', '否', '1', '50'), ('51', '14', 'adminIsLock', '1', '是', '1', '51'), ('52', '15', 'educational', '1', '研究生以上', '1', '52'), ('53', '15', 'educational', '2', '大学', '1', '53'), ('54', '15', 'educational', '3', '大专', '1', '54'), ('55', '15', 'educational', '4', '高中（含中专）', '1', '55'), ('56', '15', 'educational', '5', '初小', '1', '56'), ('57', '8', 'teacherState', '3', '备用', '1', '57'), ('58', '16', 'courseRoom', '1', '210', '1', '58'), ('59', '16', 'courseRoom', '2', '212', '1', '59'), ('60', '16', 'courseRoom', '3', '213', '1', '60'), ('61', '16', 'courseRoom', '4', '215', '1', '61'), ('62', '16', 'courseRoom', '5', '216', '1', '62'), ('63', '16', 'courseRoom', '6', '307', '1', '63'), ('64', '16', 'courseRoom', '7', '308', '1', '64'), ('65', '16', 'courseRoom', '8', '309', '1', '65'), ('66', '16', 'courseRoom', '9', '310', '1', '66'), ('67', '16', 'courseRoom', '10', '401', '1', '67'), ('68', '16', 'courseRoom', '11', '402', '1', '68'), ('69', '16', 'courseRoom', '12', '403', '1', '69'), ('70', '16', 'courseRoom', '13', '405', '1', '70'), ('71', '16', 'courseRoom', '14', '406', '1', '71'), ('72', '16', 'courseRoom', '15', '407', '1', '72'), ('73', '16', 'courseRoom', '16', '408', '1', '73'), ('74', '16', 'courseRoom', '17', '409', '1', '74'), ('75', '16', 'courseRoom', '18', '417', '1', '75'), ('76', '16', 'courseRoom', '19', '418', '1', '76'), ('77', '16', 'courseRoom', '20', '501', '1', '77'), ('78', '16', 'courseRoom', '21', '502', '1', '78'), ('79', '16', 'courseRoom', '22', '503', '1', '79'), ('80', '16', 'courseRoom', '23', '505', '1', '80'), ('82', '16', 'courseRoom', '25', '507', '1', '82'), ('83', '16', 'courseRoom', '26', '509', '1', '83'), ('84', '16', 'courseRoom', '27', '510', '1', '84'), ('85', '16', 'courseRoom', '28', '511', '1', '85'), ('86', '16', 'courseRoom', '29', '513', '1', '86'), ('87', '16', 'courseRoom', '30', '515', '1', '87'), ('88', '16', 'courseRoom', '31', '516', '1', '88'), ('89', '16', 'courseRoom', '32', '601', '1', '89'), ('90', '16', 'courseRoom', '33', '602', '1', '90'), ('91', '16', 'courseRoom', '34', '606', '1', '91'), ('92', '16', 'courseRoom', '35', '608', '1', '92'), ('93', '16', 'courseRoom', '36', '701', '1', '93'), ('94', '16', 'courseRoom', '37', '702', '1', '94'), ('95', '16', 'courseRoom', '38', '802', '1', '95'), ('96', '17', 'timeSpecific', '1', '8点20', '1', '96'), ('97', '17', 'timeSpecific', '2', '10点25', '1', '97'), ('98', '17', 'timeSpecific', '3', '13点', '1', '98'), ('99', '17', 'timeSpecific', '4', '15点05', '1', '99'), ('101', '18', 'courseSumFlag', '0', '否', '1', '101'), ('102', '18', 'courseSumFlag', '1', '是', '1', '102'), ('103', '19', 'courseYears', '1', '一年制', '1', '103'), ('104', '19', 'courseYears', '2', '二年制', '1', '104'), ('105', '19', 'courseYears', '3', '三年制', '1', '105'), ('106', '19', 'courseYears', '4', '四年制', '1', '106'), ('107', '19', 'courseYears', '5', '五年制', '1', '107'), ('109', '20', 'timeWeek', '1', '周一', '1', '109'), ('110', '20', 'timeWeek', '2', '周二', '1', '110'), ('111', '20', 'timeWeek', '3', '周三', '1', '111'), ('112', '20', 'timeWeek', '4', '周四', '1', '112'), ('113', '20', 'timeWeek', '5', '周五', '1', '113'), ('114', '20', 'timeWeek', '6', '周六', '1', '114'), ('115', '20', 'timeWeek', '7', '周日', '1', '115'), ('116', '21', 'signUpComeFrom', '1', '在线报名', '1', '116'), ('117', '21', 'signUpComeFrom', '2', '校内报名', '1', '117'), ('118', '22', 'memberLevel', '1', '副市级', '1', '1'), ('119', '22', 'memberLevel', '2', '机关局', '1', '2'), ('120', '22', 'memberLevel', '3', '机关副局', '1', '3'), ('121', '22', 'memberLevel', '4', '普通干部', '1', '4'), ('122', '22', 'memberLevel', '5', '企业局', '1', '5'), ('123', '22', 'memberLevel', '6', '企业副局', '1', '6'), ('124', '22', 'memberLevel', '7', '部队局', '1', '7'), ('125', '22', 'memberLevel', '8', '部队副局', '1', '8'), ('126', '23', 'memberJob', '1', '部队', '1', '1'), ('127', '23', 'memberJob', '2', '医院', '1', '2'), ('128', '23', 'memberJob', '3', '政府', '1', '3'), ('129', '24', 'memberCheck', '1', '通过', '1', '1'), ('130', '24', 'memberCheck', '2', '不通过', '1', '1'), ('131', '25', 'tuitionFlag', '0', '未缴费', '1', '1'), ('132', '25', 'tuitionFlag', '1', '已缴费', '1', '2'), ('133', '26', 'billFlag', '1', '已开发票', '1', '1'), ('134', '26', 'billFlag', '0', '未开发票', '1', '2'), ('135', '27', 'refundTuitionFlag', '0', '未退款', '1', '1'), ('136', '27', 'refundTuitionFlag', '1', '已退款', '1', '2'), ('137', '28', 'financeFlag', '0', '不需要', '1', '1'), ('138', '28', 'financeFlag', '1', '需要', '1', '2'), ('139', '28', 'financeFlag', '2', '费用已处理', '1', '2'), ('10000', '10', 'logAction', '-1', '请选择', '1', '10000'), ('10001', '13', 'logLevel', '-1', '请选择', '1', '10001'), ('10002', '2', 'gender', '-1', '请选择', '1', '10002'), ('10003', '3', 'stuType', '-1', '请选择', '1', '10003'), ('10004', '7', 'stuOldWorkType', '-1', '请选择', '1', '10004'), ('10005', '15', 'educational', '-1', '请选择', '1', '10005'), ('10006', '6', 'stuPolitical', '-1', '请选择', '1', '10006'), ('10007', '5', 'stuOldWorkPlaceType', '-1', '请选择', '1', '10007'), ('10008', '21', 'signUpComeFrom', '-1', '请选择', '1', '2');
COMMIT;

-- ----------------------------
--  Table structure for `lgb_disciplinary`
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
--  Table structure for `lgb_log`
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
) ENGINE=InnoDB AUTO_INCREMENT=901 DEFAULT CHARSET=utf8mb4 COMMENT='日志表\n';

-- ----------------------------
--  Table structure for `lgb_major`
-- ----------------------------
DROP TABLE IF EXISTS `lgb_major`;
CREATE TABLE `lgb_major` (
  `majorId` int(11) NOT NULL AUTO_INCREMENT,
  `departmentId` int(11) NOT NULL,
  `majorName` varchar(20) NOT NULL COMMENT '专业名称',
  `deleteFlag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除专业：0：未删除 1：删除',
  PRIMARY KEY (`majorId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='专业表';

-- ----------------------------
--  Table structure for `lgb_refundStudentCourse`
-- ----------------------------
DROP TABLE IF EXISTS `lgb_refundStudentCourse`;
CREATE TABLE `lgb_refundStudentCourse` (
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
--  Table structure for `lgb_room`
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
--  Records of `lgb_room`
-- ----------------------------
BEGIN;
INSERT INTO `lgb_room` VALUES ('1', '210室', '2', '45', null, '0'), ('2', '212室', '2', '45', null, '0'), ('3', '213室', '2', '45', null, '0'), ('4', '215室', '2', '45', null, '0'), ('5', '216室', '2', '45', null, '0'), ('6', '307室', '3', '45', null, '0'), ('7', '308室', '3', '45', null, '0'), ('8', '309室', '3', '45', null, '0'), ('9', '310室', '3', '45', null, '0'), ('10', '401室', '4', '45', null, '0'), ('11', '402室', '4', '45', null, '0'), ('12', '403室', '4', '45', null, '0'), ('13', '405室', '4', '45', null, '0'), ('14', '406室', '4', '45', null, '0'), ('15', '407室', '4', '45', null, '0'), ('16', '408室', '4', '45', null, '0'), ('17', '409室', '4', '45', null, '0'), ('18', '417室', '4', '45', null, '0'), ('19', '418室', '4', '45', null, '0'), ('20', '501室', '5', '30', null, '0'), ('21', '502室', '5', '45', null, '0'), ('22', '503室', '5', '45', null, '0'), ('23', '505室', '5', '45', null, '0'), ('25', '507室', '5', '45', null, '0'), ('26', '509室', '5', '45', null, '0'), ('27', '510室', '5', '45', null, '0'), ('28', '511室', '5', '45', null, '0'), ('29', '513室', '5', '45', null, '0'), ('30', '515室', '5', '45', null, '0'), ('31', '516室', '5', '45', null, '0'), ('32', '601室', '6', '45', null, '0'), ('33', '602室', '6', '45', null, '0'), ('34', '606室', '6', '45', null, '0'), ('35', '608室', '6', '45', null, '0'), ('36', '701室', '7', '45', null, '0'), ('37', '702室', '7', '45', null, '0'), ('38', '802室', '8', '45', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `lgb_student`
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
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COMMENT='学员（用户）表';

-- ----------------------------
--  Records of `lgb_student`
-- ----------------------------
BEGIN;
INSERT INTO `lgb_student` VALUES ('2', '200104', '梁彦博', '1', '18640831723', '', '3', '1', '152123199311176611', '1', '高中', '1', '3', '汉族', '1993-11-17', '11176611', '2', '无', '登记卡飞机', '2', '1', 'java', '3', '18640831722', '无', '1', '2', '0', '2003-11-17', 'default_student.jpg', null);
COMMIT;

-- ----------------------------
--  Table structure for `lgb_studentCourse`
-- ----------------------------
DROP TABLE IF EXISTS `lgb_studentCourse`;
CREATE TABLE `lgb_studentCourse` (
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
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8mb4 COMMENT='学生课程中间表';

-- ----------------------------
--  Table structure for `lgb_studentcheck`
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
--  Table structure for `lgb_teacher`
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COMMENT='教师表';

-- ----------------------------
--  Records of `lgb_teacher`
-- ----------------------------
BEGIN;
INSERT INTO `lgb_teacher` VALUES ('9', '486415698', '土桥', '1', '1', '1865-12-31', '11111112222', 'java', '0', '6', '111111111111111111', '1887-12-31', '大连市', '哈佛大院', '1', '计算机', '健康', '教授', '梨园', '11111111111', '毕业与哈佛大院', '100多年工作经验', '无', null);
COMMIT;

-- ----------------------------
--  Table structure for `lgb_teacherCourse`
-- ----------------------------
DROP TABLE IF EXISTS `lgb_teacherCourse`;
CREATE TABLE `lgb_teacherCourse` (
  `teacherCourseId` int(11) NOT NULL AUTO_INCREMENT,
  `teacherId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  PRIMARY KEY (`teacherCourseId`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COMMENT='教室课程中间表';

-- ----------------------------
--  Table structure for `lgb_teacherSubject`
-- ----------------------------
DROP TABLE IF EXISTS `lgb_teacherSubject`;
CREATE TABLE `lgb_teacherSubject` (
  `subjectId` int(11) NOT NULL AUTO_INCREMENT,
  `teacherId` int(11) NOT NULL,
  `subjectName` varchar(45) NOT NULL,
  PRIMARY KEY (`subjectId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
