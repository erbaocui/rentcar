package com.cn.constant;

/**
 * Created by home on 2017/7/22.
 */
public enum OrderType {

    DAY("日租包车", 1), FIGHT("机场接送", 2), TRAIN("车站接送", 3),MONTH ("月租包车", 4),ENTERPRISE("企业", 5);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private OrderType(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (OrderType c : OrderType.values()) {
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
