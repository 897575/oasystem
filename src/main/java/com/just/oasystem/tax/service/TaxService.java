package com.just.oasystem.tax.service;

import com.just.oasystem.tax.model.TaxInfo;

import java.util.List;
import java.util.Map;

public interface TaxService {


    List<TaxInfo> getAllTaxInfo(int page,int pageSize,String userNo);

    void insertTaxInfo(TaxInfo taxInfo);

    void updateTaxInfo(List<String> ids,TaxInfo taxInfo);

    int countAllInfo(Map<String,Object> param);

}
