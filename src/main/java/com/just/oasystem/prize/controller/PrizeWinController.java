package com.just.oasystem.prize.controller;

import com.alibaba.fastjson.JSONArray;
import com.just.oasystem.prize.model.PrizeWinInfo;
import com.just.oasystem.prize.service.PrizeWinService;
import com.just.oasystem.util.ResponeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *中奖控制层
 *
 * @author luyu
 * @since 20201226
 * @version v1.0
 *
 * copyright 18994139782@163.com
 */

@RestController
public class PrizeWinController {

    @Resource
    private PrizeWinService prizeWinService;

    @GetMapping("/prizeWin/queryAll")
    public Map<String,Object> queryAllPrizeInfo(int page, int limit){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("page", page);
        resultMap.put("limit", limit);
        List<PrizeWinInfo> prizeInfos = prizeWinService.getAllPrizeWinInfo(page,limit);
        resultMap.put("code", "0");
        resultMap.put("count", prizeWinService.countPrizeWinInfo());
        resultMap.put("data", prizeInfos);
        return resultMap;
    }

    @PostMapping("/prizeWin/savePrizeInfo")
    public Map<String,Object> savePrizeWinInfo(String prizeWinInfos){
        if(prizeWinInfos==null||"".equals(prizeWinInfos)){
            return ResponeUtil.respondError("情输入正确的参数");
        }
        List<PrizeWinInfo> prizeWinInfoList = JSONArray.parseArray(prizeWinInfos,PrizeWinInfo.class);
        prizeWinService.insertPrizeWinInfos(prizeWinInfoList);
        return ResponeUtil.respondSuccess();
    }
}
