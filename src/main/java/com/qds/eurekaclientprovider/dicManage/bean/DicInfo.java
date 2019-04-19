package com.qds.eurekaclientprovider.dicManage.bean;

import java.io.Serializable;
import java.util.Objects;

public class DicInfo implements Serializable {
    private String id;
    private String groupCode;
    private String groupName;
    private String dicCode;
    private String dicName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DicInfo dicInfo = (DicInfo) o;
        return Objects.equals(id, dicInfo.id) &&
                Objects.equals(groupCode, dicInfo.groupCode) &&
                Objects.equals(groupName, dicInfo.groupName) &&
                Objects.equals(dicCode, dicInfo.dicCode) &&
                Objects.equals(dicName, dicInfo.dicName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupCode, groupName, dicCode, dicName);
    }

    @Override
    public String toString() {
        return "DicInfo{" +
                "id='" + id + '\'' +
                ", groupCode='" + groupCode + '\'' +
                ", groupName='" + groupName + '\'' +
                ", dicCode='" + dicCode + '\'' +
                ", dicName='" + dicName + '\'' +
                '}';
    }
}
