package org.java.yp.service;

import org.java.yp.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
