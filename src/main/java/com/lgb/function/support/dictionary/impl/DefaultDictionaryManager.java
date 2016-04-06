package com.lgb.function.support.dictionary.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.lgb.function.support.dictionary.Dictionary;
import com.lgb.function.support.dictionary.IDictionaryManager;
import com.lgb.function.support.dictionary.IDictionaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Component Because the @PostConstruct will load data to Dictionary, so don't add the @Component(will load two times)
public class DefaultDictionaryManager implements IDictionaryManager {
    private static final Logger LOOGER = LoggerFactory.getLogger(DefaultDictionaryManager.class);

    // Logger info
    private static final String LOGGER_INFO_LOAD_DIC = "Loading Dictionary to cache.";
    private static final String LOGGER_INFO_CACHE_WITH_MAP_GROUP_BY_GROUPKEY =
            "Cache the dictionary with map group by groupKey.";
    private static final String LOGGER_INFO_CACHE_WITH_GROUPKEY_SUCCESS =
            "Cache the dictionary with map group by groupKey success.";

    // Logger error
    private static final String LOGGER_ERROR_NO_DICTIONARY_CACHED =
            "No Dictionary cached in the map, Please sure your groupKey";

    @Autowired
    private IDictionaryRepository repository;

    private static final Map<String, List<Dictionary>> CACHE = Maps.newHashMap();
    private static List<Dictionary> dictionaries = null;

    private static DefaultDictionaryManager manager = new DefaultDictionaryManager();

    private DefaultDictionaryManager() {}

    public static DefaultDictionaryManager getInstance() {
        Optional<DefaultDictionaryManager> optional = Optional.fromNullable(manager);
        if (!optional.isPresent()) {
            return new DefaultDictionaryManager();
        }

        return manager;
    }

    @PostConstruct
    public void initCache() {
        dictionaries = repository.selectAll();
        addDataToMemory();
    }

    /**
     * 获得数据字典中对应group的值
     *
     * @param groupKey 数据字典中的groupKey
     * @return 如果存在这个group,返回这个group中的值,如果不存在返回一个empty List
     */
    public List<Dictionary> dictionaries(String groupKey) {
        List<Dictionary> dictionaries = CACHE.get(groupKey);

        Optional<List<Dictionary>> optional = Optional.fromNullable(dictionaries);
        if (!optional.isPresent()) {
            dictionaries = new ArrayList<Dictionary>();
        }

        return dictionaries;
    }

    public void addDataToMemory() {
        for (Dictionary dictionary : dictionaries) {
            String groupValue = dictionary.getGroupValue();
            List<Dictionary> groupDic = CACHE.get(groupValue);

            Optional<List<Dictionary>> optional = Optional.fromNullable(groupDic);
            if (!optional.isPresent()) {
                groupDic = new ArrayList<Dictionary>();
            }

            groupDic.add(dictionary);
            CACHE.put(groupValue, groupDic);
        }
    }

    public Dictionary dictionary(int itemKey, String groupValue) {
        List<Dictionary> dictionaries = CACHE.get(groupValue);

        for (Dictionary dictionary : dictionaries) {
            if (dictionary.getItemKey() == itemKey) {
                return dictionary;
            }
        }

        return new Dictionary();
    }
}
