package com.lgb.function.admin.course.time.repository;

import com.lgb.function.admin.course.time.CourseTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseTimeRepository implements CourseTimeRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(CourseTime courseTime) {
        String sql = "INSERT INTO lgb_courseTime (timeWeek, timeSpecific, courseRoom, courseId) VALUES (?, ?, ?, ?)";
        Object[] args = {
                courseTime.getTimeWeek(),
                courseTime.getTimeSpecific(),
                courseTime.getCourseRoom(),
                courseTime.getCourseId()
        };

        jdbcTemplate.update(sql, args);
    }
}
