package com.lgb.function.support.dictionary;

import java.util.List;

public interface IDictionaryManager {
    List<Dictionary> dictionaries(String groupKey);
    void addDataToMemory();
    Dictionary dictionary(String itemKey, String groupKey);
}
