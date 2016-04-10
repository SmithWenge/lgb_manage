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
        String sql = "SELECT SC.courseId, S.stuName, S.stuCardNum, C.courseRoom, D.departmentName, M.majorName, C.courseName, S.stuGender, S.stuBirthday, S.stuTelOne, S.stuTelTwo, S.stuId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_major M ON C.majorId = M.majorId LEFT JOIN lgb_department D ON M.departmentId = D.departmentId WHERE S.stuId = C.courseMaster AND C.deleteFlag = 0 ORDER BY C.courseId";
        Object[] args = {};

        return repositoryUtils.select4Page(sql, pageable, args, new Select4PageRowMapper());
    }

    @Override
    public List<CourseLeader> selectAll() {
        String sql = "SELECT SC.courseId, S.stuName, S.stuCardNum, C.courseRoom, D.departmentName, M.majorName, C.courseName, S.stuGender, S.stuBirthday, S.stuTelOne, S.stuTelTwo, S.stuId FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId LEFT JOIN lgb_course C ON SC.courseId = C.courseId LEFT JOIN lgb_major M ON C.majorId = M.majorId LEFT JOIN lgb_department D ON M.departmentId = D.departmentId WHERE S.stuId = C.courseMaster AND C.deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new Select4PageRowMapper());
        } catch (Exception e) {
            return new ArrayList<CourseLeader>();
        }

    }

    public int courseSiteNum(int courseId, int studentId) {
        String sql = "SELECT SO.siteNum FROM (SELECT CO.studentId, CO.stuBirthday, CO.stuName, (@num:=@num + 1) AS siteNum FROM (SELECT SC.studentId, S.stuBirthday, S.stuName FROM lgb_studentCourse SC LEFT JOIN lgb_student S ON SC.studentId = S.stuId WHERE courseId = ? ORDER BY S.stuBirthday ASC) AS CO, (SELECT @num:=0) AS IT) SO WHERE SO.studentId = ?";
        Object[] args = {
                courseId,
                studentId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private class Select4PageRowMapper implements RowMapper<CourseLeader> {

        @Override
        public CourseLeader mapRow(ResultSet rs, int rowNum) throws SQLException {
            CourseLeader leader = new CourseLeader();

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
            leader.setCourseId(rs.getInt("courseId"));
            leader.setStudentId(rs.getInt("stuId"));

            return leader;
        }
    }
}
