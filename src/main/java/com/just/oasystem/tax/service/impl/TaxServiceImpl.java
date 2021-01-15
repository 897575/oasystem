package com.just.oasystem.tax.service.impl;

import com.just.oasystem.tax.dao.TaxDao;
import com.just.oasystem.tax.model.TaxInfo;
import com.just.oasystem.tax.service.TaxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("taxService")
public class TaxServiceImpl implements TaxService {

    @Resource
    private TaxDao taxDao;

    @Override
    public List<TaxInfo> getAllTaxInfo(int page, int pageSize,String userNo) {
        Map<String,Object> param = new HashMap<>();
        param.put("page",(page-1)*10);
        param.put("pageSize",pageSize);
        param.put("userNo",userNo);
        return taxDao.getAllTaxInfo(param);
    }

    @Override
    public void insertTaxInfo(TaxInfo taxInfo) {
        taxDao.insertTaxInfo(taxInfo);
    }

    @Override
    public void updateTaxInfo(List<String> ids,TaxInfo taxInfo) {
        Map<String,Object> param = new HashMap<>();
        param.put("approvalNo",taxInfo.getApprovalNo());
        param.put("approvalOpinion",taxInfo.getApprovalOpinion());
        param.put("list",ids);
        taxDao.updateTaxInfo(param);
    }

    @Override
    public int countAllInfo(Map<String,Object> param) {

        return taxDao.countAllInfo(param);
    }
}
