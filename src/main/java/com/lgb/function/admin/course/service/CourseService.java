package com.lgb.function.admin.course.service;

import com.google.common.base.Optional;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.course.time.CourseTime;
import com.lgb.function.admin.course.repository.CourseRepositoryI;
import com.lgb.function.admin.course.time.repository.CourseTimeRepositoryI;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.teacher.Teacher;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService implements CourseServiceI {
    @Autowired
    private CourseRepositoryI courseRepository;
    @Autowired
    private LogRepositoryI logRepository;
    @Autowired
    private CourseTimeRepositoryI courseTimeRepository;

    @Override
    public List<Department> departments() {
        return courseRepository.selectDepartments();
    }

    @Override
    public List<Major> majors(int departmentId) {
        return courseRepository.selectMajors(departmentId);
    }

    @Override
    public boolean courseNum(String courseNum) {
        return courseRepository.selectExist(courseNum) == 0 ? true : false;
    }

    @Override
    public boolean courseName(String courseName) {
        return courseRepository.selectCourseName(courseName) == 0 ? true : false;
    }

    @Override
    public List<Teacher> teachers(int departmentId) {
        return courseRepository.selectTeachers(departmentId);
    }

    @Override
    public boolean add(Course course, String logUser) {
        int courseTeacherOne = course.getCourseTeacherOne();
        int courseTeacherTwo = course.getCourseTeacherTwo();

        course.setTeacherOneName(courseRepository.selectTeacher(courseTeacherOne).getTeacherName());

        if (courseTeacherTwo > 0 && courseTeacherOne != courseTeacherTwo) {
            course.setTeacherTwoName(courseRepository.selectTeacher(courseTeacherTwo).getTeacherName());
        }

        boolean addSuccess = courseRepository.insert(course) == 1;
        if (addSuccess) {
            String[] courseTimes = course.getCourseTime().split(",");
            Course courseTeacher = courseRepository.selectByCourseNum(course.getCourseNum());

            addCourseTimes(course, courseTimes, courseTeacher);

            courseRepository.insertTeacherCourse(courseTeacher.getCourseId(), courseTeacherOne);

            if (courseTeacherTwo > 0 && courseTeacherOne != courseTeacherTwo) {
                courseRepository.insertTeacherCourse(courseTeacher.getCourseId(), courseTeacherTwo);
            }

            LogContent logContent = new LogContent(logUser, "添加课程" + course.getCourseName(), 1, 3);
            logRepository.insertLog(logContent);

            return true;
        }

        return false;
    }

    @Override
    public Page<Course> select4Page(int department, Pageable pageable) {
        return courseRepository.select4Page(department, pageable);
    }

    @Override
    public Course select(int courseId) {
        return courseRepository.select(courseId);
    }

    @Override
    public List<CourseTime> courseTimes(int courseId) {
        return courseRepository.selectTimes(courseId);
    }

    @Override
    public boolean edit(Course course, String logUser) {
        int courseTeacherOne = course.getCourseTeacherOne();
        int courseTeacherTwo = course.getCourseTeacherTwo();

        course.setTeacherOneName(courseRepository.selectTeacher(courseTeacherOne).getTeacherName());

        if (courseTeacherTwo > 0 && courseTeacherOne != courseTeacherTwo) {
            course.setTeacherTwoName(courseRepository.selectTeacher(courseTeacherTwo).getTeacherName());
        }

        boolean addSuccess = courseRepository.update(course) == 1;
        if (addSuccess && courseRepository.deleteTimes(course.getCourseId())) {
            String[] courseTimes = course.getCourseTime().split(",");
            Course courseTeacher = courseRepository.selectByCourseNum(course.getCourseNum());

            addCourseTimes(course, courseTimes, courseTeacher);

            courseRepository.insertTeacherCourse(courseTeacher.getCourseId(), courseTeacherOne);

            if (courseTeacherTwo > 0 && courseTeacherOne != courseTeacherTwo) {
                courseRepository.insertTeacherCourse(courseTeacher.getCourseId(), courseTeacherTwo);
            }

            LogContent logContent = new LogContent(logUser, "编辑课程" + course.getCourseName(), 1, 4);
            logRepository.insertLog(logContent);

            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int courseId, String logUser) {
        Course existCourse = courseRepository.selectById(courseId);

        Optional<Course> optional = Optional.fromNullable(existCourse);

        if (optional.isPresent()) {
            boolean tmp = courseRepository.delete(courseId);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "删除系ID为" + courseId, 1, 2);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }

    private void addCourseTimes(Course course, String[] courseTimes, Course courseTeacher) {
        for (String courseTime : courseTimes) {

            String[] tmp = courseTime.split("-");
            int week = Integer.valueOf(tmp[0]);
            String timeSpecific = tmp[1];
            int courseRoom = course.getCourseRoom();

            CourseTime time = new CourseTime(week, timeSpecific, courseRoom, courseTeacher.getCourseId());
            courseTimeRepository.insert(time);
        }
    }
}
