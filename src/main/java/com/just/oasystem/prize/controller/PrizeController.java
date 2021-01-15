package com.just.oasystem.prize.controller;

import com.just.oasystem.prize.model.PrizeInfo;
import com.just.oasystem.prize.service.PrizeService;
import com.just.oasystem.util.OaUtil;
import com.just.oasystem.util.ResponeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *奖品控制层
 *
 * @author luyu
 * @since 20201226
 * @version v1.0
 *
 * copyright 18994139782@163.com
 */
@RestController
public class PrizeController {

    @Resource
    private PrizeService prizeService;

    @GetMapping("prize/queryAll")
    public Map<String,Object> queryAllPrizeInfo(int page,int limit){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("page", page);
        resultMap.put("limit", limit);
        List<PrizeInfo> prizeInfos = prizeService.getAllPrizeInfo(page,limit);
        resultMap.put("code", "0");
        resultMap.put("count", prizeService.countPrizeInfo());
        resultMap.put("data", prizeInfos);
        return resultMap;
    }

    @PostMapping("/prize/addPrize")
    public Map<String, Object> savePrizeInfo(PrizeInfo prizeInfo){
        if(prizeInfo.getPrizeName()==null||"".equals(prizeInfo.getPrizeName())||
                prizeInfo.getPrizeStatus()==null||"".equals(prizeInfo.getPrizeStatus())
                ||prizeInfo.getPrizeLevel()==null||"".equals(prizeInfo.getPrizeLevel())){
                return ResponeUtil.respondError("请输入正确的参数");
        }
        //目前有多个不同奖品，不排冲
//        PrizeInfo resultPrizeInfo = prizeService.queryInfoByParam(prizeInfo);
//        if(resultPrizeInfo!=null){
//            return ResponeUtil.respondError("已经存在该奖品等级");
//        }
        prizeService.insertPrizeInfo(prizeInfo);
        return ResponeUtil.respondSuccess();
    }

    @PostMapping("/prize/update")
    public Map<String, Object> update(String prizeStatus,Integer prizeId){
        if(prizeStatus==null||"".equals(prizeStatus)||prizeId==null){
            return ResponeUtil.respondError("请输入参数");
        }
        PrizeInfo prizeInfo = new PrizeInfo();
        prizeInfo.setPrizeId(prizeId);
        prizeInfo.setPrizeStatus(prizeStatus);
        prizeService.updatePrizeStatus(prizeInfo);
        return ResponeUtil.respondSuccess();
    }

    @GetMapping("/prize/queryAllNoLimit")
    public Map<String,Object> getAllPrizeNoLimit(){
        List<PrizeInfo> prizeInfos = prizeService.getAllPrizeInfoNoLimit();
        //将产品分割
        Map<String,Object> result = new HashMap<>();
        result.put("prizeCount",prizeService.queryPrizeSize());
        result.put("data",OaUtil.divideInfo(prizeInfos));
        return result;
    }
}
