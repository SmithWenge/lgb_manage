package com.lgb.function.admin.student;

import com.lgb.arc.Entry;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class StudentUser extends Entry{
    private int stuId;
    private String stuCardNum;
    private String stuCardNumNew;
    private String stuName;
    private int stuGender;
    private String stuGenderValue;
    private String stuTelOne;
    private String stuTelTwo;
    private int stuType;
    private int stuIdentifiedType;
    private String stuIdentifiedNum;
    private int stuOldWorkPlaceType;
    private String stuOldWorkPlaceName;
    private String stuPolitical;
    private int stuOldWorkType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stuBirthday;
    private String stuLastEightNum;
    private String stuCheck;
    private String stuHealth;
    private String stuLocation;
    private int stuEducational;
    private String stuLevel;
    private String stuSpeciality;
    private int stuPreferential;
    private String stuDependentsTel;
    private String stuDependentsDesc;
    private String stuRemarkOne;
    private String stuRemarkTwo;
    private String stuNationality;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date studentStartDate;
    private int deleteFlag;
    private String pieStudentStartDate;
    private String yearStuBirthday;
    private String stuPicture;
    private int tuitionFlag; // 缴费状态
    private String courseName; // 选课的课程名
    private int studentCourseId; // 学生选课的ID


    public String getYearStuBirthday() {
        return yearStuBirthday;
    }

    public void setYearStuBirthday(String yearStuBirthday) {
        this.yearStuBirthday = yearStuBirthday;
    }

    public void setStuGenderValue(String stuGenderValue) {
        this.stuGenderValue = stuGenderValue;
    }

    public String getStuGenderValue() {

        return stuGenderValue;
    }

    public String getPieStudentStartDate() {
        return pieStudentStartDate;
    }

    public void setPieStudentStartDate(String pieStudentStartDate) {
        this.pieStudentStartDate = pieStudentStartDate;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(Date stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public String getStuNationality() {
        return stuNationality;
    }

    public void setStuNationality(String stuNationality) {
        this.stuNationality = stuNationality;
    }

    public Date getStudentStartDate() {
        return studentStartDate;
    }

    public void setStudentStartDate(Date studentStartDate) {
        this.studentStartDate = studentStartDate;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuCardNum() {
        return stuCardNum;
    }

    public void setStuCardNum(String stuCardNum) {
        this.stuCardNum = stuCardNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuGender() {
        return stuGender;
    }

    public void setStuGender(int stuGender) {
        this.stuGender = stuGender;
    }

    public String getStuTelOne() {
        return stuTelOne;
    }

    public void setStuTelOne(String stuTelOne) {
        this.stuTelOne = stuTelOne;
    }

    public String getStuTelTwo() {
        return stuTelTwo;
    }

    public void setStuTelTwo(String stuTelTwo) {
        this.stuTelTwo = stuTelTwo;
    }

    public int getStuType() {
        return stuType;
    }

    public void setStuType(int stuType) {
        this.stuType = stuType;
    }

    public int getStuIdentifiedType() {
        return stuIdentifiedType;
    }

    public String getStuCardNumNew() {
        return stuCardNumNew;
    }

    public void setStuCardNumNew(String stuCardNumNew) {
        this.stuCardNumNew = stuCardNumNew;
    }

    public void setStuIdentifiedType(int stuIdentifiedType) {
        this.stuIdentifiedType = stuIdentifiedType;
    }

    public String getStuIdentifiedNum() {
        return stuIdentifiedNum;
    }

    public void setStuIdentifiedNum(String stuIdentifiedNum) {
        this.stuIdentifiedNum = stuIdentifiedNum;
    }

    public int getStuOldWorkPlaceType() {
        return stuOldWorkPlaceType;
    }

    public void setStuOldWorkPlaceType(int stuOldWorkPlaceType) {
        this.stuOldWorkPlaceType = stuOldWorkPlaceType;
    }

    public String getStuOldWorkPlaceName() {
        return stuOldWorkPlaceName;
    }

    public void setStuOldWorkPlaceName(String stuOldWorkPlaceName) {
        this.stuOldWorkPlaceName = stuOldWorkPlaceName;
    }

    public String getStuPolitical() {
        return stuPolitical;
    }

    public void setStuPolitical(String stuPolitical) {
        this.stuPolitical = stuPolitical;
    }

    public int getStuOldWorkType() {
        return stuOldWorkType;
    }

    public void setStuOldWorkType(int stuOldWorkType) {
        this.stuOldWorkType = stuOldWorkType;
    }

    public String getStuLastEightNum() {
        return stuLastEightNum;
    }

    public void setStuLastEightNum(String stuLastEightNum) {
        this.stuLastEightNum = stuLastEightNum;
    }

    public String getStuCheck() {
        return stuCheck;
    }

    public void setStuCheck(String stuCheck) {
        this.stuCheck = stuCheck;
    }

    public String getStuHealth() {
        return stuHealth;
    }

    public void setStuHealth(String stuHealth) {
        this.stuHealth = stuHealth;
    }

    public String getStuLocation() {
        return stuLocation;
    }

    public void setStuLocation(String stuLocation) {
        this.stuLocation = stuLocation;
    }

    public int getStuEducational() {
        return stuEducational;
    }

    public void setStuEducational(int stuEducational) {
        this.stuEducational = stuEducational;
    }

    public String getStuLevel() {
        return stuLevel;
    }

    public void setStuLevel(String stuLevel) {
        this.stuLevel = stuLevel;
    }

    public String getStuSpeciality() {
        return stuSpeciality;
    }

    public void setStuSpeciality(String stuSpeciality) {
        this.stuSpeciality = stuSpeciality;
    }

    public int getStuPreferential() {
        return stuPreferential;
    }

    public void setStuPreferential(int stuPreferential) {
        this.stuPreferential = stuPreferential;
    }

    public String getStuDependentsTel() {
        return stuDependentsTel;
    }

    public void setStuDependentsTel(String stuDependentsTel) {
        this.stuDependentsTel = stuDependentsTel;
    }

    public String getStuDependentsDesc() {
        return stuDependentsDesc;
    }

    public void setStuDependentsDesc(String stuDependentsDesc) {
        this.stuDependentsDesc = stuDependentsDesc;
    }

    public String getStuRemarkOne() {
        return stuRemarkOne;
    }

    public void setStuRemarkOne(String stuRemarkOne) {
        this.stuRemarkOne = stuRemarkOne;
    }

    public String getStuRemarkTwo() {
        return stuRemarkTwo;
    }

    public void setStuRemarkTwo(String stuRemarkTwo) {
        this.stuRemarkTwo = stuRemarkTwo;
    }

    public void setStuPicture(String stuPicture) {
        this.stuPicture = stuPicture;
    }

    public String getStuPicture() {

        return stuPicture;
    }

    public void setTuitionFlag(int tuitionFlag) {
        this.tuitionFlag = tuitionFlag;
    }

    public int getTuitionFlag() {

        return tuitionFlag;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {

        return courseName;
    }

    public int getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }
}
