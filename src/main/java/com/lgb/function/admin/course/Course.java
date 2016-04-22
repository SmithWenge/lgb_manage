package com.lgb.function.admin.course;

import com.lgb.arc.Entry;
import com.lgb.function.admin.course.time.CourseTime;
import com.lgb.function.admin.teacher.Teacher;

import java.util.List;

public class Course extends Entry {
    private int courseId;
    private int departmentId;
    private int majorId;
    private String courseNum;
    private String courseName;
    private int courseEnrollmentNum;
    private int courseTeacherOne;
    private int courseTeacherTwo;
    private int courseTuition;
    private int courseLimitNum;
    private int courseYears;
    private int courseGraLimitNum;
    private String courseRemark;
    private String courseTime;
    private int courseRoom;
    private int courseSumFlag;
    private List<Teacher> teachers;
    private String teacherOneName;
    private String teacherTwoName;
    private int courseStuNum;
    private String majorName;
    private int courseMaster;
    private List<CourseTime> times;

    private int deleteFlag;
    private String departmentName;
    private int timeWeek;
    private String timeSpecific;
    private int studentId;
    private String adminName;

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminName() {

        return adminName;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getTimeWeek() {
        return timeWeek;
    }

    public void setTimeWeek(int timeWeek) {
        this.timeWeek = timeWeek;
    }

    public String getTimeSpecific() {
        return timeSpecific;
    }

    public void setTimeSpecific(String timeSpecific) {
        this.timeSpecific = timeSpecific;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setTimes(List<CourseTime> times) {
        this.times = times;
    }

    public List<CourseTime> getTimes() {

        return times;
    }

    public void setCourseMaster(int courseMaster) {
        this.courseMaster = courseMaster;
    }

    public int getCourseMaster() {

        return courseMaster;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorName() {

        return majorName;
    }

    public void setCourseStuNum(int courseStuNum) {
        this.courseStuNum = courseStuNum;
    }

    public int getCourseStuNum() {

        return courseStuNum;
    }

    public void setTeacherOneName(String teacherOneName) {
        this.teacherOneName = teacherOneName;
    }

    public void setTeacherTwoName(String teacherTwoName) {
        this.teacherTwoName = teacherTwoName;
    }

    public String getTeacherOneName() {

        return teacherOneName;
    }

    public String getTeacherTwoName() {
        return teacherTwoName;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Teacher> getTeachers() {

        return teachers;
    }

    public void setCourseSumFlag(int courseSumFlag) {
        this.courseSumFlag = courseSumFlag;
    }

    public int getCourseSumFlag() {

        return courseSumFlag;
    }

    public void setCourseRoom(int courseRoom) {
        this.courseRoom = courseRoom;
    }

    public int getCourseRoom() {

        return courseRoom;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseTime() {

        return courseTime;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseEnrollmentNum(int courseEnrollmentNum) {
        this.courseEnrollmentNum = courseEnrollmentNum;
    }

    public void setCourseTeacherOne(int courseTeacherOne) {
        this.courseTeacherOne = courseTeacherOne;
    }

    public void setCourseTeacherTwo(int courseTeacherTwo) {
        this.courseTeacherTwo = courseTeacherTwo;
    }

    public void setCourseTuition(int courseTuition) {
        this.courseTuition = courseTuition;
    }

    public void setCourseLimitNum(int courseLimitNum) {
        this.courseLimitNum = courseLimitNum;
    }

    public void setCourseYears(int courseYears) {
        this.courseYears = courseYears;
    }

    public void setCourseGraLimitNum(int courseGraLimitNum) {
        this.courseGraLimitNum = courseGraLimitNum;
    }

    public void setCourseRemark(String courseRemark) {
        this.courseRemark = courseRemark;
    }

    public int getCourseId() {

        return courseId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getMajorId() {
        return majorId;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseEnrollmentNum() {
        return courseEnrollmentNum;
    }

    public int getCourseTeacherOne() {
        return courseTeacherOne;
    }

    public int getCourseTeacherTwo() {
        return courseTeacherTwo;
    }

    public int getCourseTuition() {
        return courseTuition;
    }

    public int getCourseLimitNum() {
        return courseLimitNum;
    }

    public int getCourseYears() {
        return courseYears;
    }

    public int getCourseGraLimitNum() {
        return courseGraLimitNum;
    }

    public String getCourseRemark() {
        return courseRemark;
    }
}
