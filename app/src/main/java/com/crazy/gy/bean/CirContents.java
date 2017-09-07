package com.crazy.gy.bean;

import android.text.TextUtils;

import com.jaydenxiao.common.baseapp.AppCache;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Administrator
 * 时间：2017/8/4
 * 功能：
 */
public class CirContents {
    private String sImg;
    private String sName;
    private int sendNum;
    private int acceptNum;
    private String sContent;
    private String sTime;
    private double farNum;
    private List<Favort> goodjobs = new ArrayList<>();
    private List<Comment> replys = new ArrayList<>();

    public CirContents(String sImg, String sName, int sendNum, int acceptNum, String sContent, String sTime, double farNum, List<Favort> goodjobs, List<Comment> replys) {
        this.sImg = sImg;
        this.sName = sName;
        this.sendNum = sendNum;
        this.acceptNum = acceptNum;
        this.sContent = sContent;
        this.sTime = sTime;
        this.farNum = farNum;
        this.goodjobs = goodjobs;
        this.replys = replys;
    }

    public List<Favort> getGoodjobs() {
        return goodjobs;
    }

    public void setGoodjobs(List<Favort> goodjobs) {
        this.goodjobs = goodjobs;
    }

    public List<Comment> getReplys() {
        return replys;
    }

    public void setReplys(List<Comment> replys) {
        this.replys = replys;
    }

    public CirContents(String sImg, String sName, int sendNum, int acceptNum, String sContent, String sTime, double farNum) {
        this.sImg = sImg;
        this.sName = sName;
        this.sendNum = sendNum;
        this.acceptNum = acceptNum;
        this.sContent = sContent;
        this.sTime = sTime;
        this.farNum = farNum;
    }

    public String getsImg() {
        return sImg;
    }

    public void setsImg(String sImg) {
        this.sImg = sImg;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getSendNum() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }

    public int getAcceptNum() {
        return acceptNum;
    }

    public void setAcceptNum(int acceptNum) {
        this.acceptNum = acceptNum;
    }

    public String getsContent() {
        return sContent;
    }

    public void setsContent(String sContent) {
        this.sContent = sContent;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public double getFarNum() {
        return farNum;
    }

    public void setFarNum(double farNum) {
        this.farNum = farNum;
    }

    public String getCurUserFavortId() {
        String userId = "";
        String myId = AppCache.getInstance().getUserId();
        if (goodjobs != null && !TextUtils.isEmpty(myId) && goodjobs.size() > 0) {
            for (Favort item : goodjobs) {
                if (myId.equals(item.getsId())) {
                    userId = item.getsId();
                    return userId;
                }
            }
        }
        return userId;
    }
}
