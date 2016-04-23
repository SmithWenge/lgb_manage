package com.lgb.function.admin.disciplinary.repository;

import com.google.common.base.Optional;
import com.lgb.function.admin.disciplinary.DisciStudentInfo;
import com.lgb.function.admin.disciplinary.Disciplinary;
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
public class DisciplinaryRepository implements DisciplinaryRepositoryI{
    @Autowired
    private RepositoryUtils<Disciplinary> repositoryUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<Disciplinary> query4Page(Disciplinary disciplinary,Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT S.stuId, S.stuName, TMP.stuCardNum, S.stuTelOne, TMP.num FROM (SELECT stuCardNum, COUNT(stuCardNum) AS num FROM lgb_disciplinary GROUP BY stuCardNum) AS TMP LEFT JOIN lgb_student S ON TMP.stuCardNum = S.stuCardNum");
        List<Object> list = new ArrayList<Object>();

        Optional<Disciplinary> optional = Optional.fromNullable(disciplinary);
        if (optional.isPresent()) {
            if (disciplinary.getStuCardNum() != null && disciplinary.getStuCardNum().length() > 0) {
                sql.append(" WHERE TMP.stuCardNum = ?");
                list.add(disciplinary.getStuCardNum());
            }
        }
        Object[] args = list.toArray();

        sql.append(" ORDER BY TMP.num DESC");
        try {
            return repositoryUtils.select4Page(sql.toString(), pageable, args, new Query4PageRowMapper());
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Page<Disciplinary> query4Count(Disciplinary disciplinary, Pageable pageable) {
        String sql = "SELECT S.stuId, S.stuName, TMP.stuCardNum, S.stuTelOne, TMP.num FROM (SELECT stuCardNum, COUNT(stuCardNum) AS num FROM lgb_disciplinary GROUP BY stuCardNum) AS TMP LEFT JOIN lgb_student S ON TMP.stuCardNum = S.stuCardNum WHERE TMP.num = ?";
        Object[] args = {
                disciplinary.getDisciCount()
        };
        try {
            return repositoryUtils.select4Page(sql, pageable, args, new Query4PageRowMapper());
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public List<Disciplinary> queryMore(int stuId) {
        String sql = "SELECT stuId, disciplinaryId,D.stuCardNum, D.stuName, disciTime, disciCause FROM lgb_disciplinary D LEFT JOIN lgb_student S ON D.stuCardNum = S.stuCardNum WHERE stuId = ?";
        Object[] args = {
                stuId
        };
        try {
            return jdbcTemplate.query(sql, args, new Query4MoreRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Disciplinary>();
        }
    }
    @Override
    public int query(Disciplinary disciplinary) {
        String sql = "SELECT stuCardNum FROM lgb_student WHERE stuCardNum = ? ";
        Object[] args = {
            disciplinary.getStuCardNum()
        };
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, args);
        } catch (Exception e) {
            return 0;
        }
    }


    @Override
    public boolean insert(Disciplinary disciplinary) {
        String sql = "INSERT INTO lgb_disciplinary (stuName, stuCardNum, disciTime, disciCause) VALUES (?,?,?,?)";
        Object[] args = {
                disciplinary.getStuName(),
                disciplinary.getStuCardNum(),
                disciplinary.getDisciTime(),
                disciplinary.getDisciCause()
        };
        try {
            return jdbcTemplate.update(sql, args) == 1 ? true : false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Disciplinary disciplinary) {
        String sql = "UPDATE lgb_student SET disciCount = disciCount + '1' WHERE stuCardNum = ?";
        Object[] args = {
                disciplinary.getStuCardNum()
        };
        try {
            return jdbcTemplate.update(sql, args) == 1 ? true : false;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public DisciStudentInfo selectStudent(String studentCardNum) {
        String sql = "SELECT stuName, stuId, stuCardNum FROM lgb_student WHERE deleteFlag = 0 AND stuCardNum = ?";
        Object[] args = {
                studentCardNum
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectStudentRowMapper());
        } catch (Exception e) {
            return null;
        }
    }
    private class SelectStudentRowMapper implements RowMapper<DisciStudentInfo> {

        @Override
        public DisciStudentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            DisciStudentInfo info = new DisciStudentInfo();

            info.setStuId(rs.getInt("stuId"));
            info.setStuName(rs.getString("stuName"));
            info.setStuCardNum(rs.getString("stuCardNum"));

            return info;
        }
    }

    private class Query4PageRowMapper implements RowMapper<Disciplinary> {

        @Override
        public Disciplinary mapRow(ResultSet resultSet, int i) throws SQLException {
            Disciplinary disciplinary = new Disciplinary();

            disciplinary.setStuId(resultSet.getInt("stuId"));
//            disciplinary.setDisciplinaryId(resultSet.getInt("disciplinaryId"));
            disciplinary.setStuName(resultSet.getString("stuName"));
            disciplinary.setStuCardNum(resultSet.getString("stuCardNum"));
            disciplinary.setStuTelOne(resultSet.getString("stuTelOne"));
            disciplinary.setDisciSum(resultSet.getInt("num"));

            return disciplinary;
        }
    }


    private class Query4MoreRowMapper implements RowMapper<Disciplinary> {

        @Override
        public Disciplinary mapRow(ResultSet resultSet, int i) throws SQLException {
            Disciplinary disciplinary = new Disciplinary();

            disciplinary.setStuId(resultSet.getInt("stuId"));
            disciplinary.setStuCardNum(resultSet.getString("stuCardNum"));
            disciplinary.setStuName(resultSet.getString("stuName"));
            disciplinary.setDisciplinaryId(resultSet.getInt("disciplinaryId"));
            disciplinary.setDisciTime(resultSet.getDate("disciTime"));
            disciplinary.setDisciCause(resultSet.getString("disciCause"));

            return disciplinary;
        }
    }
}
