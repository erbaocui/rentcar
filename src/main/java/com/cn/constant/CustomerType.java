package com.cn.constant;

/**
 * Created by home on 2017/7/22.
 */
public enum CustomerType {
    PERSON("个人", 0), ENTERPRISE("企业", 1);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private CustomerType(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (CustomerType c : CustomerType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
