package com.lgb.function.admin.teacher.repository;

import com.google.common.base.Optional;
import com.lgb.function.admin.department.Department;
import com.lgb.function.admin.teacher.Teacher;
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
public class TeacherRepository implements TeacherRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<Teacher> repositoryUtils;


    @Override
    public int selectCardNum(Teacher teacher) {
        String sql = "SELECT COUNT(teacherId) as num FROM lgb_teacher WHERE deleteFlag = 0 AND teacherCardNum = ?";
        Object[] args = {
                teacher.getTeacherCardNum()
        };

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, args);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Department> selectDepartments() {
        String sql = "SELECT departmentId, departmentName FROM lgb_department WHERE deleteFlag = 0";
        Object[] args = {};

        return jdbcTemplate.query(sql, args, new SelectDepartmentsRowMapper());
    }

    private class SelectDepartmentsRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();

            department.setDepartmentId(rs.getInt("departmentId"));
            department.setDepartmentName(rs.getString("departmentName"));

            return department;
        }
    }

    @Override
    public boolean insert(Teacher teacher) {
        String sql = "INSERT INTO lgb_teacher (teacherCardNum, teacherName, teacherGender, teacherBirthday, teacherTel, teacherSubject, departmentId, teacherIdentifiedCardNum, teacherWorkDate, teacherOldWorkplace, teacherSchool, teacherEducational, teacherMajor, teacherHealth, teacherTitle, teacherFamilyName, teacherFamilyTel, teacherResume, teacherWorkDesc, teacherCheck, teacherOverDesc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                teacher.getTeacherCardNum(),
                teacher.getTeacherName(),
                teacher.getTeacherGender(),
                teacher.getTeacherBirthday(),
                teacher.getTeacherTel(),
                teacher.getTeacherSubject(),
                teacher.getDepartmentId(),
                teacher.getTeacherIdentifiedCardNum(),
                teacher.getTeacherWorkDate(),
                teacher.getTeacherOldWorkplace(),
                teacher.getTeacherSchool(),
                teacher.getTeacherEducational(),
                teacher.getTeacherMajor(),
                teacher.getTeacherHealth(),
                teacher.getTeacherTitle(),
                teacher.getTeacherFamilyName(),
                teacher.getTeacherFamilyTel(),
                teacher.getTeacherResume(),
                teacher.getTeacherWorkDesc(),
                teacher.getTeacherCheck(),
                teacher.getTeacherOverDesc()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public void insert(String subjectName, int teacherId) {
        String sql = "INSERT INTO lgb_teacherSubject (teacherId, subjectName) VALUES (?, ?)";
        Object[] args = {
                teacherId,
                subjectName
        };

        jdbcTemplate.update(sql, args);
    }

    @Override
    public Page<Teacher> select4Page(Teacher teacher, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT teacherId, teacherState, teacherCardNum, teacherName, teacherGender, teacherBirthday, teacherTel, teacherSubject FROM lgb_teacher WHERE deleteFlag = 0");
        List<Object> list = new ArrayList<Object>();

        Optional<Teacher> optional = Optional.fromNullable(teacher);
        if (optional.isPresent()) {
            if (teacher.getTeacherName() != null && teacher.getTeacherName().trim().length() > 0) {
                sql.append(" AND teacherName = ?");
                list.add(teacher.getTeacherName().trim());
            }
            if (teacher.getTeacherCardNum() != null && teacher.getTeacherCardNum().trim().length() > 0) {
                sql.append(" AND teacherCardNum = ?");
                list.add(teacher.getTeacherCardNum().trim());
            }
            if (teacher.getTeacherGender() > 0 && teacher.getTeacherGender() < 3) {
                sql.append(" AND teacherGender = ?");
                list.add(teacher.getTeacherGender());
            }
            if (teacher.getTeacherWorkDate() != null) {
                sql.append(" AND teacherWorkDate = ?");
                list.add(teacher.getTeacherWorkDate());
            }
            if (teacher.getTeacherState() > 0) {
                sql.append(" AND teacherState = ?");
                list.add(teacher.getTeacherState());
            }
            if (teacher.getTeacherBirthday() != null) {
                sql.append(" AND teacherBirthday = ?");
                list.add(teacher.getTeacherBirthday());
            }
            if (teacher.getDepartmentId() > 0) {
                sql.append(" AND departmentId = ?");
                list.add(teacher.getDepartmentId());
            }
            if (teacher.getTeacherIdentifiedCardNum() != null && teacher.getTeacherIdentifiedCardNum().trim().length() > 0) {
                sql.append(" AND teacherIdentifiedCardNum = ?");
                list.add(teacher.getTeacherIdentifiedCardNum());
            }
            if (teacher.getTeacherOldWorkplace() != null && teacher.getTeacherOldWorkplace().trim().length() > 0) {
                sql.append(" AND teacherOldWorkplace = ?");
                list.add(teacher.getTeacherOldWorkplace());
            }
            if (teacher.getTeacherSchool() != null && teacher.getTeacherSchool().trim().length() > 0) {
                sql.append(" AND teacherSchool = ?");
                list.add(teacher.getTeacherSchool());
            }
            if (teacher.getTeacherMajor() != null && teacher.getTeacherMajor().trim().length() > 0) {
                sql.append(" AND teacherMajor = ?");
                list.add(teacher.getTeacherMajor());
            }
            if (teacher.getTeacherFamilyName() != null && teacher.getTeacherFamilyName().trim().length() > 0) {
                sql.append(" AND teacherFamilyName = ?");
                list.add(teacher.getTeacherFamilyName());
            }
            if (teacher.getTeacherFamilyTel() != null && teacher.getTeacherFamilyTel().trim().length() > 0) {
                sql.append(" AND teacherFamilyTel = ?");
                list.add(teacher.getTeacherFamilyTel());
            }
            if (teacher.getTeacherSubject() != null && teacher.getTeacherSubject().trim().length() > 0) {
                sql.append(" AND teacherSubject = ?");
                list.add(teacher.getTeacherSubject());
            }
            if (teacher.getTeacherTel() != null && teacher.getTeacherTel().trim().length() > 0) {
                sql.append(" AND teacherTel = ?");
                list.add(teacher.getTeacherTel());
            }
            if (teacher.getTeacherHealth() != null && teacher.getTeacherHealth().trim().length() > 0) {
                sql.append(" AND teacherHealth = ?");
                list.add(teacher.getTeacherHealth());
            }
            if (teacher.getTeacherEducational() > 0) {
                sql.append(" AND teacherEducational = ?");
                list.add(teacher.getTeacherEducational());
            }
        }

        Object[] args = list.toArray();
        sql.append(" ORDER BY teacherId DESC");

        return repositoryUtils.select4Page(sql.toString(), pageable, args, new Select4PageRowMapper());
    }

    @Override
    public int selectId(String teacherCardNum) {
        String sql = "SELECT teacherId FROM lgb_teacher WHERE teacherCardNum = ?";
        Object[] args = {
                teacherCardNum
        };

        return jdbcTemplate.queryForObject(sql, args, Integer.class);
    }

    @Override
    public Teacher selectCard(int teacherId) {
        String sql = "SELECT teacherId, teacherName FROM lgb_teacher WHERE deleteFlag = 0 AND teacherId = ?";
        Object[] args = {
                teacherId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectCardRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean selectIdAndCard(Teacher teacher) {
        String sql = "SELECT COUNT(teacherId) as NUM FROM lgb_teacher WHERE deleteFlag = 0 AND teacherId = ? AND teacherCardNum = ?";
        Object[] args = {
                teacher.getTeacherId(),
                teacher.getTeacherCardNum()
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class) == 1 ? true : false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateCard(Teacher teacher) {
        String sql = "UPDATE lgb_teacher SET teacherCardNum = ? WHERE teacherId = ?";
        Object[] args = {
                teacher.getTeacherCardNumNew(),
                teacher.getTeacherId()
        };


        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public boolean update(Teacher teacher) {
        String sql = "UPDATE lgb_teacher SET teacherName = ?, teacherGender = ?, teacherState = ?, teacherBirthday = ?, teacherTel = ?, teacherSubject = ?, departmentId = ?, teacherIdentifiedCardNum = ?, teacherWorkDate = ?, teacherOldWorkplace = ?, teacherSchool = ?, teacherEducational = ?, teacherMajor = ?, teacherHealth = ?, teacherTitle = ?, teacherFamilyName = ?, teacherFamilyTel = ?, teacherResume = ?, teacherWorkDesc = ?, teacherCheck = ?, teacherOverDesc = ? WHERE teacherId = ?";
        Object[] args = {
                teacher.getTeacherName(),
                teacher.getTeacherGender(),
                teacher.getTeacherState(),
                teacher.getTeacherBirthday(),
                teacher.getTeacherTel(),
                teacher.getTeacherSubject(),
                teacher.getDepartmentId(),
                teacher.getTeacherIdentifiedCardNum(),
                teacher.getTeacherWorkDate(),
                teacher.getTeacherOldWorkplace(),
                teacher.getTeacherSchool(),
                teacher.getTeacherEducational(),
                teacher.getTeacherMajor(),
                teacher.getTeacherHealth(),
                teacher.getTeacherTitle(),
                teacher.getTeacherFamilyName(),
                teacher.getTeacherFamilyTel(),
                teacher.getTeacherResume(),
                teacher.getTeacherWorkDesc(),
                teacher.getTeacherCheck(),
                teacher.getTeacherOverDesc(),
                teacher.getTeacherId()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    @Override
    public Teacher select(int teacherId) {
        String sql = "SELECT teacherId, teacherCardNum, teacherName, teacherGender, teacherState, teacherBirthday, teacherTel, teacherSubject, departmentId, teacherIdentifiedCardNum, teacherWorkDate, teacherOldWorkplace, teacherSchool, teacherEducational, teacherMajor, teacherHealth, teacherTitle, teacherFamilyName, teacherFamilyTel, teacherResume, teacherWorkDesc, teacherCheck, teacherOverDesc FROM lgb_teacher WHERE deleteFlag = 0 AND teacherId = ?";
        Object[] args = {
                teacherId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(Teacher teacher) {
        String sql = "UPDATE lgb_teacher SET deleteFlag = 1, teacherOverDesc = ? WHERE teacherId = ?";
        Object[] args = {
                teacher.getTeacherOverDesc(),
                teacher.getTeacherId()
        };

        return jdbcTemplate.update(sql, args) == 1 ? true : false;
    }

    private class SelectRowMapper implements RowMapper<Teacher> {

        @Override
        public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Teacher teacher = new Teacher();

            teacher.setTeacherId(rs.getInt("teacherId"));
            teacher.setTeacherCardNum(rs.getString("teacherCardNum"));
            teacher.setTeacherName(rs.getString("teacherName"));
            teacher.setTeacherGender(rs.getInt("teacherGender"));
            teacher.setTeacherState(rs.getInt("teacherState"));
            teacher.setTeacherBirthday(rs.getDate("teacherBirthday"));
            teacher.setTeacherTel(rs.getString("teacherTel"));
            teacher.setTeacherSubject(rs.getString("teacherSubject"));
            teacher.setDepartmentId(rs.getInt("departmentId"));
            teacher.setTeacherIdentifiedCardNum(rs.getString("teacherIdentifiedCardNum"));
            teacher.setTeacherWorkDate(rs.getDate("teacherWorkDate"));
            teacher.setTeacherOldWorkplace(rs.getString("teacherOldWorkplace"));
            teacher.setTeacherSchool(rs.getString("teacherSchool"));
            teacher.setTeacherEducational(rs.getInt("teacherEducational"));
            teacher.setTeacherMajor(rs.getString("teacherMajor"));
            teacher.setTeacherHealth(rs.getString("teacherHealth"));
            teacher.setTeacherTitle(rs.getString("teacherTitle"));
            teacher.setTeacherFamilyName(rs.getString("teacherFamilyName"));
            teacher.setTeacherFamilyTel(rs.getString("teacherFamilyTel"));
            teacher.setTeacherResume(rs.getString("teacherResume"));
            teacher.setTeacherWorkDesc(rs.getString("teacherWorkDesc"));
            teacher.setTeacherCheck(rs.getString("teacherCheck"));
            teacher.setTeacherOverDesc(rs.getString("teacherOverDesc"));

            return teacher;
        }
    }

    private class SelectCardRowMapper implements RowMapper<Teacher> {

        @Override
        public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Teacher teacher = new Teacher();

            teacher.setTeacherId(rs.getInt("teacherId"));
            teacher.setTeacherName(rs.getString("teacherName"));

            return teacher;
        }
    }

    private class Select4PageRowMapper implements RowMapper<Teacher> {

        @Override
        public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Teacher teacher = new Teacher();

            teacher.setTeacherId(rs.getInt("teacherId"));
            teacher.setTeacherState(rs.getInt("teacherState"));
            teacher.setTeacherCardNum(rs.getString("teacherCardNum"));
            teacher.setTeacherName(rs.getString("teacherName"));
            teacher.setTeacherGender(rs.getInt("teacherGender"));
            teacher.setTeacherBirthday(rs.getDate("teacherBirthday"));
            teacher.setTeacherTel(rs.getString("teacherTel"));
            teacher.setTeacherSubject(rs.getString("teacherSubject"));

            return teacher;
        }
    }
}
