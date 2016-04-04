package com.lgb.arc.web.tag;

import com.lgb.function.support.dictionary.Dictionary;
import com.lgb.function.support.dictionary.IDictionaryManager;

import java.util.List;

public class DictionaryTag {
    private IDictionaryManager manager;
    private static DictionaryTag tag;

    public void setManager(IDictionaryManager manager) {
        this.manager = manager;
    }

    public IDictionaryManager getManager() {
        return manager;
    }

    public void init() {
        tag = this;
        tag.manager = this.manager;
    }

    /**
     * The Taglib function's method must be static
     * @param groupKey the groupKey field at table;
     * @return The List of value in CACHE {@link com.lgb.function.support.dictionary.impl.DefaultDictionaryManager}
     */
    public static List<Dictionary> list(String groupKey) {
        List<Dictionary> list = tag.manager.dictionaries(groupKey);

        return list;
    }

    public static String show(String groupKey, String itemKey) {
        Dictionary dictionary = tag.manager.dictionary(itemKey, groupKey);

        return dictionary.getItemValue();
    }
}
