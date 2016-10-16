package com.lgb.function.admin.student.card.repository;

import com.lgb.function.admin.count.model.JsonModel;

import java.util.List;

public interface StudentCardCountRepositoryI {
    List<JsonModel> select4NumOfStudentCard();
}
