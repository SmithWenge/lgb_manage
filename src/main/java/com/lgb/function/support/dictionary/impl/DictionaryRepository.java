package com.lgb.function.support.dictionary.impl;

import com.lgb.function.support.dictionary.Dictionary;
import com.lgb.function.support.dictionary.IDictionaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DictionaryRepository implements IDictionaryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DictionaryRepository.class);

    public Dictionary selectById(String id) {
        String sql = "SELECT id, groupKey, groupKey, itemKey, itemValue, status, sort WHERE id=?";
        Object[] args = { id };

        return jdbcTemplate.queryForObject(sql, args, new DictionaryRowMapper());
    }

    public List<Dictionary> selectAll() {
        String sql = "SELECT id, groupKey, groupValue, itemKey, itemValue, status, sort FROM lgb_dictionary";
        Object[] args = {};

        //TODO Don't using query replace with other api or other JdbcTemplate
        return jdbcTemplate.query(sql, args, new DictionaryRowMapper());
    }

    private class DictionaryRowMapper implements RowMapper<Dictionary> {
        private static final String DICTIONARY_FIELD_ID = "id";
        private static final String DICTIONARY_FIELD_GROUPKEY = "groupKey";
        private static final String DICTIONARY_FIELD_GROUPVALUE = "groupValue";
        private static final String DICTIONARY_FIELD_ITEMKEY = "itemKey";
        private static final String DICTIONARY_FIELD_ITEMVALUE = "itemValue";
        private static final String DICTIONARY_FIELD_STATUS = "status";
        private static final String DICTIONARY_FIELD_SORT = "sort";

        @Override
        public Dictionary mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dictionary dictionary = new Dictionary();

            dictionary.setId(rs.getInt(DICTIONARY_FIELD_ID));
            dictionary.setGroupKey(rs.getInt(DICTIONARY_FIELD_GROUPKEY));
            dictionary.setItemKey(rs.getInt(DICTIONARY_FIELD_ITEMKEY));
            dictionary.setGroupValue(rs.getString(DICTIONARY_FIELD_GROUPVALUE));
            dictionary.setItemValue(rs.getString(DICTIONARY_FIELD_ITEMVALUE));
            dictionary.setStatus(rs.getInt(DICTIONARY_FIELD_STATUS));
            dictionary.setSort(rs.getInt(DICTIONARY_FIELD_SORT));

            return dictionary;
        }
    }
}
