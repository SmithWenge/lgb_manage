package com.lgb.function.admin.disciplinary.service;

import com.lgb.function.admin.disciplinary.DisciStudentInfo;
import com.lgb.function.admin.disciplinary.Disciplinary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DisciplinaryServiceI {
    Page<Disciplinary> query4page (Disciplinary disciplinary,Pageable pageable);
    Page<Disciplinary> query4Count (Disciplinary disciplinary,Pageable pageable);
    boolean addDis (Disciplinary disciplinary, String logUser);
    boolean existCardNum(Disciplinary disciplinary);
    List<Disciplinary> queryMore(int stuId);
    DisciStudentInfo student(String studentCardNum);
}
