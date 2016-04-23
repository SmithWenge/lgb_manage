package com.lgb.function.admin.finance;

import com.lgb.arc.Entry;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

public class Finance extends Entry {
    private String departmentName;
    private String majorName;
    private String courseName;
    private int courseTuition;
    private int actualTuition;
    private int tuitionFlag;
    private int courseDiscount;
    private String stuName;
    private int stuGender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stuBirthday;
    private String telOne;
    private String telTwo;
    private String cardNum;
    private int signUpComeFrom;
    private String signUpUser;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signUpDate;
    private int studentCourseId;
    private int departmentId;
    private int majorId;
    private int courseId;
    private Timestamp financeTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date queryFinanceDate;
    private String countFinanceYear;
    private String financeUser;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date queryFinanceDateOne;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date queryFinanceDateTwo;
    private String excelYear;
    private String excelMonth;
    private String billNumber;

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getExcelYear() {
        return excelYear;
    }

    public void setExcelYear(String excelYear) {
        this.excelYear = excelYear;
    }

    public String getExcelMonth() {
        return excelMonth;
    }

    public void setExcelMonth(String excelMonth) {
        this.excelMonth = excelMonth;
    }

    public Date getQueryFinanceDateOne() {
        return queryFinanceDateOne;
    }

    public void setQueryFinanceDateOne(Date queryFinanceDateOne) {
        this.queryFinanceDateOne = queryFinanceDateOne;
    }

    public Date getQueryFinanceDateTwo() {
        return queryFinanceDateTwo;
    }

    public void setQueryFinanceDateTwo(Date queryFinanceDateTwo) {
        this.queryFinanceDateTwo = queryFinanceDateTwo;
    }

    public void setFinanceUser(String financeUser) {
        this.financeUser = financeUser;
    }

    public String getFinanceUser() {

        return financeUser;
    }

    public String getCountFinanceYear() {
        return countFinanceYear;
    }

    public void setCountFinanceYear(String countFinanceYear) {
        this.countFinanceYear = countFinanceYear;
    }

    public void setQueryFinanceDate(Date queryFinanceDate) {
        this.queryFinanceDate = queryFinanceDate;
    }

    public Date getQueryFinanceDate() {

        return queryFinanceDate;
    }

    public void setFinanceTime(Timestamp financeTime) {
        this.financeTime = financeTime;
    }

    public Timestamp getFinanceTime() {

        return financeTime;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getDepartmentId() {

        return departmentId;
    }

    public int getMajorId() {
        return majorId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public int getStudentCourseId() {

        return studentCourseId;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public Date getSignUpDate() {

        return signUpDate;
    }

    public void setSignUpComeFrom(int signUpComeFrom) {
        this.signUpComeFrom = signUpComeFrom;
    }

    public void setSignUpUser(String signUpUser) {
        this.signUpUser = signUpUser;
    }

    public int getSignUpComeFrom() {

        return signUpComeFrom;
    }

    public String getSignUpUser() {
        return signUpUser;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardNum() {

        return cardNum;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseTuition(int courseTuition) {
        this.courseTuition = courseTuition;
    }

    public void setActualTuition(int actualTuition) {
        this.actualTuition = actualTuition;
    }

    public void setTuitionFlag(int tuitionFlag) {
        this.tuitionFlag = tuitionFlag;
    }

    public void setCourseDiscount(int courseDiscount) {
        this.courseDiscount = courseDiscount;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setStuGender(int stuGender) {
        this.stuGender = stuGender;
    }

    public void setStuBirthday(Date stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public void setTelOne(String telOne) {
        this.telOne = telOne;
    }

    public void setTelTwo(String telTwo) {
        this.telTwo = telTwo;
    }

    public String getDepartmentName() {

        return departmentName;
    }

    public String getMajorName() {
        return majorName;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseTuition() {
        return courseTuition;
    }

    public int getActualTuition() {
        return actualTuition;
    }

    public int getTuitionFlag() {
        return tuitionFlag;
    }

    public int getCourseDiscount() {
        return courseDiscount;
    }

    public String getStuName() {
        return stuName;
    }

    public int getStuGender() {
        return stuGender;
    }

    public Date getStuBirthday() {
        return stuBirthday;
    }

    public String getTelOne() {
        return telOne;
    }

    public String getTelTwo() {
        return telTwo;
    }
}
