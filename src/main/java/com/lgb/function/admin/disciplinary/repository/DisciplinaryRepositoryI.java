package com.lgb.function.admin.disciplinary.repository;


import com.lgb.function.admin.disciplinary.Disciplinary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DisciplinaryRepositoryI {
    Page<Disciplinary> query4Page(Disciplinary disciplinary,Pageable pageable);
    Page<Disciplinary> query4Count(Disciplinary disciplinary, Pageable pageable);
    boolean insert(Disciplinary disciplinary);
    int query(Disciplinary disciplinary);
    boolean update(Disciplinary disciplinary);
    List<Disciplinary> queryMore(int stuId);
}
