package com.lgb.function.admin.course.leader.repository;

import com.lgb.function.admin.course.leader.CourseLeader;
import com.lgb.function.support.utils.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseLeaderRepository implements CourseLeaderRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<CourseLeader> repositoryUtils;

    @Override
    public Page<CourseLeader> select4Page(Pageable pageable) {
        String sql = "SELECT S.stuName, S.stuCardNum, C.courseRoom, D.departmentName, M.majorName, C.courseName, S.stuGender, S.stuBirthday, S.stuTelOne, S.stuTelTwo, S.stuId, STUSITE.siteNum FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_major M ON C.majorId = M.majorId LEFT JOIN lgb_department D ON M.departmentId = D.departmentId LEFT JOIN (SELECT TMP.studentId, TMP.stuBirthday, TMP.courseId, (@num:=@num + 1) AS siteNum FROM (SELECT SC.studentId, S.stuBirthday, SC.courseId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId WHERE SC.courseId IN (SELECT courseId FROM lgb_course C WHERE C.deleteFlag = 0) ORDER BY S.stuBirthday ASC) AS TMP, (SELECT @num:=0) AS IT) AS STUSITE ON STUSITE.studentId = S.stuId WHERE S.stuId = C.courseMaster ORDER BY S.stuId";
        Object[] args = {};

        return repositoryUtils.select4Page(sql, pageable, args, new Select4PageRowMapper());
    }

    @Override
    public List<CourseLeader> selectAll() {
        String sql = "SELECT S.stuName, S.stuCardNum, C.courseRoom, D.departmentName, M.majorName, C.courseName, S.stuGender, S.stuBirthday, S.stuTelOne, S.stuTelTwo, S.stuId, STUSITE.siteNum FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_major M ON C.majorId = M.majorId LEFT JOIN lgb_department D ON M.departmentId = D.departmentId LEFT JOIN (SELECT TMP.studentId, TMP.stuBirthday, TMP.courseId, (@num:=@num + 1) AS siteNum FROM (SELECT SC.studentId, S.stuBirthday, SC.courseId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId WHERE SC.courseId IN (SELECT courseId FROM lgb_course C WHERE C.deleteFlag = 0) ORDER BY S.stuBirthday ASC) AS TMP, (SELECT @num:=0) AS IT) AS STUSITE ON STUSITE.studentId = S.stuId WHERE S.stuId = C.courseMaster ORDER BY S.stuId";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new Select4PageRowMapper());
        } catch (Exception e) {
            return new ArrayList<CourseLeader>();
        }

    }

    private class Select4PageRowMapper implements RowMapper<CourseLeader> {

        @Override
        public CourseLeader mapRow(ResultSet rs, int rowNum) throws SQLException {
            CourseLeader leader = new CourseLeader();

            leader.setSiteNum(rs.getInt("siteNum"));
            leader.setLeaderName(rs.getString("stuName"));
            leader.setCourseName(rs.getString("courseName"));
            leader.setClassRoom(rs.getInt("courseRoom"));
            leader.setDepartmentName(rs.getString("departmentName"));
            leader.setMajorName(rs.getString("majorName"));
            leader.setGender(rs.getInt("stuGender"));
            leader.setBirthday(rs.getDate("stuBirthday"));
            leader.setTelOne(rs.getString("stuTelOne"));
            leader.setTelTwo(rs.getString("stuTelTwo"));
            leader.setCardNum(rs.getString("stuCardNum"));

            return leader;
        }
    }
}
