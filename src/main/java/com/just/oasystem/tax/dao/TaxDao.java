package com.just.oasystem.tax.dao;

import com.just.oasystem.tax.model.TaxInfo;

import java.util.List;
import java.util.Map;

/**
 * 报销交互层
 *
 * @author luyu
 * @version v1.0
 * <p>
 * copyright 18994139782@163.com
 * @since 20201228
 */
public interface TaxDao {

    List<TaxInfo> getAllTaxInfo(Map<String,Object> param);

    void insertTaxInfo(TaxInfo taxInfo);

    void updateTaxInfo(Map<String,Object> param);

    int countAllInfo(Map<String,Object> param);
}
