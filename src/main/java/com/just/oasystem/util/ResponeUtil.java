package com.just.oasystem.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 前台返回
 *
 * @author luyu
 * @since 20201017
 * @version v1.0
 *
 * copyright 18994139782@63.com
 */
public class ResponeUtil {

    /**
     * 成功返回
     * @return 返回成功
     */
    public static Map<String,Object> respondSuccess(){
        Map<String,Object> result = new HashMap<>();
        result.put("status","200");
        result.put("message","操作成功");
        return result;
    }

    /**
     * 成功并且带有返回数据
     * @param data 数据
     * @return 成功信息
     */
    public static Map<String,Object> respondSuccessData(Object data){
        if(data==null){
            return null;
        }
        Map<String,Object> result = new HashMap<>();
        result.put("status","200");
        result.put("message","操作成功");
        result.put("data",data);
        return result;
    }

    /**
     * 异常返回
     * @param message 异常信息
     * @return 异常信息
     */
    public static Map<String,Object> respondError(String message){
        if(message==null||"".equals(message)){
            return null;
        }
        Map<String,Object> result = new HashMap<>();
        result.put("status","500");
        result.put("message",message);
        return result;
    }
}
