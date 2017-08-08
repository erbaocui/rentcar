package com.cn.model;

import java.math.BigDecimal;
import java.util.Date;

public class Statistics {

    private String statistics;


    private BigDecimal sumFee;


    private BigDecimal sumTotal;

    private BigDecimal sumCommission;

    public Statistics() {

    }

    public Statistics(String statistics, BigDecimal sumFee, BigDecimal sumTotal,BigDecimal sumCommission) {
        this.statistics = statistics;
        this.sumFee = sumFee;
        this.sumTotal = sumTotal;
        this.sumCommission = sumCommission;
    }

    public String getStatistics() {
        return statistics;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }

    public BigDecimal getSumFee() {
        return sumFee;
    }

    public void setSumFee(BigDecimal sumFee) {
        this.sumFee = sumFee;
    }

    public BigDecimal getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(BigDecimal sumTotal) {
        this.sumTotal = sumTotal;
    }

    public BigDecimal getSumCommission() {
        return sumCommission;
    }

    public void setSumCommission(BigDecimal sumCommission) {
        this.sumCommission = sumCommission;
    }
}