package com.just.oasystem.user.service;

import java.util.Map;

/**
 * 邮箱发送服务类
 *
 * @author luyu
 * @since 20201021
 * @version v1.0
 *
 * copyright 18994139782@163.com
 */
public interface MailService {


    /**
     * 发送普通邮件
     *
     * @param to  收件人
     * @param subject 主题
     * @param content 内容
     */
     String sendSimpleMailMessge(String to, String subject, String content);

    /**
     * 发送 HTML 邮件
     *
     * @param to  收件人
     * @param subject 主题
     * @param content 内容
     */
     void sendMimeMessge(String to, String subject, String content) ;

    /**
     * 发送带附件的邮件
     *
     * @param to  收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件路径
     */
     void sendMimeMessge(String to, String subject, String content, String filePath);

    /**
     * 发送带静态文件的邮件
     *
     * @param to  收件人
     * @param subject 主题
     * @param content 内容
     * @param rscIdMap 需要替换的静态文件
     */
     default void sendMimeMessge(String to, String subject, String content, Map<String, String> rscIdMap) {

    }

}
