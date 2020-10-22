package com.just.oasystem.file.model;

/**
 * 文件实体
 *
 * @author luyu
 * @version v1.0
 * <p>
 * copyright 18994139782@163.com
 * @since 20201018
 */
public class FileInfo {

    private String fileNo;
    private String fileName;
    private String fileAuthor;
    private String fileUploader;
    private String createTime;

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileAuthor() {
        return fileAuthor;
    }

    public void setFileAuthor(String fileAuthor) {
        this.fileAuthor = fileAuthor;
    }

    public String getFileUploader() {
        return fileUploader;
    }

    public void setFileUploader(String fileUploader) {
        this.fileUploader = fileUploader;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
