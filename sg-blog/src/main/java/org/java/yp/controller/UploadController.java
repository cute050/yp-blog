package org.java.yp.controller;

import org.java.yp.domain.ResponseResult;
import org.java.yp.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 杨鹏
 * @version 1.0
 * Create by 2022/8/31 20:44
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
