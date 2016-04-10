package com.lgb.function.admin.course.leader.service;

import com.lgb.function.admin.course.leader.CourseLeader;
import com.lgb.function.admin.course.leader.repository.CourseLeaderRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
        Page<CourseLeader> courseLeaders = courseLeaderRepository.select4Page(pageable);
        List<CourseLeader> courseLeaderList = courseLeaders.getContent();

        for (CourseLeader courseLeader : courseLeaderList) {
            courseLeader.setSiteNum(courseLeaderRepository.courseSiteNum(courseLeader.getCourseId(), courseLeader.getStudentId()));
        }

        return new PageImpl<CourseLeader>(courseLeaderList, pageable, courseLeaders.getTotalElements());
    }

    @Override
    public List<CourseLeader> listAll() {
        List<CourseLeader> courseLeaders = courseLeaderRepository.selectAll();
        for (CourseLeader courseLeader : courseLeaders) {
            courseLeader.setSiteNum(courseLeaderRepository.courseSiteNum(courseLeader.getCourseId(), courseLeader.getStudentId()));
        }

        return courseLeaders;
    }
}
