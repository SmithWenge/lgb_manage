package com.lgb.function.support.dictionary;

import java.util.List;

public interface IDictionaryManager {
    List<Dictionary> dictionaries(String groupValue);
    void addDataToMemory();
    Dictionary dictionary(int itemKey, String groupValue);
}
