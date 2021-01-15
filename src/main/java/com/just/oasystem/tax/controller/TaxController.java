package com.just.oasystem.tax.controller;

import com.just.oasystem.tax.model.TaxInfo;
import com.just.oasystem.tax.service.TaxService;
import com.just.oasystem.util.ResponeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报销控制层
 *
 * @author luyu
 * @since 20201228
 * @version v1.0
 *
 * copyright 18994139782@163.com
 */
@RestController
public class TaxController {

    @Resource
    private TaxService taxService;


    @PostMapping("tax/add")
    public Map<String,Object> saveTaxInfo(TaxInfo taxInfo){
        if(taxInfo==null||taxInfo.getMoney()==null||taxInfo.getMoney()==null||
                taxInfo.getBillNo()==null||"".equals(taxInfo.getBillNo())
                || taxInfo.getTaxDescribe()==null||"".equals(taxInfo.getTaxDescribe())){
            return ResponeUtil.respondError("请输入正确的参数");
        }
        taxInfo.setUserNo("209070038");
        Map<String,Object> param = new HashMap<>();
        param.put("billNo",taxInfo.getBillNo());
        int result = taxService.countAllInfo(param);
        if(result==1){
            return ResponeUtil.respondError("数据已经存在");
        }
        taxInfo.setTaxStatus("未审核");
        taxService.insertTaxInfo(taxInfo);
        return ResponeUtil.respondSuccess();
    }


    @GetMapping("/tax/query")
    public Map<String,Object> queryAllInfo(int page,int limit){
        //判断用户权限
        String userNo = "209070038";
        String authority = null;
        Map<String, Object> resultMap = new HashMap<>();
        if(authority==null){
            userNo=null;
        }
        List<TaxInfo> taxInfos = taxService.getAllTaxInfo(page,limit,userNo);
        resultMap.put("page", page);
        resultMap.put("limit", limit);
        resultMap.put("code", "0");
        resultMap.put("userNo",userNo);
        resultMap.put("count", taxService.countAllInfo(resultMap));
        resultMap.put("data", taxInfos);
        return resultMap;
    }

    @PostMapping("/tax/update")
    public Map<String,Object> updateTaxInfo(List<String> taxInfos,TaxInfo taxInfo){
        if(taxInfos==null||taxInfos.isEmpty()||taxInfo==null){
            return ResponeUtil.respondError("请输入有效参数");
        }
        taxService.updateTaxInfo(taxInfos,taxInfo);
        return ResponeUtil.respondSuccess();
    }
}
