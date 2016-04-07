package com.lgb.function.admin.student;

import com.lgb.arc.Entry;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class StudentUser extends Entry{
    private int stuId;
    private String stuCardNum;
    private String stuName;
    private String stuGender;
    private String stuTelOne;
    private String stuTelTwo;
    private String stuType;
    private int stuIdentifiedType;
    private String stuIdentifiedNum;
    private String stuOldWorkPlaceType;
    private String stuOldWorkPlaceName;
    private String stuPolitical;
    private String stuOldWorkType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stuBirthday;
    private String stuLastEightNum;
    private String stuCheck;
    private String stuHealth;
    private String stuLocation;
    private String stuEducational;
    private String stuLevel;
    private String stuSpeciality;
    private String stuPreferential;
    private String stuDependentsTel;
    private String stuDependentsDesc;
    private String stuRemarkOne;
    private String stuRemarkTwo;
    private String stuNationality;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date studentStartDate;
    private int deleteFlag;

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

    public String getStuGender() {
        return stuGender;
    }

    public void setStuGender(String stuGender) {
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

    public String getStuType() {
        return stuType;
    }

    public void setStuType(String stuType) {
        this.stuType = stuType;
    }

    public int getStuIdentifiedType() {
        return stuIdentifiedType;
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

    public String getStuOldWorkPlaceType() {
        return stuOldWorkPlaceType;
    }

    public void setStuOldWorkPlaceType(String stuOldWorkPlaceType) {
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

    public String getStuOldWorkType() {
        return stuOldWorkType;
    }

    public void setStuOldWorkType(String stuOldWorkType) {
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

    public String getStuEducational() {
        return stuEducational;
    }

    public void setStuEducational(String stuEducational) {
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

    public String getStuPreferential() {
        return stuPreferential;
    }

    public void setStuPreferential(String stuPreferential) {
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
}
