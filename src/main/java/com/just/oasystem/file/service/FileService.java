package com.just.oasystem.file.service;

import com.just.oasystem.file.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件服务
 *
 * @author luyu
 * @version v1.0
 * <p>
 * copyright 18994139782@163.com
 * @since 20201019
 */
public interface FileService {


    /**
     * 获取所有文件
     * @return 文件数据
     */
    List<FileInfo> getAllFile();

    /**
     * 获取文件信息
     * @param fileName 文件名称
     * @return 文件信息
     */
    String getFileInfoByFileName(String fileName);


    /**
     * 处理文件信息
     * @param multipartFile 文件信息
     * @return 处理结果
     */
    String dealUploadFile(MultipartFile multipartFile,FileInfo fileInfo);
}
