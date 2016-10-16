package com.lgb.function.admin.student.card.service;

import com.lgb.function.admin.count.model.JsonModel;

import java.util.List;

public interface StudentCardCountServiceI {
    List<JsonModel> queryNumOfStudentCard();
}
