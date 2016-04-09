package com.lgb.function.admin.course.leader.repository;

import com.lgb.function.admin.course.leader.CourseLeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseLeaderRepositoryI {
    Page<CourseLeader> select4Page(Pageable pageable);
    List<CourseLeader> selectAll();
}
