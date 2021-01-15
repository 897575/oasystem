package com.just.oasystem.prize.service.impl;

import com.just.oasystem.prize.dao.PrizeWinDao;
import com.just.oasystem.prize.model.PrizeWinInfo;
import com.just.oasystem.prize.service.PrizeWinService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("prizeWinService")
public class PrizeWinServiceImpl implements PrizeWinService {

    @Resource
    private PrizeWinDao prizeWinDao;

    @Override
    public List<PrizeWinInfo> getAllPrizeWinInfo(int page,int pageSize) {
        Map<String,Object> param = new HashMap<>();
        param.put("page",(page - 1)*10);
        param.put("pageSize",pageSize);
        return prizeWinDao.getAllPrizeWinInfo(param);
    }

    @Override
    public void insertPrizeWinInfos(List<PrizeWinInfo> list) {
        prizeWinDao.insertPrizeWinInfos(list);
    }

    @Override
    public int countPrizeWinInfo() {
        return prizeWinDao.countPrizeWinInfo();
    }
}
