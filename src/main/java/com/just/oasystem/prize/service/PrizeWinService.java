package com.just.oasystem.prize.service;

import com.just.oasystem.prize.model.PrizeWinInfo;

import java.util.List;
import java.util.Map;

public interface PrizeWinService {

    List<PrizeWinInfo> getAllPrizeWinInfo(int page,int pageSize);

    void insertPrizeWinInfos(List<PrizeWinInfo> list);

    int countPrizeWinInfo();
}
