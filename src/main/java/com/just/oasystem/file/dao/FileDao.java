package com.just.oasystem.file.dao;

import com.just.oasystem.file.model.FileInfo;

import java.io.File;
import java.util.List;

/**
 * 文件管理交互层
 *
 * @author luyu
 * @since v1.0
 * @version v1.0
 *
 * copyright 1899413978@63.com
 */
public interface FileDao {

    /**
     * 获取所有文件
     * @return 文件数据
     */
    List<FileInfo> getAllFile();

    /**
     * 获取文件信息
     * @param fileName 文件名
     * @return 文件信息
     */
    String getFileInfoByFileName(String fileName);
}
