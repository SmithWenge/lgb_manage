package com.lgb.function.support.excel.repository;

import com.lgb.function.admin.student.StudentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentExcelImportRepository implements StudentExcelImportRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(StudentUser user) {
        String sql = "INSERT INTO lgb_student (stuCardNum, StuName, stuTelOne, stuIdentifiedNum, stuOldWorkPlaceName, stuBirthday, stuLastEightNum, stuLocation, studentStartDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                user.getStuCardNum(),
                user.getStuName(),
                user.getStuTelOne(),
                user.getStuIdentifiedNum(),
                user.getStuOldWorkPlaceName(),
                user.getStuBirthday(),
                user.getStuLastEightNum(),
                user.getStuLocation(),
                user.getStudentStartDate()
        };

        try {
            return jdbcTemplate.update(sql, args);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
