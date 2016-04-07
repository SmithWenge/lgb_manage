package com.lgb.function.admin.teacher;

import com.lgb.arc.Entry;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Teacher extends Entry {
    private int teacherId;
    private String teacherCardNum;
    private String teacherCardNumNew;
    private String teacherName;
    private int teacherGender;
    private int teacherState;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date teacherBirthday;
    private String teacherTel;
    private String teacherSubject;
    private int deleteFlag;
    private int departmentId;
    private String teacherIdentifiedCardNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date teacherWorkDate;
    private String teacherOldWorkplace;
    private String teacherSchool;
    private int teacherEducational;
    private String teacherMajor;
    private String teacherHealth;
    private String teacherTitle;
    private String teacherFamilyName;
    private String teacherFamilyTel;
    private String teacherResume;
    private String teacherWorkDesc;
    private String teacherCheck;
    private String teacherOverDesc;

    public void setTeacherCardNumNew(String teacherCardNumNew) {
        this.teacherCardNumNew = teacherCardNumNew;
    }

    public String getTeacherCardNumNew() {

        return teacherCardNumNew;
    }

    public void setTeacherFamilyName(String teacherFamilyName) {
        this.teacherFamilyName = teacherFamilyName;
    }

    public void setTeacherFamilyTel(String teacherFamilyTel) {
        this.teacherFamilyTel = teacherFamilyTel;
    }

    public void setTeacherResume(String teacherResume) {
        this.teacherResume = teacherResume;
    }

    public void setTeacherWorkDesc(String teacherWorkDesc) {
        this.teacherWorkDesc = teacherWorkDesc;
    }

    public void setTeacherCheck(String teacherCheck) {
        this.teacherCheck = teacherCheck;
    }

    public void setTeacherOverDesc(String teacherOverDesc) {
        this.teacherOverDesc = teacherOverDesc;
    }

    public String getTeacherFamilyName() {

        return teacherFamilyName;
    }

    public String getTeacherFamilyTel() {
        return teacherFamilyTel;
    }

    public String getTeacherResume() {
        return teacherResume;
    }

    public String getTeacherWorkDesc() {
        return teacherWorkDesc;
    }

    public String getTeacherCheck() {
        return teacherCheck;
    }

    public String getTeacherOverDesc() {
        return teacherOverDesc;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    public String getTeacherTitle() {

        return teacherTitle;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setTeacherCardNum(String teacherCardNum) {
        this.teacherCardNum = teacherCardNum;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setTeacherGender(int teacherGender) {
        this.teacherGender = teacherGender;
    }

    public void setTeacherState(int teacherState) {
        this.teacherState = teacherState;
    }

    public void setTeacherBirthday(Date teacherBirthday) {
        this.teacherBirthday = teacherBirthday;
    }

    public void setTeacherTel(String teacherTel) {
        this.teacherTel = teacherTel;
    }

    public void setTeacherSubject(String teacherSubject) {
        this.teacherSubject = teacherSubject;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setTeacherIdentifiedCardNum(String teacherIdentifiedCardNum) {
        this.teacherIdentifiedCardNum = teacherIdentifiedCardNum;
    }

    public void setTeacherWorkDate(Date teacherWorkDate) {
        this.teacherWorkDate = teacherWorkDate;
    }

    public void setTeacherOldWorkplace(String teacherOldWorkplace) {
        this.teacherOldWorkplace = teacherOldWorkplace;
    }

    public void setTeacherSchool(String teacherSchool) {
        this.teacherSchool = teacherSchool;
    }

    public void setTeacherEducational(int teacherEducational) {
        this.teacherEducational = teacherEducational;
    }

    public void setTeacherMajor(String teacherMajor) {
        this.teacherMajor = teacherMajor;
    }

    public void setTeacherHealth(String teacherHealth) {
        this.teacherHealth = teacherHealth;
    }

    public int getTeacherId() {

        return teacherId;
    }

    public String getTeacherCardNum() {
        return teacherCardNum;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getTeacherGender() {
        return teacherGender;
    }

    public int getTeacherState() {
        return teacherState;
    }

    public Date getTeacherBirthday() {
        return teacherBirthday;
    }

    public String getTeacherTel() {
        return teacherTel;
    }

    public String getTeacherSubject() {
        return teacherSubject;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getTeacherIdentifiedCardNum() {
        return teacherIdentifiedCardNum;
    }

    public Date getTeacherWorkDate() {
        return teacherWorkDate;
    }

    public String getTeacherOldWorkplace() {
        return teacherOldWorkplace;
    }

    public String getTeacherSchool() {
        return teacherSchool;
    }

    public int getTeacherEducational() {
        return teacherEducational;
    }

    public String getTeacherMajor() {
        return teacherMajor;
    }

    public String getTeacherHealth() {
        return teacherHealth;
    }
}
