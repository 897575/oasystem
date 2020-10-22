package com.just.oasystem.file.service.Impl;

import com.just.oasystem.file.dao.FileDao;
import com.just.oasystem.file.model.FileInfo;
import com.just.oasystem.file.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Resource
    private FileDao fileDao;

    @Value("${win.spring.upload.url}")
    private String winUploadFileUrl;

    @Override
    public List<FileInfo> getAllFile() {
        return fileDao.getAllFile();
    }

    @Override
    public String getFileInfoByFileName(String fileName) {
        return fileDao.getFileInfoByFileName(fileName);
    }

    @Override
    public String dealUploadFile(MultipartFile multipartFile,FileInfo fileInfo) {
        try{
            File file = new File(winUploadFileUrl);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdir();
            }
            multipartFile.transferTo(file);
        }catch (Exception e){
            return "上传失败";
        }
        return "";
    }
}
