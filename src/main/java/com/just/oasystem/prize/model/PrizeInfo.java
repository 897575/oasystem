package com.just.oasystem.prize.model;

/**
 * 奖品结构
 *
 * @author luyu
 * @since 20201226
 * @version v1.0
 *
 * copyright 18994139782@163.com
 */
public class PrizeInfo {

    private Integer prizeId;

    private String prizeName;

    private String prizeLevel;

    private String prizeType;

    private String prizeDescribe;

    private String prizeStatus;

    private String prizeTime;

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public String getPrizeTime() {
        return prizeTime;
    }

    public void setPrizeTime(String prizeTime) {
        this.prizeTime = prizeTime;
    }

    public String getPrizeStatus() {
        return prizeStatus;
    }

    public void setPrizeStatus(String prizeStatus) {
        this.prizeStatus = prizeStatus;
    }

    public Integer getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Integer prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeLevel() {
        return prizeLevel;
    }

    public void setPrizeLevel(String prizeLevel) {
        this.prizeLevel = prizeLevel;
    }

    public String getPrizeDescribe() {
        return prizeDescribe;
    }

    public void setPrizeDescribe(String prizeDescribe) {
        this.prizeDescribe = prizeDescribe;
    }
}
