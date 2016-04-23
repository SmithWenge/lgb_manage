package com.lgb.function.admin.course.service;

import com.google.common.base.Optional;
import com.lgb.arc.exception.BatchRollbackException;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.course.CourseSite;
import com.lgb.function.admin.course.time.CourseTime;
import com.lgb.function.admin.course.repository.CourseRepositoryI;
import com.lgb.function.admin.course.time.repository.CourseTimeRepositoryI;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.major.Major;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.admin.teacher.Teacher;
import com.lgb.function.support.log.LogContent;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    public Page<Course> select4Page(Course course, Pageable pageable) {
        return courseRepository.select4Page(course, pageable);
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
                LogContent logContent = new LogContent(logUser, "删除课程ID为" + courseId, 1, 2);
                logRepository.insertLog(logContent);
            }

            return tmp;
        }

        return false;
    }

    @Override
    public List<CourseSite> courseSiteNum(int courseId) {
        return courseRepository.selectSiteNum(courseId);
    }

    @Override
    public List<StudentUser> courseStudent(int courseId) {
        return courseRepository.selectStudents(courseId);
    }

    @Override
    public Course selectName(int courseId) {
        return courseRepository.selectName(courseId);
    }

    @Override
    public boolean makeLeader(Course course, String logUser) {
        Course existCourse = courseRepository.selectName(course.getCourseId());

        Optional<Course> optional = Optional.fromNullable(existCourse);

        if (optional.isPresent()) {
            boolean tmp = courseRepository.updateLeader(course);

            if (tmp) {
                LogContent logContent = new LogContent(logUser, "修改班级班长,班级ID为" + course.getCourseId(), 1, 4);
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

    @Override
    public boolean courseUpgrade(int courseId, String logUser) {
//        Optional<Course> optional = Optional.fromNullable(courseRepository.selectById(courseId));
//        if (!optional.isPresent()) return false;

        boolean beUpgrade = courseRepository.canUpdateCourseGrade(courseId);

        if (beUpgrade) {
            try {
                boolean tmp =  courseRepository.updateUpgradeCourse(courseId) == 1 ? true : false;

                if (tmp) {
                    LogContent logContent = new LogContent(logUser, "升级课程,课程ID为{}" + courseId, 1, 4);
                    logRepository.insertLog(logContent);
                }

                return tmp;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public boolean batchUpgrade(String batchIds, String logUser) throws BatchRollbackException {
        String[] courseIds = batchIds.split(",");
        int successSum = 0;

        for (String courseId : courseIds) {
            successSum += batchOneUpgrade(Integer.valueOf(courseId), logUser);
        }

        if (courseIds.length != successSum) {
            throw new BatchRollbackException();
        }

        return true;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public boolean batchGraduate(String batchIds, String logUser) throws BatchRollbackException {
        String[] courseIds = batchIds.split(",");
        int successSum = 0;

        for (String courseId : courseIds) {
            successSum += batchOneDelete(Integer.valueOf(courseId), logUser);
        }

        if (courseIds.length != successSum) {
            throw new BatchRollbackException();
        }

        return true;
    }

    private int batchOneUpgrade(int courseId, String logUser) {

        boolean beUpgrade = courseRepository.canUpdateCourseGrade(courseId);

        if (beUpgrade) {
            try {
                int tmp =  courseRepository.updateUpgradeCourse(courseId);

                if (tmp == 1) {
                    LogContent logContent = new LogContent(logUser, "升级课程,课程ID为{}" + courseId, 1, 4);
                    logRepository.insertLog(logContent);
                }

                return tmp;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        return 0;
    }

    private int batchOneDelete(int courseId, String logUser) {
        try {
            int tmp =  courseRepository.deleteGraduateCourse(courseId);

            if (tmp == 1) {
                LogContent logContent = new LogContent(logUser, "毕业课程,课程ID为{}" + courseId, 1, 2);
                logRepository.insertLog(logContent);
            }

            return tmp;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
