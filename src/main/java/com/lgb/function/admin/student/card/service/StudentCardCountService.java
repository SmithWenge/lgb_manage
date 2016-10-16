package com.lgb.function.admin.student.card.service;

import com.lgb.function.admin.count.model.JsonModel;
import com.lgb.function.admin.student.card.repository.StudentCardCountRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCardCountService implements StudentCardCountServiceI {
    @Autowired
    private StudentCardCountRepositoryI studentCardCountRepository;

    @Override
    public List<JsonModel> queryNumOfStudentCard() {
        return studentCardCountRepository.select4NumOfStudentCard();
    }
}
