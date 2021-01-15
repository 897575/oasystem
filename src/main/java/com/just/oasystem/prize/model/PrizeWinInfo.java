package com.just.oasystem.prize.model;

/**
 * 获奖信息
 *
 * @author luyu
 * @since 20201226
 * @version v1.0
 *
 * copyright 18994139782@63.com
 *
 */
public class PrizeWinInfo {

    private String prizeWinId;

    private String userNo;

    private String userName;

    private String prizeName;

    private String prizeLevel;

    private String prizeType;

    private String prizeDescribe;

    private String prizeWinTime;

    public String getPrizeLevel() {
        return prizeLevel;
    }

    public void setPrizeLevel(String prizeLevel) {
        this.prizeLevel = prizeLevel;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeWinId() {
        return prizeWinId;
    }

    public void setPrizeWinId(String prizeWinId) {
        this.prizeWinId = prizeWinId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public String getPrizeDescribe() {
        return prizeDescribe;
    }

    public void setPrizeDescribe(String prizeDescribe) {
        this.prizeDescribe = prizeDescribe;
    }

    public String getPrizeWinTime() {
        return prizeWinTime;
    }

    public void setPrizeWinTime(String prizeWinTime) {
        this.prizeWinTime = prizeWinTime;
    }
}
