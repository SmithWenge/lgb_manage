package com.lgb.function.support.dictionary;

import com.lgb.arc.Entry;

public class Dictionary extends Entry {
    private int id;
    private String groupKey;
    private String groupValue;
    private String itemKey;
    private String itemValue;
    private int status;
    private int sort;

    public void setId(int id) {
        this.id = id;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public void setGroupValue(String groupValue) {
        this.groupValue = groupValue;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getId() {
        return id;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public String getGroupValue() {
        return groupValue;
    }

    public String getItemKey() {
        return itemKey;
    }

    public String getItemValue() {
        return itemValue;
    }

    public int getStatus() {
        return status;
    }

    public int getSort() {
        return sort;
    }
}
