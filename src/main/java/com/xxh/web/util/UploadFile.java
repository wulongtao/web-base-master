package com.xxh.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author 小小黑
 */
public class UploadFile implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(UploadFile.class);
    /**
     * 子目录名称
     */
    private static final String SUB_DIRECTORY = "uploads";
    private MultipartFile file;
    private String filePath;


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean save() {
        //拼接子目录
        StringBuilder sb = new StringBuilder();
        sb.append(SpringUtil.getWebappRootPath());
        sb.append(SUB_DIRECTORY);
        sb.append(File.separator);
        //拼接日期
        sb.append(DateTimeUtil.getDateNow());
        sb.append(File.separator);

        String path = sb.toString();
        logger.info("Upload file path = " + path);

        String filename = file.getOriginalFilename();
        File savedFile = new File(path, filename);
        if (!savedFile.getParentFile().exists()) {
            savedFile.getParentFile().mkdirs();
        }


        this.filePath = path + File.separator + filename;
        try {
            file.transferTo(new File(this.filePath));
        } catch (IOException e) {
            this.filePath = null;
            return false;
        }

        return true;
    }
}
