package com.lgb.function.admin.course.change;

import com.lgb.function.admin.course.time.CourseTime;

import java.sql.Timestamp;
import java.util.List;

public class ChangeCourse {
    private String stuCardNum;   // 学生卡号
    private int studentId;   // 学生主键ID
    private int courseId;   // 课程主键ID
    private String stuName;   // 学生名字
    private String courseName;   // 课程名字
    private Timestamp changeTime;   // 换课时间
    private int courseGrade;   // 课程为几年课程
    private int courseRoom;   // 课程教室编号
    private String teacherTwoName;   // 第二个教师姓名
    private String teacherOneName;   // 第一个教师姓名
    private String departmentName;   // 系名称
    private int courseLimitNum;   // 课程报名限制
    private int courseGraLimitNum;   // 课程毕业人数限制
    private int courseEnrollmentNum;   // 课程计划招生人数
    private int courseStuNum;   // 课程实际人数
    private int courseTuition;   // 课程费用
    private int refundMoney;   // 换课退费数目
    private int financeMoney;   // 换课后缴费数目
    private int oldCourseTuition;   // 换课前的实际收取费用
    private int actualTuition;   // 课程的真实缴费
    private int tuitionFlag;   // 课程缴费标识
    private int billFlag;   // 是否开发票标识
    private Timestamp signUpTime;   // 选课时间
    private int oldCourseId;   // 更换课程之前的Id
    private String oldCourseName;   // 更改课程之前的课程名
    private int studentCourseId;   // 选择课程Id
    private String majorName;   // 专业名
    private int courseYears;   // 课程几年制
    private int stuType;   //  学生类型
    private String operationUser;   // 操作用户
    private int financeFlag;   // 是否费用操作
    private List<CourseTime> courseTimes; // 上课时间

    public void setStuCardNum(String stuCardNum) {
        this.stuCardNum = stuCardNum;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setChangeTime(Timestamp changeTime) {
        this.changeTime = changeTime;
    }

    public void setCourseGrade(int courseGrade) {
        this.courseGrade = courseGrade;
    }

    public void setCourseRoom(int courseRoom) {
        this.courseRoom = courseRoom;
    }

    public void setTeacherTwoName(String teacherTwoName) {
        this.teacherTwoName = teacherTwoName;
    }

    public void setTeacherOneName(String teacherOneName) {
        this.teacherOneName = teacherOneName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setCourseLimitNum(int courseLimitNum) {
        this.courseLimitNum = courseLimitNum;
    }

    public void setCourseGraLimitNum(int courseGraLimitNum) {
        this.courseGraLimitNum = courseGraLimitNum;
    }

    public void setCourseEnrollmentNum(int courseEnrollmentNum) {
        this.courseEnrollmentNum = courseEnrollmentNum;
    }

    public void setCourseStuNum(int courseStuNum) {
        this.courseStuNum = courseStuNum;
    }

    public void setCourseTuition(int courseTuition) {
        this.courseTuition = courseTuition;
    }

    public void setRefundMoney(int refundMoney) {
        this.refundMoney = refundMoney;
    }

    public void setFinanceMoney(int financeMoney) {
        this.financeMoney = financeMoney;
    }

    public void setOldCourseTuition(int oldCourseTuition) {
        this.oldCourseTuition = oldCourseTuition;
    }

    public String getStuCardNum() {

        return stuCardNum;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getStuName() {
        return stuName;
    }

    public String getCourseName() {
        return courseName;
    }

    public Timestamp getChangeTime() {
        return changeTime;
    }

    public int getCourseGrade() {
        return courseGrade;
    }

    public int getCourseRoom() {
        return courseRoom;
    }

    public String getTeacherTwoName() {
        return teacherTwoName;
    }

    public String getTeacherOneName() {
        return teacherOneName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getCourseLimitNum() {
        return courseLimitNum;
    }

    public int getCourseGraLimitNum() {
        return courseGraLimitNum;
    }

    public int getCourseEnrollmentNum() {
        return courseEnrollmentNum;
    }

    public int getCourseStuNum() {
        return courseStuNum;
    }

    public int getCourseTuition() {
        return courseTuition;
    }

    public int getRefundMoney() {
        return refundMoney;
    }

    public int getFinanceMoney() {
        return financeMoney;
    }

    public int getOldCourseTuition() {
        return oldCourseTuition;
    }

    public void setActualTuition(int actualTuition) {
        this.actualTuition = actualTuition;
    }

    public void setTuitionFlag(int tuitionFlag) {
        this.tuitionFlag = tuitionFlag;
    }

    public void setBillFlag(int billFlag) {
        this.billFlag = billFlag;
    }

    public void setSignUpTime(Timestamp signUpTime) {
        this.signUpTime = signUpTime;
    }

    public int getActualTuition() {

        return actualTuition;
    }

    public int getTuitionFlag() {
        return tuitionFlag;
    }

    public int getBillFlag() {
        return billFlag;
    }

    public Timestamp getSignUpTime() {
        return signUpTime;
    }

    public void setOldCourseId(int oldCourseId) {
        this.oldCourseId = oldCourseId;
    }

    public int getOldCourseId() {

        return oldCourseId;
    }

    public void setOldCourseName(String oldCourseName) {
        this.oldCourseName = oldCourseName;
    }

    public String getOldCourseName() {

        return oldCourseName;
    }

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public int getStudentCourseId() {

        return studentCourseId;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorName() {

        return majorName;
    }

    public void setCourseYears(int courseYears) {
        this.courseYears = courseYears;
    }

    public int getCourseYears() {

        return courseYears;
    }

    public void setStuType(int stuType) {
        this.stuType = stuType;
    }

    public int getStuType() {

        return stuType;
    }

    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }

    public String getOperationUser() {

        return operationUser;
    }

    public void setFinanceFlag(int financeFlag) {
        this.financeFlag = financeFlag;
    }

    public int getFinanceFlag() {

        return financeFlag;
    }

    public void setCourseTimes(List<CourseTime> courseTimes) {
        this.courseTimes = courseTimes;
    }

    public List<CourseTime> getCourseTimes() {

        return courseTimes;
    }
}
