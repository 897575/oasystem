package com.just.oasystem.util;

import com.just.oasystem.user.model.UserInfo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
                    || userInfo.getMobilePhone() == null || "".equals(userInfo.getMobilePhone())
                    || userInfo.getPassword() == null || "".equals(userInfo.getPassword())
                    || userInfo.getStatus() == null || "".equals(userInfo.getStatus())) {
                result = true;
            }
        } else if ("modify".equals(type)) {
            if (userInfo == null || userInfo.getUserNo() == null || "".equals(userInfo.getUserNo())
                    || userInfo.getStatus() == null || "".equals(userInfo.getStatus())
                    || userInfo.getMobilePhone() == null || "".equals(userInfo.getMobilePhone())
                    || userInfo.getAuthority() == null || "".equals(userInfo.getAuthority())){
                result = true;
            }
        }
        return result;
    }
}
