package com.just.oasystem.prize.service.impl;

import com.just.oasystem.prize.dao.PrizeDao;
import com.just.oasystem.prize.model.PrizeInfo;
import com.just.oasystem.prize.service.PrizeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("prizeService")
public class PrizeServiceImpl implements PrizeService {

    @Resource
    private PrizeDao prizeDao;

    @Override
    public List<PrizeInfo> getAllPrizeInfo(int page, int pageSize) {
        Map<String,Object> param =new HashMap<>();
        param.put("page",(page - 1)*10);
        param.put("pageSize",pageSize);
        return prizeDao.getAllPrizeInfo(param);
    }

    @Override
    public void insertPrizeInfo(PrizeInfo prizeInfo) {
        prizeDao.insertPrizeInfo(prizeInfo);
    }

    @Override
    public int countPrizeInfo() {
        return prizeDao.countPrizeInfo();
    }

    @Override
    public PrizeInfo queryInfoByParam(PrizeInfo prizeInfo) {
        return prizeDao.queryInfoByParam(prizeInfo);
    }

    @Override
    public void updatePrizeStatus(PrizeInfo prizeInfo) {
        prizeDao.updatePrizeStatus(prizeInfo);
    }

    @Override
    public List<PrizeInfo> getAllPrizeInfoNoLimit() {
        return prizeDao.getAllPrizeInfoNoLimit();
    }

    @Override
    public int queryPrizeSize() {
        return prizeDao.queryPrizeSize();
    }
}
