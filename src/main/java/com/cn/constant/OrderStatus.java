package com.cn.constant;

/**
 * Created by home on 2017/7/22.
 */
public enum OrderStatus {
    CREATE("等待处理", 1), DISPATCH("预约完成", 2),FINISH("订单完成", 3),CANCEL("订单取消", 4),TERMINAL("订单完结", 5);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private OrderStatus(String name, int index) {
        this.name = name;
        this.index=index;
    }
    // 普通方法
    public static String getName(int index) {
        for (OrderStatus c : OrderStatus.values()) {
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
