package com.crazy.gy.bean;

/**
 * 作者：Administrator
 * 时间：2017/7/14
 * 功能：
 */
public class Comment {
    private String sUserName;
    private String sUserId;
    private String commentId;
    private String sContent;
    private String sCommentName;



    private String appointUserNickname;
    private String appointUserid;
    private String content;
    private String createTime;
    private String id;
    private String pictures;
    private String publishId;
    private String userId;
    private String userNickname;

    public Comment(String sUserName, String sContent) {
        this.sUserName = sUserName;
        this.sContent = sContent;
    }

    public String getAppointUserNickname() {
        return appointUserNickname;
    }

    public void setAppointUserNickname(String appointUserNickname) {
        this.appointUserNickname = appointUserNickname;
    }

    public Comment(String userId, String userNickname, String appointUserid, String appointUserNickname, String content) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.appointUserid = appointUserid;
        this.appointUserNickname = appointUserNickname;
        this.content = content;
    }

    public String getAppointUserid() {
        return appointUserid;
    }

    public void setAppointUserid(String appointUserid) {
        this.appointUserid = appointUserid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Comment(String sCommentName) {
        this.sCommentName = sCommentName;
    }

    public String getsUserName() {
        return sUserName;
    }

    public void setsUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getsUserId() {
        return sUserId;
    }

    public void setsUserId(String sUserId) {
        this.sUserId = sUserId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getsContent() {
        return sContent;
    }

    public void setsContent(String sContent) {
        this.sContent = sContent;
    }

    public String getsCommentName() {
        return sCommentName;
    }

    public void setsCommentName(String sCommentName) {
        this.sCommentName = sCommentName;
    }
}
