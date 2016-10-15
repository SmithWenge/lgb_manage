package com.lgb.function.support.student.card.service;

import com.lgb.arc.utils.ConstantFields;
import com.lgb.arc.utils.DateUtils;
import com.lgb.function.support.student.StudentNowCourseInfo;
import com.lgb.function.support.student.card.StudentCheck;
import com.lgb.function.support.student.card.repository.StudentCardRepositoryI;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentCardService implements StudentCardServiceI {
    @Autowired
    private StudentCardRepositoryI studentCardRepository;

    /**
     * 查看今天学员上课情况,然后完成学员打卡纪录考勤信息
     *
     * @param studentCardNum
     * @return
     */
    @Transactional
    @Override
    public List<StudentNowCourseInfo> nowDayCourseInfo(String studentCardNum) {
        int week = DateUtils.getNowWeek();

        List<StudentNowCourseInfo> courseInfos =  studentCardRepository.selectCourses(studentCardNum, week);
        if (!courseInfos.isEmpty()) {
            StudentCheck check = new StudentCheck();

            StudentNowCourseInfo nowCourseInfo = studentCardRepository.selectStudent(studentCardNum);

            check.setCheckCardNum(nowCourseInfo.getStudentCardNum());
            check.setCheckUserName(nowCourseInfo.getStudentName());
            check.setCheckFlag(ConstantFields.STUDENT_CHECK_HAS_COURSE_FLAG);

            // 判断这个学员是否已经打卡了
            DateTime dateTime = DateTime.now();
            int year = dateTime.year().get();
            int month = dateTime.monthOfYear().get();
            int day = dateTime.dayOfMonth().get();

            if (!studentCardRepository.hasCarded(studentCardNum, year, month, day)) {
                studentCardRepository.addStudentCheck(check);
            }
        } else {
            StudentCheck check = new StudentCheck();

            StudentNowCourseInfo nowCourseInfo = studentCardRepository.selectStudent(studentCardNum);

            check.setCheckCardNum(nowCourseInfo.getStudentCardNum());
            check.setCheckUserName(nowCourseInfo.getStudentName());
            check.setCheckFlag(ConstantFields.STUDENT_CHECK_NOT_COURSE_FLAG);

            // 判断这个学员是否已经打卡了
            DateTime dateTime = DateTime.now();
            int year = dateTime.year().get();
            int month = dateTime.monthOfYear().get();
            int day = dateTime.dayOfMonth().get();

            if (!studentCardRepository.hasCarded(studentCardNum, year, month, day)) {
                studentCardRepository.addStudentCheck(check);
            }
        }

        return courseInfos;
    }

    @Override
    public StudentNowCourseInfo student(String studentCardNum) {
        return studentCardRepository.selectStudent(studentCardNum);
    }
}
