package com.crazy.gy.bean;

/**
 * 作者：Administrator
 * 时间：2017/7/14
 * 功能：
 */
public class Favort {
    private String sName;
    private String sId;
    private boolean isZan;

    public Favort(String sName) {
        this.sName = sName;
    }

    public Favort() {
    }

    public Favort(String sName, String sId) {
        this.sName = sName;
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }
}
