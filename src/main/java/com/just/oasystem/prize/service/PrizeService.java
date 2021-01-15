package com.just.oasystem.prize.service;

import com.just.oasystem.prize.model.PrizeInfo;

import java.util.List;

/**
 * 奖品信息服务
 *
 * @author luyu
 * @since 20201226
 * @version v1.0
 *
 * copyright 18994139782@63.com
 *
 */
public interface PrizeService {

    List<PrizeInfo> getAllPrizeInfo(int page,int pageSize);

    void insertPrizeInfo(PrizeInfo prizeInfo);

    int countPrizeInfo();

    PrizeInfo queryInfoByParam(PrizeInfo prizeInfo);

    void updatePrizeStatus(PrizeInfo prizeInfo);

    List<PrizeInfo> getAllPrizeInfoNoLimit();

    int queryPrizeSize();

}
