package com.lgb.function.admin.count.detail.service;

import com.lgb.function.admin.count.detail.repository.CountDetailRepositoryI;
import com.lgb.function.admin.student.StudentUser;
import com.lgb.function.support.dictionary.impl.DefaultDictionaryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountDetailService implements CountDetailServiceI {
    @Autowired
    private CountDetailRepositoryI detailRepository;

    DefaultDictionaryManager manager = DefaultDictionaryManager.getInstance();

    @Override
    public List<StudentUser> yearStuEduStart(String value) {
        return detailRepository.selectStuYearStart(value);
    }

    @Override
    public List<StudentUser> stuGender(String value) {

        int dataValue = manager.dictionaryChange("gender",value).getItemKey();
        return detailRepository.selectStuGender(dataValue);
    }

    @Override
    public List<StudentUser> stuEducational(String value) {

        int dataValue = manager.dictionaryChange("educational",value).getItemKey();
        return detailRepository.selectStuEducational(dataValue);
    }

    @Override
    public List<StudentUser> stuOldWorkPlaceType(String value) {

        int dataValue = manager.dictionaryChange("stuOldWorkPlaceType",value).getItemKey();
        return detailRepository.selectStuOldWorkPlaceType(dataValue);
    }

    @Override
    public List<StudentUser> stuOldWorkType(String value) {

        int dataValue = manager.dictionaryChange("stuOldWorkType",value).getItemKey();
        return detailRepository.selectStuOldWorkType(dataValue);
    }

    @Override
    public List<StudentUser> stuPolitical(String value) {

        int dataValue = manager.dictionaryChange("stuPolitical",value).getItemKey();
        return detailRepository.selectStuPolitical(dataValue);
    }

    @Override
    public List<StudentUser> stuPreferential(String value) {

        int dataValue = manager.dictionaryChange("courseDiscount",value).getItemKey();
        return detailRepository.selectStuPreferential(dataValue);
    }

    @Override
    public List<StudentUser> stuType(String value) {

        int dataValue = manager.dictionaryChange("stuType",value).getItemKey();
        return detailRepository.selectStuType(dataValue);
    }

    @Override
    public List<StudentUser> yearStuBirthday(String value) {
        return detailRepository.selectStuBirthday(value);
    }

    @Override
    public List<StudentUser> stuLevel(String key) {
        int dataValue = manager.dictionaryChange("memberLevel", key).getItemKey();

        return detailRepository.selectStuLevel(dataValue);
    }
}
