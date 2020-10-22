package com.just.oasystem.file.controller;

import com.just.oasystem.file.model.FileInfo;
import com.just.oasystem.file.service.FileService;
import com.just.oasystem.util.ResponeUtil;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;

/**
 * 文件管理
 *
 * @author luyu
 * @version v1.0
 * <p>
 * copyright 18994139782@63.com
 * @since 20201019
 */
@Controller
public class FileController {

    @Resource
    private FileService fileService;

    @ResponseBody
    @PostMapping("/upload")
    public Map<String,Object> uploadFile(MultipartFile multipartFile,String uploader,String author){

        if(multipartFile==null||uploader==null||"".equals(uploader)||author==null||"".equals(author)){
            return ResponeUtil.respondError("缺少参数");
        }
        String fileName = multipartFile.getOriginalFilename();
        String baseFileName = fileService.getFileInfoByFileName(fileName);
        if(baseFileName!=null&&fileName.split(".")[0].equals(baseFileName.split(".")[0])){
            return ResponeUtil.respondError("请检查文件名是否重复");
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(fileName);
        fileInfo.setFileAuthor(author);
        String uploadResult = fileService.dealUploadFile(multipartFile,fileInfo);
        if(!"".equals(uploadResult)){
            return ResponeUtil.respondError(uploadResult);
        }
        return ResponeUtil.respondSuccess();
    }

}