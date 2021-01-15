package com.just.oasystem.prize.dao;

import com.just.oasystem.prize.model.PrizeInfo;

import java.util.List;
import java.util.Map;

/**
 * 奖品交互层
 *
 * @author luyu
 * @since 20201226
 * @version v1.0
 *
 * copyright 18994139782@63.com
 */
public interface PrizeDao {

    List<PrizeInfo> getAllPrizeInfo(Map<String,Object> param);

    void insertPrizeInfo(PrizeInfo prizeInfo);

    int countPrizeInfo();

    PrizeInfo queryInfoByParam(PrizeInfo prizeInfo);

    void updatePrizeStatus(PrizeInfo prizeInfo);

    List<PrizeInfo> getAllPrizeInfoNoLimit();

    int queryPrizeSize();
}
