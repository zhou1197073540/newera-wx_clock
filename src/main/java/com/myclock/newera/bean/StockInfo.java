package com.myclock.newera.bean;

import com.myclock.newera.utils.TimeUtil;

public class StockInfo {
    private String uid;
    private String account_info;
    private String trusts_info;
    private String position_info;
    private String balance_info;
    private String date= TimeUtil.getCurDateTime();

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccount_info() {
        return account_info;
    }

    public void setAccount_info(String account_info) {
        this.account_info = account_info;
    }

    public String getTrusts_info() {
        return trusts_info;
    }

    public void setTrusts_info(String trusts_info) {
        this.trusts_info = trusts_info;
    }

    public String getPosition_info() {
        return position_info;
    }

    public void setPosition_info(String position_info) {
        this.position_info = position_info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBalance_info() {
        return balance_info;
    }

    public void setBalance_info(String balance_info) {
        this.balance_info = balance_info;
    }
}
