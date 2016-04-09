package com.lgb.function.admin.course.leader.service;

import com.lgb.function.admin.course.leader.CourseLeader;
import com.lgb.function.admin.course.leader.repository.CourseLeaderRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseLeaderService implements CourseLeaderServiceI {
    @Autowired
    private CourseLeaderRepositoryI courseLeaderRepository;

    @Override
    public Page<CourseLeader> select4Page(Pageable pageable) {
        return courseLeaderRepository.select4Page(pageable);
    }

    @Override
    public List<CourseLeader> listAll() {
        return courseLeaderRepository.selectAll();
    }
}
