package com.just.oasystem.prize.dao;

import com.just.oasystem.prize.model.PrizeWinInfo;

import java.util.List;
import java.util.Map;


/**
 * 中奖交互层
 *
 * @author luyu
 * @since 20201226
 * @version v1.0
 *
 * copyright 18994139782@63.com
 */
public interface PrizeWinDao {

    List<PrizeWinInfo> getAllPrizeWinInfo(Map<String,Object> param);

    void insertPrizeWinInfos(List<PrizeWinInfo> list);

    int countPrizeWinInfo();
}
