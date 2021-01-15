package com.just.oasystem.util;

import com.just.oasystem.prize.model.PrizeInfo;
import com.just.oasystem.user.model.UserInfo;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * oa工具类
 *
 * @author luyu
 * @version v1.0
 * <p>
 * copyright 18994139782@163.com
 * @since 20201017
 */
public class OaUtil {

    /**
     * 密码加密
     *
     * @param password 密码
     * @return 加密的密码
     */
    public static String transformPass(String password) throws NoSuchAlgorithmException {
        if (password == null || "".equals(password)) {
            return null;
        }
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        return new BigInteger(md.digest()).toString(32);
    }

    /**
     * 用户信息检查
     *
     * @param userInfo 用户信息
     * @return 检查结果
     */
    public static boolean checkUserInfo(UserInfo userInfo, String type) {
        boolean result = false;
        if ("save".equals(type)) {
            if (userInfo == null || userInfo.getUserNo() == null || "".equals(userInfo.getUserNo())
                    || userInfo.getAuthority() == null || "".equals(userInfo.getAuthority())
                    || userInfo.getMobilePhone() == null || "".equals(userInfo.getMobilePhone())) {
                result = true;
            }
        } else if ("modify".equals(type)) {
            if (userInfo == null || userInfo.getUserNo() == null || "".equals(userInfo.getUserNo())
                    || userInfo.getEmail() == null || "".equals(userInfo.getEmail())
                    || userInfo.getMobilePhone() == null || "".equals(userInfo.getMobilePhone())
                    || userInfo.getAuthority() == null || "".equals(userInfo.getAuthority())){
                result = true;
            }
        }
        return result;
    }


    /**
     * 对象转map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            boolean accessFlag = fields[i].isAccessible();
            fields[i].setAccessible(true);// 允许通过反射访问该字段

            Object valueObj = fields[i].get(obj);
            if (valueObj != null) {
                map.put(varName, valueObj);
            }
            fields[i].setAccessible(accessFlag);
        }
        return map;
    }

    /**
     * 判断用户是否是管理员
     * @param userInfo 人员信息
     * @return 判断结果
     */
    public static String isManager(UserInfo userInfo){

        if(userInfo==null||userInfo.getAuthority()==null||"".equals(userInfo.getAuthority())){
            return null;
        }
        if("学生".equals(userInfo.getAuthority())){
            return "学生";
        }else{
            return null;
        }
    }

    /**
     *  划分奖品信息
     * @param prizeInfos 奖品信息
     * @return
     */
    public static Map<String,List<PrizeInfo>> divideInfo(List<PrizeInfo> prizeInfos){
        if(prizeInfos==null||prizeInfos.isEmpty()){
            return null;
        }
        Map<String,List<PrizeInfo>> listMap = new HashMap<>();
        for(PrizeInfo prizeInfo:prizeInfos){
            String relation=OaUtil.relationLevel(prizeInfo.getPrizeLevel());
            //没有放入数据，直接放入数据
            if(!listMap.containsKey(relation)){
                List<PrizeInfo> prizeInfoList = new ArrayList<>();
                prizeInfoList.add(prizeInfo);
                listMap.put(relation,prizeInfoList);
            }else{
                //直接把数据放入其中
                listMap.get(relation).add(prizeInfo);
            }
        }
        return listMap;
    }

    /**
     * 奖品等级与数字的对应
     * @param prizeLevel 奖品等级
     * @return 对应的数字
     */
    public static String relationLevel(String prizeLevel){
        if(prizeLevel==null||"".equals(prizeLevel)){
            return null;
        }
        if("特等奖".equals(prizeLevel)){
            return "0";
        }else if("一等奖".equals(prizeLevel)){
            return "1";
        }else if("二等奖".equals(prizeLevel)){
            return "2";
        }else if("三等奖".equals(prizeLevel)){
            return "3";
        }else if("四等奖".equals(prizeLevel)){
            return "4";
        }else if("五等奖".equals(prizeLevel)){
            return "5";
        }else{
            return "6";
        }
    }
}
