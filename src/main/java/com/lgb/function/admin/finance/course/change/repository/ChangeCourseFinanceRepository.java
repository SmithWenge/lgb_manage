package com.lgb.function.admin.finance.course.change.repository;

import com.lgb.function.admin.course.change.ChangeCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChangeCourseFinanceRepository implements ChangeCourseFinanceRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查看所有换课的费用为处理的记录
     *
     * @return
     */
    @Override
    public List<ChangeCourse> select4ChangeCourseFinances() {
        String sql = "SELECT changeCourseId, CC.courseId, CC.studentId, CC.changeTime, CC.financeFlag, C.courseName, S.stuName, S.stuCardNum, CC.finance FROM lgb_changecourse CC LEFT JOIN lgb_course C ON CC.courseId = C.courseId LEFT JOIN lgb_student S ON CC.studentId = S.stuId WHERE CC.financeFlag = 1";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new Select4ChangeCourseFinancesRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Select4ChangeCourseFinancesRowMapper implements RowMapper<ChangeCourse> {

        @Override
        public ChangeCourse mapRow(ResultSet resultSet, int i) throws SQLException {
            ChangeCourse changeCourse = new ChangeCourse();

            changeCourse.setChangeCourseId(resultSet.getInt("changeCourseId"));
            changeCourse.setCourseId(resultSet.getInt("courseId"));
            changeCourse.setStudentId(resultSet.getInt("studentId"));
            changeCourse.setChangeTime(resultSet.getTimestamp("changeTime"));
            changeCourse.setFinanceFlag(resultSet.getInt("financeFlag"));
            changeCourse.setCourseName(resultSet.getString("courseName"));
            changeCourse.setStuName(resultSet.getString("stuName"));
            changeCourse.setStuCardNum(resultSet.getString("stuCardNum"));
            changeCourse.setFinance(resultSet.getInt("finance"));

            return changeCourse;
        }
    }

    /**
     * 更新换课记录中的缴费状态为已处理
     *
     * @param changeCourseId
     * @return
     */
    @Override
    public boolean update4Tuition(int changeCourseId) {
        String sql = "UPDATE lgb_changecourse SET financeFlag = 2 WHERE changeCourseId = ? AND financeFlag = 1";
        Object[] args = {
                changeCourseId
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
